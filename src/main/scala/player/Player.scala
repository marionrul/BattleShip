package player

import ship._
import scala.util.Random

trait Player {

  val name: String
  val ships: List[Ship]
  val placedHits: List[Point]

  def copyPlayerName(name: String): Player
  def copyPlayerShips(ships: List[Ship]): Player
  def copyPlayerPlacedHits(placedHits: List[Point]): Player

  def createFleet(typeShips: List[TypeShip], player: Player, random: Random):  Player
  def hit(player2: Player, random: Random): Player

  /**
    *
    * @param ships the player's list of ships
    * @return true if all the ships are sunk
    */
  def isFinished(ships: List[Ship]): Boolean = {
    if(ships.exists(ship => !Ship.isSunk(ship))) false
    else true
  }

  /**
    *
    * @param ship the ship the player wants to place
    * @return a copy of the player with the new list of ships
    */
  def placeShip(ship: Ship): Player = {
    // get all the positions of the player's ships
    var shipsPosition = ships.map( x => x.getPos ).flatMap( x => x )
    // get all the positions of the ship
    var points = ship.getPos
    if (ships.isEmpty) this.copyPlayerShips( ships = ship :: Nil )
    // if the ship's positions are not occupied, we can add the ship
    else if (Ship.areNotOccupied( shipsPosition, points )) {
      this.copyPlayerShips( ships = ships :+ ship )
    }
    else {
      this.copyPlayerShips( ships = ships )
    }
  }



}
