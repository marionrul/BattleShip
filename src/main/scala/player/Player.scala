package player

import ship._
import scala.util.Random

trait Player {

  /**
    * The name of the player
    */
  val name: String

  /**
    * The list of ships of the player
    */
  val ships: List[Ship]

  /**
    * The list of placed hits of the player
    */
  val placedHits: List[Point]

  /**
    * Creates a new player
    * @param name a new name
    * @return a new player with the new name
    */
  def copyPlayerName(name: String): Player

  /**
    * Creates a new player
    * @param ships a new list of ships
    * @return a new player with the new list of ships
    */
  def copyPlayerShips(ships: List[Ship]): Player

  /**
    * Creates a new player
    * @param placedHits a new list of placedHits
    * @return a new player with the new list of placedHits
    */
  def copyPlayerPlacedHits(placedHits: List[Point]): Player

  /**
    * Allows the player to create his fleet
    * @param typeShips the list of ships the player has to place
    * @param player the player that creates the fleet
    * @param random the random
    * @return a new player with his new fleet
    */
  def createFleet(typeShips: List[TypeShip], player: Player, random: Random):  Player

  /**
    * Allows the players to play one by one
    * @param player2 the player whose ships are hit
    * @param random the random
    * @return if the hit is good a copy of the player 1 with the new list of placed hits
    */
  def hit(player2: Player, random: Random): Player

  /**
    * Tells if the game is over
    * @param ships the player's list of ships
    * @return true if all the ships are sunk
    */
  def isFinished(ships: List[Ship]): Boolean = {
    if(ships.exists(ship => !Ship.isSunk(ship))) false
    else true
  }





}
