package com.mwronski.pathdependenttypes

/**
  * Movie
  * @param name name of the movie
  */
class Movie(name: String) {

  /**
    * Character that belongs to chosen movie
    * @param name character name
    */
  case class Character(name: String)

  /**
    * Create team from characters
    * @param character1 some character
    * @param character2 other character
    * @return non-nullable
    */
  def team(character1: Character, character2: Character): (Character, Character) = (character1, character2)

}