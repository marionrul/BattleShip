package player

import game.Input
import ship._

import scala.util.Random

trait AI extends Player {

  /**
    * For the AI Hard, so it can remember the goodHits placed
    */
  val goodHits: List[Point]

  /**
    * Creates a new player
    * @param goodHits a new list of goodHits placed
    * @return a new player with the new lis of goodHits
    */
  def copyPlayerGoodHits(goodHits: List[Point]): Player

  /**
    * Allows the player to create his fleet
    * @param typeShips the list of ships the player has to place
    * @param player the player that creates the fleet
    * @param random the random
    * @return a new player with his new fleet
    */
  override def createFleet(typeShips: List[TypeShip], player: Player, random: Random): Player = {
    if (typeShips.isEmpty) {
      player
    }
    else {
      // Starts with the first ship
      val firstTypeShip: TypeShip = typeShips.head
      // Generates the coordinates and the direction randomly
      val (x,y) = Input.EntryParametersAI(random, random)
      val dir = Input.EntryDirectionAI(random)
      // if the coordinates are in the grid
      if (Ship.canPlace( dir, x, y, firstTypeShip.len )) {
        // Creates the ship's list of positions
        val listPos = Ship.createList( dir, x, y, firstTypeShip )
        // Creates the ship
        val ship = Ship( listPos, Nil, firstTypeShip )
        // Creates the list of positions of the player ships
        var shipsPosition = player.ships.flatMap( x => x.getPos )
        // Creates the list of positions of the ship
        var points = ship.getPos
        // if the list of ships of the player is empty
        if (player.ships.isEmpty) {
          val secondTypeShip = typeShips.tail
          // Creates a new player with the new list of ships
          val newPlayer = player.copyPlayerShips( ships = ship :: Nil )
          // Creates the fleet with the second ship
          createFleet(secondTypeShip, newPlayer, random)
        }
        // if the list of ships of the player is not empty and that the positions are not occupied
        else if (Ship.areNotOccupied(shipsPosition, points )) {
          val secondTypeShip = typeShips.tail
          // Creates a new player with the new list of ships
          val newPlayer = player.copyPlayerShips( ships = player.ships :+ ship )
          // Creates the fleet with the second ship
          createFleet(secondTypeShip, newPlayer, random)
        }
        // if the positions are already occupied
        else {
          // Starts again with the first ship
          createFleet(typeShips, player, random)
        }
      }
      // if the coordinates aren't in the grid
      else {
        // Starts again with the first ship
        createFleet( typeShips, player, random )
      }

    }

  }


}


