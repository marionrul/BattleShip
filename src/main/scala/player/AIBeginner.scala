package player

import game._
import ship._

import scala.util.Random

case class AIBeginner(private val _name: String, private val _ships: List[Ship], private val _placedHits: List[Point], private val _goodHits: List[Point]) extends AI {

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
    * Allows the player to create his fleet
    * @param typeShips the list of ships the player has to place
    * @param player the player that creates the fleet
    * @param random the random
    * @return a new player with his new fleet
    */
  override def createFleet(typeShips: List[TypeShip], player: Player, random: Random): Player = {
    if (typeShips.isEmpty) {
      player
    }
    else {
      // Starts with the first ship
      val firstTypeShip: TypeShip = typeShips.head
      // Generates the coordinates and the direction randomly
      val (x,y) = Helper.EntryParametersAI(random, random)
      val dir = Helper.EntryDirectionAI(random)
      // if the coordinates are in the grid
      if (Ship.canPlace( dir, x, y, firstTypeShip.len )) {
        // Creates the ship's list of positions
        val listPos = Ship.createList( dir, x, y, firstTypeShip )
        // Creates the ship
        val ship = Ship( listPos, Nil, firstTypeShip )
        // Creates the list of positions of the player ships
        var shipsPosition = player.ships.flatMap( x => x.getPos )
        // Creates the list of positions of the ship
        var points = ship.getPos
        // if the list of ships of the player is empty
        if (player.ships.isEmpty) {
          val secondTypeShip = typeShips.tail
          // Creates a new player with the new list of ships
          val newPlayer = player.copyPlayerShips( ships = ship :: Nil )
          // Creates the fleet with the second ship
          createFleet(secondTypeShip, newPlayer, random)
        }
        // if the list of ships of the player is not empty and that the positions are not occupied
        else if (Ship.areNotOccupied(shipsPosition, points )) {
          val secondTypeShip = typeShips.tail
          // Creates a new player with the new list of ships
          val newPlayer = player.copyPlayerShips( ships = player.ships :+ ship )
          // Creates the fleet with the second ship
          createFleet(secondTypeShip, newPlayer, random)
        }
        // if the positions are already occupied
        else {
          // Starts again with the first ship
          createFleet(typeShips, player, random)
        }
      }
      // if the coordinates aren't in the grid
      else {
        // Starts again with the first ship
        createFleet( typeShips, player, random )
      }

    }

  }


  /**
    * Allows the players to play one by one
    * @param player2 the player whose ships are hit
    * @param random the random
    * @return if the hit is good a copy of the player 1 with the new list of placed hits
    */
  override def hit(player2: Player, random: Random): Player = {
    // if the player didn't loose
    if (!this.isFinished(ships )) {
      // Generates the shot randomly
      val (x,y) = Helper.EntryParametersAI(random, random )
      val pos = Point( x, y )
      // if one shit is hit, changes the player 2 list of ships
      val newPlayer2 = AIBeginner.hitShip( player2, pos )
      // Creates a new player with the new list of placed hits
      val newPlayer1 = this.copyPlayerPlacedHits( placedHits = placedHits :+ pos )
      // Now is the player 2 turn to play
      newPlayer2.hit(newPlayer1, random)
    }
    else {
      // if the player looses
      println( "The player " + player2.name + " won" )
      // The player 2 wins the game
      player2
    }
  }

}

  object AIBeginner {

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
