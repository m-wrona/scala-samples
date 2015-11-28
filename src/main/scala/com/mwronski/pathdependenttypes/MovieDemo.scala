package com.mwronski.pathdependenttypes

class MovieDemo {

  def main(args: Array[String]) {
    val startWars = new Movie("Start Wars")
    val luke = startWars.Character("Luke Skywalker")
    val obiWan = startWars.Character("Obi-Wan Kenobi")

    val lordOfTheRings = new Movie("Lord Of the Rings")
    val legolas = lordOfTheRings.Character("Legolas")
    val aragorn = lordOfTheRings.Character("Aragorn")

    //build teams for characters from the same movie
    startWars.team(luke, obiWan)
    lordOfTheRings.team(legolas, aragorn)

    //cannot build teams using characters from different movies
    //startWars.team(luke, legolas) //will not compile
    //lordOfTheRings.team(aragorn, obiWan) //will not compile
  }

}
