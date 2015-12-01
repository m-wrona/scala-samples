package com.mwronski.typelevel


object TypesDemo {

  def main(args: Array[String]) {
    booleanLogic()
    println("-----------")
  }

  private def booleanLogic(): Unit = {
    import com.mwronski.typelevel.Bool._
    println("Boolean logic")
    println("True && True = " + toBoolean[True && True])
    println("True && False = " + toBoolean[True && False])
    println("True && False || Not[False] = " + toBoolean[True && False || Not[False]])
    println("True && True = " + toBoolean[True && True])

    implicitly[Rep[True] =:= Int]
    //    implicitly [Rep[False] =:= Int ] //will not compile check Rep definition
    implicitly[Rep[False] =:= Long]
    implicitly[True && False || Not[False] =:= True]
  }


}
