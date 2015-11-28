package com.mwronski.typeclasses

object ParserDemo {

  def main(args: Array[String]) {
    shapelessModelParsing
    modelParsing
  }

  def shapelessModelParsing = {
    import ShapelessModelParser._
    val person = ShapelessModelParser[Person]("Michal,31.5")
    println(s"Parsed person using shapeless type classes: ${person}")
    val wrongPerson = ShapelessModelParser[Person]("Michal,wrong")
    println(s"Person parsing error using shapeless type classes: ${wrongPerson}")
  }

  def modelParsing = {
    import ModelParser._
    val person = ModelParser[Person]("Michal,31.5")
    println(s"Parsed person using type classes: ${person}")
    val wrongPerson = ModelParser[Person]("Michal,wrong")
    println(s"Person parsing error using type classes: ${wrongPerson}")
  }


}
