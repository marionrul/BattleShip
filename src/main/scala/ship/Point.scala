package ship

case class Point(x : Int, y : Int) {

  /**
    *
    * @return the x coordinate of the point
    */
  def getX: Int = {
    x
  }

  /**
    *
    * @return the y coordinate of the point
    */
  def getY: Int = {
    y
  }

}

object Point {

  /**
    *
    * @return true if the coordinates x and y are in the grid, false otherwise
    */
  def isGood(x: Int, y: Int): Boolean = {
    if((x>0) && (x<=10) && (y>0) && (y<=10)) true
    else false
  }

}


