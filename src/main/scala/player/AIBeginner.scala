package player

import game._
import player.AIBeginner.hitShip
import ship._

import scala.annotation.tailrec
import scala.util.Random

case class AIBeginner(private val _name: String, private val _ships: List[Ship], private val _placedHits: List[Point], private val _goodHits: List[Point]) extends AI {

  override val name = _name
  override val ships = _ships
  override val placedHits = _placedHits
  override val goodHits = _goodHits

  override def copyPlayerName(name: String): Player = {
    this.copy( _name = name )
  }

  override def copyPlayerShips(ships: List[Ship]): Player = {
    this.copy( _ships = ships )
  }

  override def copyPlayerPlacedHits(placedHits: List[Point]): Player = {
    this.copy( _placedHits = placedHits )
  }

  override def placeShip(ship: Ship): Player = {
    // get all the positions of the player's ships
    var shipsPosition = ships.map( x => x.getPos ).flatMap( x => x )
    // get all the positions of the ship
    var points = ship.getPos
    if (ships.isEmpty) this.copyPlayerShips( ships = ship :: Nil )
    // if the ship's positions are not occupied, we can add the ship
    else if (Ship.areNotOccupied( shipsPosition, points )) {
      this.copyPlayerShips( ships = ships :+ ship )
    }
    else {
      this.copyPlayerShips( ships = ships )
    }
  }


  /**
    *
    * @param typeShips the list of ships the player has to place
    * @param player    the player that creates his fleet
    * @return a new player with his new fleet
    */
  override def createFleet(typeShips: List[TypeShip], player: Player): Player = {
    if (typeShips.isEmpty) {
      player
    }
    else {
      val firstTypeShip: TypeShip = typeShips.head
      println( "The ship to place is" + " " + firstTypeShip.getName + " " + "with the length" + " " + firstTypeShip.getLen )
      println( "Please enter its parameters" )
      val x = Helper.EntryParametersAI( new Random(), new Random() )._1
      val y = Helper.EntryParametersAI( new Random(), new Random() )._2
      val dir = Helper.EntryDirectionAI( new Random() )
      if (Ship.canPlace( dir, x, y, firstTypeShip.len )) {
        val liste = Ship.createList( dir, x, y, firstTypeShip )
        val ship = Ship( liste, Nil, firstTypeShip )
        val newPlayer = player.placeShip( ship )
        val secondTypeShip = typeShips.tail
        createFleet( secondTypeShip, newPlayer )
      }
      else {
        println( "The ship is outside the grid, place again" )
        createFleet( typeShips, player )
      }

    }

  }

  /**
    *
    * @param ships the player's list of ships
    * @return true if all the ships are sunk
    */
  override def isFinished(ships: List[Ship]): Boolean = {
    if (ships.exists( ship => !Ship.isSunk( ship ) )) false
    else true
  }

  /**
    * The function that allows the players to play one by one
    *
    * @param player1 the player that makes the hit
    * @param player2 the player whose ships are hit
    * @return if the hit is good a copy of the player 1 with the new list of placed hits
    */
  override def hit(player2: Player): Player = {
    // if the player 1 didn't loose
    if (!this.isFinished(ships )) {
      println( name + " these are our grids " )
      // displays the first grid of the player 1
      Grid.displayGrid1( 1, 1, this )
      println( " " )
      // displays the second grid of the player 1
      Grid.displayGrid2( 1, 1, this, player2 )
      println(name + " Please enter the shot you want to make" )
      val x = Helper.EntryParametersAI( new Random(), new Random() )._1
      val y = Helper.EntryParametersAI( new Random(), new Random() )._2
      val pos = Point( x, y )
      val newPlayer2 = hitShip( player2, pos )
      val newPlayer1 = this.copyPlayerPlacedHits( placedHits = placedHits :+ pos )
      // now is the player2 turn to play
      newPlayer2.hit(newPlayer1)
    }
    else {
      println( "The player " + player2.name + " won" )
      player2
    }
  }

}

  object AIBeginner {

    /**
      *
      * @param player the player that was hit
      * @param pos    the position he was hit
      * @return if one of his ship was hit, the player with the list of hits of the ship modified
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
