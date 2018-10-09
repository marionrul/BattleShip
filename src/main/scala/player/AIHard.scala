package player
import helper._
import ship._

import scala.util.Random

case class AIHard(private val _name: String, private val _ships: List[Ship], private val _placedHits: List[Point], private val _goodHits: List[Point]) extends AI {

  /**
    * The name of the player
    */
  override val name = _name

  /**
    * The list of ships of the player
    */
  override val ships = _ships

  /**
    * The list of placed hits of the player
    */
  override val placedHits = _placedHits

  /**
    * The list of good hits of the player
    */
  override val goodHits = _goodHits

  /**
    * Creates a new player
    * @param goodHits a new list of goodHits placed
    * @return a new player with the new lis of goodHits
    */
  override def copyPlayerGoodHits(goodHits: List[Point]): Player = {
    this.copy( _goodHits = goodHits )
  }

  /**
    * Creates a new player
    * @param name a new name
    * @return a new player with the new name
    */
  override def copyPlayerName(name: String): Player = {
    this.copy( _name = name )
  }

  /**
    * Creates a new player
    * @param ships a new list of ships
    * @return a new player with the new list of ships
    */
  override def copyPlayerShips(ships: List[Ship]): Player = {
    this.copy( _ships = ships )
  }

  /**
    * Creates a new player
    * @param placedHits a new list of placedHits
    * @return a new player with the new list of placedHits
    */
  override def copyPlayerPlacedHits(placedHits: List[Point]): Player = {
    this.copy( _placedHits = placedHits )
  }


  /**
    * Hits a ship
    * @param player the player that was hit
    * @param pos the position he was hit on
    * @return if one of the player's ships is hit :
    *         - Changes the list of good hits of the player
    *         - Changes the list of hits of the player's ship that was hit
    */
  def hitShip(player: Player, pos: Point): Player = {
    if (Ship.oneShipIsHit( player.ships, pos )) {
      this.copyPlayerGoodHits(this.goodHits :+ pos)
      player.copyPlayerShips( player.ships.map( x => Ship.hitShip( x, pos ) ) )
    }
    else {
      player
    }
  }

  /**
    * Checks if a hit is good
    * @param pos the position that is hit
    * @return true if the position is right and if the player didn't hit here before, false otherwise
    */
  def goodHit(pos: Point): Boolean = {
    if (!this.placedHits.contains(pos) && Point.isGood(pos.x, pos.y)) true
    else false
  }

  /**
    * Makes a shoot that is close to a good hit that was placed
    * @param goodHits the list of good hits of the player
    * @param random the random
    * @return a new position where to make a shot according to a previous shot that was good
    */
  def shoot(goodHits: List[Point], random: Random) : Point = {
    val (x, y) = Input.EntryParametersAI( random, random )
    val pos = Point( x, y )
    if(goodHit(pos)) {
      // if there isn't a good hit
      if (goodHits.isEmpty) {
        // Generates the position randomly
        pos
      }
      else {
        // Takes the first good hit of the list
        val pos = goodHits.head
        // Takes the positions that are close to it
        val pos2 = Point(pos.x + 1, pos.y)
        val pos3 = Point(pos.x, pos.y + 1)
        val pos4 = Point(pos.x - 1, pos.y)
        val pos5 = Point(pos.x, pos.y - 1)
        val list = List(pos2, pos3, pos4, pos5).filter(x => goodHit(x))
        if(list.nonEmpty) {
          list.head
        }
        else {
          pos
        }

      }
    }
    else {
      shoot(goodHits, random)
    }

  }

  /**
    * Allows the players to play one by one
    * @param player2 the player whose ships are hit
    * @param random the random
    * @return if the hit is good a copy of the player 1 with the new list of placed hits
    */
  override def hit(player2: Player, random: Random): Player = {
    // if the player 1 didn't loose
    if (!this.isFinished(ships )) {
      // Creates the shot
      val pos = shoot(this.goodHits, random)
      // if the hit is in the grid and if the player didn't hit here before
        if (goodHit(pos)) {
          // if one shit is hit, changes the player 2 list of ships
          val newPlayer2 = this.hitShip( player2, pos)
          // Creates a new player with the new list of placed hits
          val newPlayer1 = this.copyPlayerPlacedHits( placedHits :+ pos )
          // Now is the player 2 turn to play
          newPlayer2.hit( newPlayer1, random )
        }
          // if the hit is not good
        else {
          // The player 1 hits again
          this.hit( player2, random )
        }
    }
    // if the player looses
    else {
      Output.printWinner(player2.name)
      // The player 2 wins the game
      player2
    }
  }

}
