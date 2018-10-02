package ship

case class Player(name: String, ships: List[Ship], placedHits: List[Point])

object Player {

  def isFinished: Boolean = {
    ships.forall(x => x.isFinished)
  }

  /**
    *
    * @param ships the player list of ships
    * @param ship the ship the player wants to place
    * @return the list of ships updates
    */
  def placeShip(ships: List[Ship], ship: Ship): List[Ship] = {
    var shipsPosition = ships.map(x => x.getPos).flatMap(x => x)
    var  points = ship.getPos
    if(ships.isEmpty) ship::List()
    else if (verif(shipsPosition, points)){
      ships:+ship
    }
    else {
      ships
    }
  }

  /**
    *
    * @param listPosShip
    * @param listPos
    * @return
    */
  def verif(listPosShip: List[Point], listPos: List[Point]): Boolean = {
    if(listPos.isEmpty) true //on peut la placer
    else if (!isOccupied(listPosShip, listPos.head)) false
    else verif(listPosShip, listPos.tail)
  }

  /**
    *
    * @param positions
    * @param pos
    * @return
    */
  def isOccupied(positions: List[Point], pos: Point): Boolean = {
    if(positions.isEmpty) false
    else if(positions.contains(pos)) true
    else false
  }
}
