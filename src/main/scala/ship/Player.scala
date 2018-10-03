package ship

import ship.Player.isOccupied

case class Player(name: String, ships: List[Ship], placedHits: List[Point]) {

  /**
    *
    * @return all the ships of the player
    */
  def getShips: List[Ship] = {
    return ships
  }

  /**
    *
    * @return all the hits the payer placed
    */
  def getPlacedHits: List[Point] = {
    return placedHits
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
    else if (areNotOccupied(shipsPosition, points)){
      this.copy(ships = ships:+ship)
    }
    else {
      this.copy(ships = ships)
    }
  }


  /**
    *
    * @param listPosShip the ship's list of positions
    * @param listPos a list of positions
    * @return true if none of the ship's positions are occupied, false otherwise
    */
  def areNotOccupied(listPosShip: List[Point], listPos: List[Point]): Boolean = {
    if(listPos.isEmpty) true //on peut la placer
    else if (isOccupied(listPosShip, listPos.head)) false
    else areNotOccupied(listPosShip, listPos.tail)
  }
}

object Player {

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
    * @return true if all the ships are sink
    */
  def isFinished(ships: List[Ship]): Boolean = {
    if(ships.isEmpty) false
    else if(ships.head.isFinished ) true
    else (isFinished(ships.tail))
    ships.forall(x => x.isFinished)
  }

  /**
    *
    * @param ships the player's list of ships
    * @param pos the position we want to hit
    * @return true if the position correspond to a ship's position, false otherwise
    */
  def oneIsHit(ships: List[Ship], pos: Point): Boolean = {
    if(ships.isEmpty) false
    else if(Ship.isHit(ships.head, pos)) true
    else oneIsHit(ships.tail, pos)
  }

}
