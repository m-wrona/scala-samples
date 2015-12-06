package com.mwronski.typelevel

/**
  * @author Michal Wronski
  */
trait Fold[-Elem, Value] {

  type Apply[E <: Elem, V <: Value] <: Value

}
