package ship

import io.AnsiColor._

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
      *
      * @param row the row of the grid
      * @return the grid of the battleship
      */
    def createRowGrid(row: Int): Grid = {
      if (row <= 10) {
        Grid(createColumnsGrid(row, 0) :: createRowGrid(row + 1).getCells)
      }
      else {
        Grid(Nil)
      }
    }

    /**
      * creates all the columns of the grid
      *
      * @param row the row of the grid
      * @param col the column of the grid
      * @return a list of cells that represents a row of the grid
      */
    def createColumnsGrid(row: Int, col: Int): List[String] = {
      if (col <= 10) {
        val cell = "O"
        cell :: createColumnsGrid(row, col + 1)
      }
      else {
        Nil
      }
    }

    /**
      * displays the grid 1 of the player, with all his ships and the positions hit by the other player
      *
      * @param x      the first x coordinate
      * @param y      the first y coordinate
      * @param player the player
      */
    def displayGrid1(x: Int, y: Int, player: Player): Unit = {
      //val player : Option[Point] = player.placedHits.find(player => player.x == x && player.y == y)
      //if(player.isDefined) {
      //if(player.placedHits.dropWhile(point => point.x == x && point.y == y).nonEmpty )
      if (x == 10 && y == 10) {
        val cell = existsBoats(x, y, player)
        println(cell)
      }
      else if (x < 10) {
        val cell = existsBoats(x, y, player)
        print(cell)
        displayGrid1(x + 1, y, player)
      }
      else {
        val cell = existsBoats(x, y, player)
        println(cell)
        displayGrid1(1, y + 1, player)
      }
    }

    /**
      * checks if there is a ship or a ship hit on the coordinates
      *
      * @param x      the x coordinate
      * @param y      the y coordinate
      * @param player the player
      * @return S if there is a ship on the coordinates, X if there is a hit ship on the coordinates, O otherwise
      */
    def existsBoats(x: Int, y: Int, player: Player): String = {
      val ship: Option[Option[Point]] = player.ships.map(ship => ship.getPos.find(point => point.x == x && point.y == y)).find(point => point.isDefined)
      val shipHit: Option[Option[Point]] = player.ships.map(ship => ship.getHits.find(point => point.x == x && point.y == y)).find(point => point.isDefined)
      if (ship.isDefined) {
        val cell = Console.GREEN + "S" + Console.RESET
        cell
      }
      else if (shipHit.isDefined) {
        val cell = Console.RED + "X" + Console.RESET
        cell
      }
      else {
        val cell = "O"
        cell
      }
    }

    /**
      * displays the grid 2 of the player, with all the positions he placed
      *
      * @param x the first x coordinate
      * @param y the first y coordinate
      * @param player the player
      */
    def displayGrid2(x: Int, y: Int, player: Player): Unit = {
      if (x == 10 && y == 10) {
        val cell = existsPlacedHit(x, y, player)
        println(cell)
      }

      else if (x < 10) {
      val cell = existsPlacedHit(x, y, player)
        print(cell)
        displayGrid2(x + 1, y, player)
      }
      else {
        val cell = existsPlacedHit(x, y, player)
        println(cell)
        displayGrid2(1, y + 1, player)
      }
  }


    /**
      * checks if there is placed hit on the coordinate
      * @param x the x coordinate
      * @param y the y coordinate
      * @param player the player
      * @return X if there is a placed hit on the coordinates, O otherwise
      */
    def existsPlacedHit(x: Int, y: Int, player: Player): String = {
      val hit: List[Point] = player.placedHits.dropWhile(point => point.x != x || point.y != y)
      if(hit.nonEmpty) {
        val cell = Console.RED + "X" + Console.RESET
        cell
      }
      else {
        val cell = "O"
        cell
      }
    }


  }
