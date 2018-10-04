package ship

class Grid(cells: List[List[Point]], cellsOccupied: List[Point]) {

  /**
    *
    * @return the cells of the grid
    */
  def getCells:List[List[Point]] = {
    cells
  }

  /**
    *
    * @return the cells occupied of the grid
    */
  def getCellsOccupied: List[Point] = {
    cellsOccupied
  }
}

  object Grid {

  /**
    * creates the grid
    * @param row the row of the grid
    * @return the grid of the battleship
    */
  def createRowGrid(row: Int): List[List[Point]] = {
    if(row<=10) {
      createColumnsGrid(row, 0) :: createRowGrid(row + 1)
    }
    Nil
  }

  /**
    * creates all the columns of the grid
    * @param row the row of the grid
    * @param col the column of the grid
    * @return a list of cells that represents a row of the grid
    */
  def createColumnsGrid(row: Int, col: Int): List[Point] = {
    if(col <= 10) {
      val cell = Point(col, row)
      cell :: createColumnsGrid(row, col+1)
    }
    else {
      Nil
    }
  }



}
