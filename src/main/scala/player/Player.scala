package ship

import scala.annotation.tailrec

case class Player(name: String, ships: List[Ship], placedHits: List[Point]) {

  /**
    *
    * @return all the ships of the player
    */
  def getShips: List[Ship] = {
    ships
  }

  /**
    *
    * @return all the hits the payer placed
    */
  def getPlacedHits: List[Point] = {
    placedHits
  }

  /**
    *
    * @return the name of the player
    */
  def getName: String = {
    name
  }

  /**
    *
    * @param ship the ship the player wants to place
    * @return a copy of the player with the new list of ships
    */
  def placeShip(ship: Ship): Player = {
    // get all the positions of the player's ships
    var shipsPosition = ships.map(x => x.getPos).flatMap(x => x)
    // get all the positions of the ship
    var  points = ship.getPos
    if(ships.isEmpty) this.copy(ships = ship::Nil)
    // if the ship's positions are not occupied, we can add the ship
    else if (Player.areNotOccupied(shipsPosition, points)){
      this.copy(ships = ships:+ship)
    }
    else {
      this.copy(ships = ships)
    }
  }

}

object Player {

  /**
    *
    * @param typeShips the list of ships the player has to place
    * @param player the player that creates his fleet
    * @return a new player with his new fleet
    */
  @tailrec
  def createFleet(typeShips: List[TypeShip], player: Player):  Player = {

    if(typeShips.isEmpty) {
      player
    }
    else {
      val firstTypeShip: TypeShip = typeShips.head
      println("The ship to place is" + " " + firstTypeShip.getName + " " + "with the length" + " " + firstTypeShip.getLen)
      println("Please enter its parameters")
      val x = Helper.EntryParameters("x")
      val y = Helper.EntryParameters("y")
      val dir = Helper.EntryDirection()
      if(Ship.canPlace(dir, x, y, firstTypeShip.len)) {
        val liste = Ship.createList(dir, x, y, firstTypeShip)
        val ship = Ship(liste, Nil, firstTypeShip)
        val newPlayer = player.placeShip(ship)
        val secondTypeShip = typeShips.tail
        createFleet(secondTypeShip, newPlayer)
      }
      else {
        println("The ship is outside the grid, place again")
        createFleet(typeShips, player)
      }

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
    * @param ships the player's list of ships
    * @return true if all the ships are sunk
    */
  def isFinished(ships: List[Ship]): Boolean = {
    if(ships.exists(ship => !Ship.isSunk(ship))) false
    else true
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
    * checks if one ship of the player is hit
    * @param ships the player's list of ships
    * @param pos the position we want to hit
    * @return true if the position correspond to a ship's position, false otherwise
    */
  @tailrec
  def oneShipIsHit(ships: List[Ship], pos: Point): Boolean = {
    if(ships.isEmpty) false
    else if(Ship.isHit(ships.head, pos)) {
      println("The ship " + ships.head.getType.getName + " is hit")
      true
    }
    else oneShipIsHit(ships.tail, pos)
  }

  /**
    *
    * @param player the player that was hit
    * @param pos the position he was hit
    * @return if one of his ship was hit, the player with the list of hits of the ship modified
    */
  def hitShip(player: Player, pos: Point): Player = {
    if(oneShipIsHit(player.getShips, pos)) {
      player.copy(ships = player.ships.map(x => Ship.hitShip(x, pos)))
    }
    else {
      player
    }
  }


}
