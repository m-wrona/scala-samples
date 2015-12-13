package com.mwronski.shapeless

import shapeless._, Nat._
import shapeless.ops.nat.{Mod, Sum, Prod}
import shapeless.ops.tuple.Length

object CheckSum {

  def main(args: Array[String]) {
    // valid lists
    isValid(_3 :: _4 :: _5 :: _8 :: _8 :: _2 :: _8 :: _6 :: _5 :: HNil)

    // invalid lists - won't compile:
    // isValid(_3 :: _1 :: _5 :: _8 :: _8 :: _2 :: _8 :: _6 :: _5 :: HNil) //wrong check-sum
    // isValid(_3 :: _4 :: _5 :: _8 :: _8 :: _2 :: _8 :: _6 :: HNil) //wrong length
  }

  /**
    * Check-sum for empty list
    */
  implicit object hNilHasChecksum extends HasChecksum[HNil, _0]

  /**
    * Check whether any list meets check-sum criteria
    * @param tl tail length
    * @param ts tail check-sum
    * @param hl head length
    * @param hs head check-sum
    * @param sm summary check-sum evidence
    * @tparam H type of head
    * @tparam T type of tail
    * @tparam S type of check-sum evidence
    * @tparam TL type of tail length
    * @tparam TS type of tail check-sum
    * @tparam HL type of head length
    * @tparam HS type of head check-sum
    * @return new check-sum
    */
  implicit def hListHasChecksum[
  H <: Nat, T <: HList, S <: Nat,
  TL <: Nat, TS <: Nat,
  HL <: Nat, HS <: Nat
  ](implicit
    tl: Length.Aux[T, TL],
    ts: HasChecksum[T, TS],
    hl: Prod.Aux[H, Succ[TL], HL],
    hs: Sum.Aux[HL, TS, HS],
    sm: Mod.Aux[HS, _11, S]
   ) = new HasChecksum[H :: T, S] {}

  /**
    * Check that the list has nine elements and a proper checksum.
    * @param l list to be checked
    * @param len length evidence
    * @param hcs check-sum evidence
    * @tparam L type of list
    */
  def isValid[L <: HList](l: L)(implicit
                                len: Length.Aux[L, _9],
                                hcs: HasChecksum[L, _0]
  ) = {
    //empty - nothing to do since compiler will do all the work
  }

}

/**
  * Evidence that the sum of each item in L multiplied by its distance from
  * the end of the list plus one modulo eleven is S.
  */
sealed trait HasChecksum[L <: HList, S <: Nat]
