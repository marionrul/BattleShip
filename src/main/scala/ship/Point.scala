package ship

case class Point(val x : Int, val y : Int) {



  /**
    *
    * @return
    */
  def isGood: Boolean = {
    if((x>0) && (x<=10) && (y>0) && (y<=10)) true
    else false
  }

}