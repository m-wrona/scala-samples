package com.mwronski.typeclasses

/**
  * Type class parser
  */
trait Parser[A] {

  /**
    * Parse value
    * @param s string to be parsed
    * @return optional parsed value
    */
  def apply(s: String): Option[A]

}
