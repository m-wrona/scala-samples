package com.mwronski.typeclasses

import scala.util.Try
import shapeless._

/**
  * Member of parser type class using shapeless for more generic approach
  */
object ShapelessModelParser {

  def apply[A](s: String)(implicit parser: Parser[A]): Option[A] = parser(s)

  /* induction for parsers of any generic types */

  implicit def caseClassParser[A, R <: HList](implicit
                                              gen: Generic[A] {type Repr = R},
                                              reprParser: Parser[R]
                                             ): Parser[A] =
    new Parser[A] {
      def apply(s: String): Option[A] = reprParser.apply(s).map(gen.from)
    }

  /* parsers for building HList */

  implicit val hNilParser: Parser[HNil] =
    new Parser[HNil] {
      def apply(s: String): Option[HNil] = if (s.isEmpty) Some(HNil) else None
    }

  implicit def hConsParser[H: Parser, T <: HList : Parser]: Parser[H :: T] =
    new Parser[H :: T] {
      def apply(s: String): Option[H :: T] = s.split(",").toList match {
        case cell +: rest => for {
          head <- implicitly[Parser[H]].apply(cell)
          tail <- implicitly[Parser[T]].apply(rest.mkString(","))
        } yield head :: tail
      }
    }

  /* parser for basic types */

  implicit val stringParser: Parser[String] = new Parser[String] {
    def apply(s: String): Option[String] = Some(s)
  }

  implicit val intParser: Parser[Int] = new Parser[Int] {
    def apply(s: String): Option[Int] = Try(s.toInt).toOption
  }

  implicit val doubleParser: Parser[Double] = new Parser[Double] {
    def apply(s: String): Option[Double] = Try(s.toDouble).toOption
  }

}

