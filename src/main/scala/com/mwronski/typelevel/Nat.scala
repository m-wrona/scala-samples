package com.mwronski.typelevel

/**
  * Peano numbers
  */
object Nat {

}

sealed trait Nat {

  type Match[NonZero[N <: Nat] <: Up, IfZero <: Up, Up] <: Up

}

sealed trait _0 extends Nat {

  type Match[NonZero[N <: Nat] <: Up, IfZero <: Up, Up] = IfZero

}

sealed trait Succ[N <: Nat] extends Nat {

  type Match[NonZero[N <: Nat] <: Up, IfZero <: Up, Up] = NonZero[N]

}
