package com.mwronski.typeclasses

object ParserDemo {

  def main(args: Array[String]) {
    shapelessModelParsing
    println("--------------")
    modelParsing
  }

  def shapelessModelParsing = {
    import ShapelessModelParser._
    val person = ShapelessModelParser[Person]("Michal,31.5")
    println(s"Parsed person using shapeless type classes: ${person}")
    val wrongPerson = ShapelessModelParser[Person]("Michal,wrong")
    println(s"Person parsing error using shapeless type classes: ${wrongPerson}")

    val book = ShapelessModelParser[Book]("Fall of Giants,Ken Follett,2010")
    println(s"Parsed book using shapeless type classes: ${book}")
    val wrongBook = ShapelessModelParser[Person]("Fall of Giants,2010,Ken Follett")
    println(s"Person book error using shapeless type classes: ${wrongBook}")
  }

  def modelParsing = {
    import ModelParser._
    val person = ModelParser[Person]("Michal,31.5")
    println(s"Parsed person using type classes: ${person}")
    val wrongPerson = ModelParser[Person]("Michal,wrong")
    println(s"Person parsing error using type classes: ${wrongPerson}")

    val book = ModelParser[Book]("Fall of Giants,Ken Follett,2010")
    println(s"Parsed book using type classes: ${book}")
    val wrongBook = ModelParser[Person]("Fall of Giants,2010,Ken Follett")
    println(s"Person book error using type classes: ${wrongBook}")
  }


}
