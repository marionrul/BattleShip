package ship

case class Ship(private val pos: List[Point], private val hits: List[Point], private val typeS: TypeShip) {

  /**
    *
    * @return
    */
  def getPos: List[Point] = {
    return pos
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
    * @param dir
    * @param x
    * @param y
    * @param len
    * @return
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
    * @return
    */
  def isFinished: Boolean = {
    pos.forall(hits.contains)
  }

  /**
    *
    * @param position
    * @return
    */
  def isHit(position: Point): Boolean = {
    pos.contains(position)
  }
}