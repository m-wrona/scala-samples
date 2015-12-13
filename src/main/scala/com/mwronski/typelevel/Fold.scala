package com.mwronski.typelevel

trait Fold[-Elem, Value] {

  type Apply[E <: Elem, V <: Value] <: Value

  def apply[N <: Elem, Acc <: Value](n: N, acc: Acc): Apply[N, Acc]

}
