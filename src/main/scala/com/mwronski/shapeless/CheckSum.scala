package com.mwronski.shapeless

import shapeless._
import nat._
import ops.nat._
import ops.hlist._

object CheckSum {

  def main(args: Array[String]) {
    // valid lists:
    println(isValid(List(3, 4, 5, 8, 8, 2, 8, 6, 5)))
    isValid[_3 :: _4 :: _5 :: _8 :: _8 :: _2 :: _8 :: _6 :: _5 :: HNil]

    // invalid lists - won't compile:
    println(isValid(List(3, 4, 5, 8, 8, 2, 8, 6)))
    //    isValid[_3 :: _4 :: _5 :: _8 :: _8 :: _2 :: _8 :: _6 :: HNil] //wrong length
    println(isValid(List(3, 1, 5, 8, 8, 2, 8, 6, 5)))
    //    isValid[_3 :: _1 :: _5 :: _8 :: _8 :: _2 :: _8 :: _6 :: _5 :: HNil] //wrong check-sum
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
    * @param sm summary check-sum modulo
    * @tparam H type of head
    * @tparam T type of tail
    * @tparam S type of check-sum evidence
    * @tparam TL type of tail length
    * @tparam TS type of tail check-sum
    * @tparam HL type of head length
    * @tparam HS type of head check-sum modulo
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
    * Checksum calculation: (d1+2*d2+3*d3 +..+9*d9) mod 11 = 0
    *
    * @param len length evidence
    * @param hcs check-sum evidence
    * @tparam L type of list
    */
  def isValid[L <: HList](implicit
                          len: Length.Aux[L, _9],
                          hcs: HasChecksum[L, _0]
                         ) = {
    //empty - nothing to do since compiler will do all the work
  }

  /**
    * Evidence that list has proper check-sum.
    */
  sealed trait HasChecksum[L <: HList, S <: Nat]

  /**
    * Count check-sum directly
    * @param l list for which check-sum should be counted
    * @return non-negative number
    */
  private def checksum(l: List[Int]): Int = l
    .reverse
    .zipWithIndex
    .map {
      case (v, i) => v * (i + 1)
    }
    .sum % 11

  /**
    * Check directly whether list is valid
    * @param l list which should be validate
    * @return true if list if valid, false otherwise
    */
  private def isValid(l: List[Int]): Boolean = l.size == 9 && checksum(l) == 0

}
