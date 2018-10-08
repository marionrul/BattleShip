package player
import game.{Grid, Helper}
import ship._

import scala.util.Random

case class AIHard(private val _name: String, private val _ships: List[Ship], private val _placedHits: List[Point], private val _goodHits: List[Point]) extends AI {

  override val name = _name
  override val ships = _ships
  override val placedHits = _placedHits
  override val goodHits = _goodHits

  override def copyPlayerGoodHits(goodHits: List[Point]): Player = {
    this.copy( _goodHits = goodHits )
  }

  override def copyPlayerName(name: String): Player = {
    this.copy( _name = name )
  }

  override def copyPlayerShips(ships: List[Ship]): Player = {
    this.copy( _ships = ships )
  }

  override def copyPlayerPlacedHits(placedHits: List[Point]): Player = {
    this.copy( _placedHits = placedHits )
  }

  /**
    *
    * @param typeShips the list of ships the player has to place
    * @param player    the player that creates his fleet
    * @return a new player with his new fleet
    */
  override def createFleet(typeShips: List[TypeShip], player: Player, random: Random): Player = {
    if (typeShips.isEmpty) {
      player
    }
    else {
      val firstTypeShip: TypeShip = typeShips.head
      println( "The ship to place is" + " " + firstTypeShip.getName + " " + "with the length" + " " + firstTypeShip.getLen )
      println( "Please enter its parameters" )
      val x = Helper.EntryParametersAI(random, random)._1
      val y = Helper.EntryParametersAI(random, random)._2
      val dir = Helper.EntryDirectionAI(random)
      if (Ship.canPlace( dir, x, y, firstTypeShip.len )) {
        val liste = Ship.createList( dir, x, y, firstTypeShip )
        val ship = Ship( liste, Nil, firstTypeShip )
        val newPlayer = player.placeShip( ship )
        val secondTypeShip = typeShips.tail
        createFleet( secondTypeShip, newPlayer, random )
      }
      else {
        println( "The ship is outside the grid, place again" )
        createFleet( typeShips, player, random )
      }

    }
  }

  /**
    *
    * @param player the player that was hit
    * @param pos    the position he was hit
    * @return if one of his ship was hit, the player with the list of hits of the ship modified
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
    *
    * @param pos     the position that the player1 wants to hit
    * @return true if the position is right and if the player didn't hit here before, false otherwise
    */
  def goodHit(pos: Point): Boolean = {
    if (!this.placedHits.contains(pos) && Point.isGood(pos.x, pos.y)) true
    else {
      //println("You have already made this shot or the coordinate are not good")
      false
    }
  }

  def shoot(goodHits: List[Point], random: Random) : Point = {
    val (x, y) = Helper.EntryParametersAI( random, random )
    val pos = Point( x, y )
    if(goodHit(pos)) {
      if (goodHits.isEmpty) {
        pos
      }
      else {
        val pos = goodHits.head
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

  override def hit(player2: Player, random: Random): Player = {
    // if the player 1 didn't loose
    if (!this.isFinished(ships )) {
      //println( name + " these are our grids " )
      // displays the first grid of the player 1
      //Grid.displayGrid1( 1, 1, this )
     // println( " " )
      // displays the second grid of the player 1
      //Grid.displayGrid2( 1, 1, this, player2 )
     // println(name + " Please enter the shot you want to make" )
      val pos = shoot(this.goodHits, random)
        if (goodHit(pos)) {
          println("HIT GOOD HIT")
          // if the player2 has one ship hit, changes him
          val newPlayer2 = this.hitShip( player2, pos)
          val newPlayer1 = this.copyPlayerPlacedHits( placedHits :+ pos )
          // now is the player2 turn to play
          newPlayer2.hit( newPlayer1, random )
        }
        else {
          println("NOT GOOD HIT IN HIT")
          // if the hit is not good, the player 1 hits again
          this.hit( player2, random )
        }
    }
    else {
      println( "The player " + player2.name + " won" )
      player2
    }
  }

}

object AIHard {


}