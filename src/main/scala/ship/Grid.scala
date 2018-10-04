package ship

case class Grid(cells: List[List[String]]) {

  /**
    *
    * @return the cells of the grid
  */
  def getCells:List[List[String]] = {
    cells
  }

}

  object Grid {

  /**
    * creates the grid
    * @param row the row of the grid
    * @return the grid of the battleship
    */
  def createRowGrid(row: Int): Grid = {
    if(row<=10) {
      Grid(createColumnsGrid(row, 0) :: createRowGrid(row + 1).getCells)
    }
    else {
      Grid(Nil)
    }
  }

    def displayGrid(listGrid: List[List[String]], x: Int, y: Int, player: Player): Unit = {
      //val player : Option[Point] = player.placedHits.find(player => player.x == x && player.y == y)
      //if(player.isDefined) {
     //if(player.placedHits.dropWhile(point => point.x == x && point.y == y).nonEmpty )
     val point: Option[Option[Point]] = player.ships.map(ship => ship.getPos.find(point => point.x == x && point.y == y)).find(point => point.isDefined)
    if(x == 10 && y==10) {
      if(point.isDefined) {
        val cell = "X"
        println(Console.RED + cell + Console.WHITE)
      }
      else {
        //mettre une fonction
        val cell = "O"
        println(cell)
      }
    }
    else if(x<10) {
      if(point.isDefined) {
        val cell = "X"
        print(Console.RED + cell)
        displayGrid(listGrid, x + 1, y, player)
      }
      else {
        //mettre une fonction
        val cell = "O"
        print(cell)
        displayGrid(listGrid, x + 1, y, player)
      }
    }
    else {
      if(point.isDefined) {
        val cell = "X"
        println(Console.RED + cell)
        displayGrid(listGrid, 1, y+1, player)
      }
      else {
        //mettre une fonction
        val cell = "O"
        println(cell)
        displayGrid(listGrid, 1, y + 1, player)
      }
    }
    }
      //

  /**
    * creates all the columns of the grid
    * @param row the row of the grid
    * @param col the column of the grid
    * @return a list of cells that represents a row of the grid
    */
  def createColumnsGrid(row: Int, col: Int): List[String] = {
    if(col <= 10) {
      val cell = "O"
      cell :: createColumnsGrid(row, col+1)
    }
    else {
    Nil
    }
  }



}
