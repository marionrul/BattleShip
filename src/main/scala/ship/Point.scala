package ship

case class Point(val x : Int, val y : Int)

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


