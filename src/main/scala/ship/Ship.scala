package ship

import player._

import scala.annotation.tailrec

case class Ship(private val pos: List[Point], private val hits: List[Point], private val typeS: TypeShip) {

  /**
    *
    * @return the ship's list of positions
    */
  def getPos: List[Point] = {
    pos
  }

  /**
    *
    * @return the ship's list of positions that were hit
    */
  def getHits: List[Point] = {
    hits
  }

  /**
    *
    * @return the ship's type
    */
  def getType: TypeShip = {
    typeS
  }
}

object Ship {

  val vertical = "v"
  val horizontal = "h"
  val carrier: TypeShip = TypeShip("Carrier", 5)
  val battleship: TypeShip = TypeShip("Battleship", 4)
  val submarine: TypeShip = TypeShip("Submarine", 3)
  val cruiser: TypeShip = TypeShip("Cruiser", 3)
  val destroyer: TypeShip = TypeShip("Destroyer", 2)

  val typeShipList: List[TypeShip] = List(Ship.carrier)
  // Ship.battleship, Ship.submarine, Ship.cruiser, Ship.destroyer)

  /**
    *
    * @param dir the direction
    * @param x the x coordinate
    * @param y the y coordinate
    * @param len the length of the ship
    * @return true if we can place a ship on the grid according to these parameters
    */
  def canPlace(dir: String, x: Int, y: Int, len: Int) : Boolean = {
    if(len == 0) {
      true
    }
    else {
      Point.isGood(x, y) && (dir match {
        case "h" => Point.isGood(x = x + len - 1, y)
        case "v" => Point.isGood(x, y = y + len - 1)
        case _ => false
      })
    }

  }

  /**
    *
    * @param dir the direction of the ship
    * @param x the x coordinate of the ship
    * @param y the y coordinate of the ship
    * @param typeS the type of the ship
    * @return a list of positions according to these parameters
    */
  def createList(dir: String, x: Int, y: Int, typeS: TypeShip) : List[Point] = {
    if(canPlace(dir, x, y, typeS.getLen) && typeS.getLen > 0) {
      val newTypeShip = typeS.copy(len = typeS.len-1)
      dir match {
        case Ship.horizontal => new Point(x,y) :: createList(dir, x+1, y, newTypeShip)
        case Ship.vertical => new Point(x,y) :: createList(dir, x, y+1, newTypeShip)
      }
    }
    else {
      Nil
    }
  }

  /**
    *
    * @param positions a list of positions
    * @param pos a position we want to add
    * @return true if the position is already occupied, false otherwise
    */
  def isOccupied(positions: List[Point], pos: Point): Boolean = {
    if(positions.isEmpty) false
    else if(positions.contains(pos)) true
    else false
  }

  /**
    *
    * @param listPosShip the ship's list of positions
    * @param listPos a list of position
    * @return true if none of the ship's positions are occupied, false otherwise
    */
  @tailrec
  def areNotOccupied(listPosShip: List[Point], listPos: List[Point]): Boolean = {
    if(listPos.isEmpty) true
    else if (isOccupied(listPosShip, listPos.head)) false
    else areNotOccupied(listPosShip, listPos.tail)
  }


  /**
    *
    * @param ship a ship
    * @param position the position the player wants to hit
    * @return true if the ship is hit
    */
  def isHit(ship: Ship, position: Point): Boolean = {
    ship.getPos.contains(position)
  }

  /**
    * checks if one ship of the player is hit
    * @param ships the player's list of ships
    * @param pos the position we want to hit
    * @return true if the position correspond to a ship's position, false otherwise
    */
  @tailrec
  def oneShipIsHit(ships: List[Ship], pos: Point): Boolean = {
    if(ships.isEmpty) false
    else if(isHit(ships.head, pos)) {
      println("The ship " + ships.head.getType.getName + " is hit")
      true
    }
    else oneShipIsHit(ships.tail, pos)
  }

  /*
    *
    * @return true if the ship is sunk
    */
  def isSunk(ship: Ship): Boolean = {
    if(ship.pos.length == ship.hits.length) {
      println("The ship " + ship.getType.getName + " is sunk")
      true
    }
    else false
  }

  /**
    *
    * @param ship a ship
    * @param pos the position the player wants to hit
    * @return if the ship is hit, a copy of the ship with the new hits list
    */
  def hitShip(ship: Ship, pos:Point): Ship = {
    if(isHit(ship, pos)) {
     ship.copy(hits = ship.getHits:+pos)
    }
    else ship.copy(hits = ship.getHits)
  }

}