package com.mwronski.typelevel


object TypesDemo {

  def main(args: Array[String]) {
    booleanLogic()
    println("-----------")
    nat()
  }

  private def nat() = {
    import com.mwronski.typelevel.Bool._
    import com.mwronski.typelevel.Nat._
    println("Peano numbers")

    println("_0#Compare[_0]#eq = " + toBoolean[_0#Compare[_0]#eq])
    println("_0#Compare[_0]#lt = " + toBoolean[_0#Compare[_0]#lt])
    println("_3#Compare[_4]#le = " + toBoolean[_3#Compare[_4]#le])

    println("Eq[_6, Mult[_2, _3]] = " + toBoolean[Eq[_6, Mult[_2, _3]]])
    println("Eq[Sq[_3], Mult[_3, _3]] = " + toBoolean[Eq[Sq[_3], Mult[_3, _3]]])
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
