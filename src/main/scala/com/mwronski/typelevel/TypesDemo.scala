package com.mwronski.typelevel


object TypesDemo {

  def main(args: Array[String]) {
    booleanLogic()
    println("-----------")
    nat()
  }

  private def nat() = {
    import com.mwronski.typelevel.Nat._
    println("Peano numbers")
    type _1 = Succ[_0]
    type _2 = Succ[_1]

    type ConstFalse[A] = False
    type Is0[A <: Nat] = A#Match[ConstFalse, True, Bool]
  }

  private def booleanLogic() = {
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
