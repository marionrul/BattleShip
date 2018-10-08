package player

import ship._

trait Player {

  val name: String
  val ships: List[Ship]
  val placedHits: List[Point]
  def placeShip(ship: Ship): Player

  def copyPlayerName(name: String): Player
  def copyPlayerShips(ships: List[Ship]): Player
  def copyPlayerPlacedHits(placedHits: List[Point]): Player

  def createFleet(typeShips: List[TypeShip], player: Player):  Player
  def hit(player2: Player): Player
  def isFinished(ships: List[Ship]): Boolean



}
