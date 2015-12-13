package com.mwronski.typelevel

object HList {

  type ::[H, T <: HList] = HCons[H, T]

  val :: = HCons

  val HNil = new HNil

  type Length[H <: HList] = H#Foldr[Nat, Inc, _0]

  type Inc = Fold[Any, Nat] {
    type Apply[N <: Any, Acc <: Nat] = Succ[Acc]
  }

  type :::[A <: HList, B <: HList] = A#Foldr[HList, AppHCons.type, B]

  type Reverse_:::[A <: HList, B <: HList] = A#Foldl[HList, AppHCons.type, B]

  type Reverse[A <: HList] = A#Foldl[HList, AppHCons.type, HNil]

  object AppHCons extends Fold[Any, HList] {

    type Apply[N <: Any, H <: HList] = N :: H

    // used later for value-level implementations
    def apply[A, B <: HList](a: A, b: B) = HCons(a, b)

  }

  sealed trait HListOps[B <: HList] {

    def length: Int

    def :::[A <: HList](a: A): A ::: B

    def reverse: Reverse[B]

    def reverse_:::[A <: HList](a: A): A Reverse_::: B

  }

  implicit def hlistOps[B <: HList](b: B): HListOps[B] = new HListOps[B] {

    def length = b.foldr(Length, 0)

    def reverse = b.foldl[HList, AppHCons.type, HNil](AppHCons, HNil)

    def :::[A <: HList](a: A): A#Foldr[HList, AppHCons.type, B] =
      a.foldr[HList, AppHCons.type, B](AppHCons, b)

    def reverse_:::[A <: HList](a: A): A Reverse_::: B =
      a.foldl[HList, AppHCons.type, B](AppHCons, b)
  }

  object Length extends Fold[Any, Int] {

    type Apply[N <: Any, Acc <: Int] = Int

    def apply[A, B <: Int](a: A, b: B) = b + 1

  }

}

sealed trait HList {

  type Foldr[Value, F <: Fold[Any, Value], I <: Value] <: Value

  def foldr[Value, F <: Fold[Any, Value], I <: Value](f: F, i: I): Foldr[Value, F, I]

  type Foldl[Value, F <: Fold[Any, Value], I <: Value] <: Value

  def foldl[Value, F <: Fold[Any, Value], I <: Value](f: F, i: I): Foldl[Value, F, I]

}

final case class HCons[H, T <: HList](head: H, tail: T) extends HList {

  def ::[T](v: T) = HCons(v, this)

  type Foldr[Value, F <: Fold[Any, Value], I <: Value] = F#Apply[H, tail.Foldr[Value, F, I]]

  def foldr[Value, F <: Fold[Any, Value], I <: Value](f: F, i: I): Foldr[Value, F, I] =
    f(head, tail.foldr[Value, F, I](f, i))

  type Foldl[Value, F <: Fold[Any, Value], I <: Value] = tail.Foldl[Value, F, F#Apply[H, I]]

  def foldl[Value, F <: Fold[Any, Value], I <: Value](f: F, i: I): Foldl[Value, F, I] =
    tail.foldl[Value, F, F#Apply[H, I]](f, f(head, i))

}

sealed class HNil extends HList {

  def ::[T](v: T) = HCons(v, this)

  type Foldr[Value, F <: Fold[Any, Value], I <: Value] = I

  def foldr[Value, F <: Fold[Any, Value], I <: Value](f: F, i: I) = i

  type Foldl[Value, F <: Fold[Any, Value], I <: Value] = I

  def foldl[Value, F <: Fold[Any, Value], I <: Value](f: F, i: I) = i

}


