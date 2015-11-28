package com.mwronski.typeclasses

import scala.util.Try

/**
  * Member of parser type class
  */
object ModelParser {

  def apply[A](s: String)(implicit parser: Parser[A]): Option[A] = parser(s)

  implicit val personParser: Parser[Person] = new Parser[Person] {
    def apply(s: String): Option[Person] = s.split(",").toList match {
      case List(name, age) => Try(age.toDouble).map(Person(name, _)).toOption
      case _ => None
    }
  }

  implicit val bookParser: Parser[Book] = new Parser[Book] {
    def apply(s: String): Option[Book] = s.split(",").toList match {
      case List(title, author, year) =>
        Try(year.toInt).map(Book(title, author, _)).toOption
      case _ => None
    }
  }

}



