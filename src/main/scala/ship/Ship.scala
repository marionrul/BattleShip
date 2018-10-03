package ship

case class Ship(private val pos: List[Point], private val hits: List[Point], private val typeS: TypeShip) {

  /**
    *
    * @return the ship's list of positions
    */
  def getPos: List[Point] = {
    return pos
  }

  /**
    *
    * @return the ship's list of positions that were hit
    */
  def getHits: List[Point] = {
    return hits
  }

  /**
    *
    * @return true if the ship is sink
    */
  def isFinished: Boolean = {
    pos.length == hits.length
  }


}

case class TypeShip(name: String, len: Int)

object Ship {

  val vertical = "vertical"
  val horizontal = "horizontal"
  val carrier: TypeShip = TypeShip("Carrier", 5)
  val battleship: TypeShip = TypeShip("Battleship", 4)
  val submarine: TypeShip = TypeShip("Submarine", 3)
  val cruiser: TypeShip = TypeShip("Cruiser", 3)
  val destroyer: TypeShip = TypeShip("Destroyer", 2)

  /**
    *
    * @param dir the direction of the ship
    * @param x the x coordinate of the ship
    * @param y the y coordinate of the ship
    * @param len the length of the ship
    * @return a list of positions according to the parameters
    */
  def createList(dir: String, x: Int, y: Int, len: Int) : List[Point] = {
    if(len>0) {
      dir match {
        case Ship.horizontal => new Point(x,y) :: createList(dir, x+1, y, len-1)
        case Ship.vertical => new Point(x,y) :: createList(dir, x, y+1, len-1)
      }
    }
    else {
      Nil
    }
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
    *
    * @param ship a ship
    * @param pos the position the player wants to hit
    * @return if the ship is hit, a copy of the shit with the new list of positions hit
    */
  def hitShip(ship: Ship, pos:Point): Ship = {
    if(isHit(ship, pos)) {
     ship.copy(hits = ship.getHits:+pos)
    }
    else ship.copy(hits = ship.getHits)
  }





}