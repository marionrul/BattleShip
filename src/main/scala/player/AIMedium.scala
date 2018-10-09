package player

import game._
import ship._
import scala.util.Random


case class AIMedium(private val _name: String, private val _ships: List[Ship], private val _placedHits: List[Point], private val _goodHits: List[Point]) extends AI {

  /**
    * The list of good hits of the player
    */
  override val goodHits: List[Point] = _goodHits

  /**
    * The name of the player
    */
  override val name: String = _name

  /**
    * The list of ships of the player
    */
  override val ships: List[Ship] = _ships

  /**
    * The list of placed hits of the player
    */
  override val placedHits: List[Point] = _placedHits

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
    * Checks if a hit is good
    * @param pos the position that is hit
    * @return true if the position is right and if the player didn't hit here before, false otherwise
    */
  def goodHit(pos: Point): Boolean = {
    if (!this.placedHits.contains(pos) && Point.isGood(pos.x, pos.y)) true
    else false
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
      // Generates the shot randomly
      val (x,y) = Input.EntryParametersAI(random, random)
      val pos = Point(x, y)
      // if the hit is in the grid and if the player didn't hit here before
      if (this.goodHit(pos)) {
        // if one shit is hit, changes the player 2 list of ships
        val newPlayer2 = AIMedium.hitShip(player2, pos)
        // Creates a new player with the new list of placed hits
        val newPlayer1 = this.copyPlayerPlacedHits(placedHits :+ pos)
        // Now is the player 2 turn to play
        newPlayer2.hit(newPlayer1, random)
      }
      else {
        // if the hit is not good, the player 1 hits again
        this.hit(player2, random)
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

object AIMedium {

  /**
    * Hits a ship
    * @param player the player that was hit
    * @param pos the position he was hit on
    * @return if one of the player's ships is hit, the player with the new list of hits of the ship
    */
  def hitShip(player: Player, pos: Point): Player = {
    if (Ship.oneShipIsHit( player.ships, pos )) {
      player.copyPlayerShips( player.ships.map( x => Ship.hitShip( x, pos ) ) )
    }
    else {
      player
    }
  }

}