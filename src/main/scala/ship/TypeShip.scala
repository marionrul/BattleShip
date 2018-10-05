package ship

case class TypeShip(name: String, len: Int) {

  /**
    *
    * @return the len of the ship
    */
  def getLen: Int = {
    len
  }

  /**
    *
    * @return the name of the ship
    */
  def getName: String = {
    name
  }


}