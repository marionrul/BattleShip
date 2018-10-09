package game

import ship._
import player._

case class Grid()

  object Grid {

    /**
      * displays the grid 1 of the player, with all his ships and the positions hit by the other player
      *
      * @param x the first x coordinate
      * @param y the first y coordinate
      * @param player the player
      */
    def displayGrid1(x: Int, y: Int, player: Player, player2: Player): Unit = {
      if (x == 10 && y == 10) {
        val cell = existsBoats(x, y, player, player2)
        println(cell)
      }
      else if (x < 10) {
        val cell = existsBoats(x, y, player, player2)
        print(cell)
        displayGrid1(x + 1, y, player, player2)
      }
      else {
        val cell = existsBoats(x, y, player, player2)
        println(cell)
        displayGrid1(1, y + 1, player, player2)
      }
    }

    /**
      * checks if there is a ship or a ship hit in the coordinates
      *
      * @param x the x coordinate
      * @param y the y coordinate
      * @param player the player
      * @return S if there is a ship in the coordinates, X if there is a hit ship in the coordinates, H if there is a placed hit in the coordinates, O otherwise
      */
    def existsBoats(x: Int, y: Int, player: Player, player2 : Player): String = {
      // Find the ships where the positions corresponds to x and y
      val ship: Option[Option[Point]] = player.ships.map(ship => ship.getPos.find(point => point.x == x && point.y == y)).find(point => point.isDefined)
      // Finds the list of points that where placed by the player 2 on the coordinates
      val hit: List[Point] = player2.placedHits.dropWhile(point => point.x != x || point.y != y)
      // Finds the ships where the hit positions corresponds to x and y
      val shipHit: Option[Option[Point]] = player.ships.map(ship => ship.getHits.find(point => point.x == x && point.y == y)).find(point => point.isDefined)
      // if the player 2 placed a hit here
        if(hit.nonEmpty) {
          // if this hit touched a ship
          if (shipHit.isDefined) {
            val cell = Console.RED + "X" + Console.RESET
            cell
          }
          else {
            val cell = Console.BLUE + "H" + Console.RESET
            cell
          }
        }
          // if a ship is in this position
        else if (ship.isDefined) {{
          val cell = Console.GREEN + "S" + Console.RESET
          cell
        }
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
      * @param player the player that sees the grid
      * @param player2 the other player
      */
    def displayGrid2(x: Int, y: Int, player: Player, player2 : Player): Unit = {
      if (x == 10 && y == 10) {
        val cell = existsPlacedHit(x, y, player, player2)
        println(cell)
      }

      else if (x < 10) {
      val cell = existsPlacedHit(x, y, player, player2)
        print(cell)
        displayGrid2(x + 1, y, player, player2)
      }
      else {
        val cell = existsPlacedHit(x, y, player, player2)
        println(cell)
        displayGrid2(1, y + 1, player, player2)
      }
  }


    /**
      * checks if there is a placed hit in the coordinate and if the placed hit actually hit a ship
      * @param x the x coordinate
      * @param y the y coordinate
      * @param player1 the player that placed the hit
      * @param player2 the player that is hit
      * @return H if there is a placed hit in the coordinates, X if this placed hit hit a ship, O otherwise
      */
    def existsPlacedHit(x: Int, y: Int, player1: Player, player2: Player): String = {
      // Finds the list of points that where placed in the coordinates
      val hit: List[Point] = player1.placedHits.dropWhile(point => point.x != x || point.y != y)
      // Finds the ships that where hits in the coordinates
      val shipHit: Option[Option[Point]] = player2.ships.map(ship => ship.getHits.find(point => point.x == x && point.y == y)).find(point => point.isDefined)
      // if the player placed a hit in this position
      if(hit.nonEmpty) {
        // if this hit touched a ship
        if(shipHit.isDefined) {
          val cell = Console.RED + "X" + Console.RESET
          cell
        }
        else {
          val cell = Console.BLUE + "H" + Console.RESET
          cell
        }
      }
      else {
        val cell = "O"
        cell
      }
    }


  }
