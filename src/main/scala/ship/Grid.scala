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

    def displayGrid(listGrid: List[List[String]], x: Int, y: Int): Unit = {
    if(x == 10 && y==10) {
      val cell = "O"
      println(cell)
    }
    else if(y==10) {
      val cell = "O"
      println(cell)
      displayGrid(listGrid, x+1, 1)
    }
    else {
      val cell = "O"
      print(cell)
      displayGrid(listGrid, x, y+1)
    }
    }

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
