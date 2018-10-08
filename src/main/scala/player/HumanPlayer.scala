package player

import game._
import ship._
import scala.util.Random


case class HumanPlayer(private val _name: String, _ships: List[Ship], _placedHits: List[Point]) extends Player {

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
    * Creates a new player
    * @param name a new name
    * @return a new player with the new name
    */
  override def copyPlayerName(name: String): Player = {
    this.copy(_name = name)
  }

  /**
    * Creates a new player
    * @param ships a new list of ships
    * @return a new player with the new list of ships
    */
  override def copyPlayerShips(ships: List[Ship]) : Player = {
    this.copy(_ships = ships)
  }

  /**
    * Creates a new player
    * @param placedHits a new list of placedHits
    * @return a new player with the new list of placedHits
    */
  override def copyPlayerPlacedHits(placedHits: List[Point]) : Player = {
    this.copy(_placedHits = placedHits)
  }


  /**
    * Allows the player to create his fleet
    * @param typeShips the list of ships the player has to place
    * @param player the player that creates the fleet
    * @param random the random
    * @return a new player with his new fleet
    */
  override def createFleet(typeShips: List[TypeShip], player: Player, random: Random): Player = {
    if(typeShips.isEmpty) {
      player
    }
    else {
      // Starts with the first ship
      val firstTypeShip: TypeShip = typeShips.head
      println("The ship to place is" + " " + firstTypeShip.getName + " " + "with the length" + " " + firstTypeShip.getLen)
      println("Please enter its parameters")
      // Gets the player inputs
      val x = Helper.EntryParameters("x")
      val y = Helper.EntryParameters("y")
      val dir = Helper.EntryDirection()
      // if the coordinates are in the grid
      if(Ship.canPlace(dir, x, y, firstTypeShip.len)) {
        // Creates the ship's list of positions
        val listPos = Ship.createList(dir, x, y, firstTypeShip)
        // Creates the ship
        val ship = Ship(listPos, Nil, firstTypeShip)
        // Creates the list of positions of the player ships
        var shipsPosition = player.ships.flatMap( x => x.getPos )
        // Creates the list of positions of the ship
        var points = ship.getPos
        // if the list of ships of the player is empty
        if (player.ships.isEmpty) {
          val secondTypeShip = typeShips.tail
          // Creates a new player with the new list of ships
          val newPlayer = player.copyPlayerShips(ship :: Nil )
          // Creates the fleet with the second ship
          createFleet(secondTypeShip, newPlayer, random)
        }
        // if the list of ships of the player is not empty and that the positions are not occupied
        else if (Ship.areNotOccupied(shipsPosition, points )) {
          val secondTypeShip = typeShips.tail
          // Creates a new player with the new list of ships
          val newPlayer = player.copyPlayerShips( player.ships :+ ship )
          // Creates the fleet with the second ship
          createFleet(secondTypeShip, newPlayer, random)
        }
        // if the positions are already occupied
        else {
          // Starts again with the first ship
          println("The position is already occupied, place again")
          createFleet(typeShips, player, random)
        }
      }
      // if the coordinates aren't in the grid
      else {
        // Starts again with the first ship
        println("The ship is outside the grid, place again")
        createFleet(typeShips, player, random)
      }

    }
  }

  /**
    *
    * @param pos     the position that the player1 wants to hit
    * @return true if the position is right and if the player didn't hit here before, false otherwise
    */
  def goodHit(pos: Point): Boolean = {
    if (!this.placedHits.contains(pos) && Point.isGood(pos.x, pos.y)) true
    else {
      println("You have already made this shot or the coordinate are not good")
      false
    }
  }

  /**
    * The function that allows the players to play one by one
    * @param player1 the player that makes the hit
    * @param player2 the player whose ships are hit
    * @return if the hit is good a copy of the player 1 with the new list of placed hits
    */
  override def hit(player2: Player, random: Random): Player = {
    // if the player 1 didn't loose
    if(!this.isFinished(this.ships)) {
      println(this.name + " these are our grids ")
      // displays the first grid of the player 1
      Grid.displayGrid1(1, 1, this)
      println(" ")
      // displays the second grid of the player 1
      Grid.displayGrid2(1, 1, this, player2)
      println(this.name + " Please enter the shot you want to make")
      val x = Helper.EntryParameters("x")
      val y = Helper.EntryParameters("y")
      val pos = Point(x, y)
      if (this.goodHit(pos)) {
        // if the player2 has one ship hit, changes him
        val newPlayer2 = HumanPlayer.hitShip(player2, pos)
        val newPlayer1 = this.copyPlayerPlacedHits(placedHits :+ pos)
        // now is the player2 turn to play
        newPlayer2.hit(newPlayer1, random)
      }
        // if the hit is not good
      else {
        // The player 1 hits again
        this.hit(player2, random)
      }
    }
    else {
      println("The player " + player2.name + " won")
      player2
    }
  }


}

object HumanPlayer {


  /**
    *
    * @param player the player that was hit
    * @param pos the position he was hit
    * @return if one of his ship was hit, the player with the list of hits of the ship modified
    */
  def hitShip(player: Player, pos: Point): Player = {
    if(Ship.oneShipIsHit(player.ships, pos)) {
      player.copyPlayerShips(player.ships.map(x => Ship.hitShip(x, pos)))
    }
    else {
      player
    }
  }




}
