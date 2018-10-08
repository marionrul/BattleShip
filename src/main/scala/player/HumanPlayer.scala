package player

import game._
import player.HumanPlayer.hitShip
import ship._

case class HumanPlayer(private val _name: String, _ships: List[Ship], _placedHits: List[Point]) extends Player {

  override val name = _name
  override val ships = _ships
  override val placedHits = _placedHits

  override def copyPlayerName(name: String): Player = {
    this.copy(_name = name)
  }

  override def copyPlayerShips(ships: List[Ship]) : Player = {
    this.copy(_ships = ships)
  }

  override def copyPlayerPlacedHits(placedHits: List[Point]) : Player = {
    this.copy(_placedHits = placedHits)
  }

  /**
    *
    * @param ship the ship the player wants to place
    * @return a copy of the player with the new list of ships
    */
  override def placeShip(ship: Ship): Player = {
    // get all the positions of the player's ships
    var shipsPosition = ships.map(x => x.getPos).flatMap(x => x)
    // get all the positions of the ship
    var  points = ship.getPos
    if(ships.isEmpty) this.copyPlayerShips(ships = ship::Nil)
    // if the ship's positions are not occupied, we can add the ship
    else if (Ship.areNotOccupied(shipsPosition, points)){
      this.copyPlayerShips(ships = ships:+ship)
    }
    else {
      this.copyPlayerShips(ships = ships)
    }
  }

  /**
    *
    * @param typeShips the list of ships the player has to place
    * @param player the player that creates his fleet
    * @return a new player with his new fleet
    */
  override def createFleet(typeShips: List[TypeShip], player: Player): Player = {
    if(typeShips.isEmpty) {
      player
    }
    else {
      val firstTypeShip: TypeShip = typeShips.head
      println("The ship to place is" + " " + firstTypeShip.getName + " " + "with the length" + " " + firstTypeShip.getLen)
      println("Please enter its parameters")
      val x = Helper.EntryParameters("x")
      val y = Helper.EntryParameters("y")
      val dir = Helper.EntryDirection()
      if(Ship.canPlace(dir, x, y, firstTypeShip.len)) {
        val liste = Ship.createList(dir, x, y, firstTypeShip)
        val ship = Ship(liste, Nil, firstTypeShip)
        val newPlayer = player.placeShip(ship)
        val secondTypeShip = typeShips.tail
        createFleet(secondTypeShip, newPlayer)
      }
      else {
        println("The ship is outside the grid, place again")
        createFleet(typeShips, player)
      }

    }
  }

  /**
    *
    * @param ships the player's list of ships
    * @return true if all the ships are sunk
    */
  override def isFinished(ships: List[Ship]): Boolean = {
    if(ships.exists(ship => !Ship.isSunk(ship))) false
    else true
  }

  /**
    * The function that allows the players to play one by one
    * @param player1 the player that makes the hit
    * @param player2 the player whose ships are hit
    * @return if the hit is good a copy of the player 1 with the new list of placed hits
    */
  override def hit(player2: Player): Player = {
    // if the player 1 didn't loose
    if(!this.isFinished(this.ships)) {
      println(this.name + " these are our grids ")
      // displays the first grid of the player 1
      Grid.displayGrid1(1, 1, this)
      println(" ")
      // displays the second grid of the player 1
      Grid.displayGrid2(1, 1, this, player2)
      println(this.name + " Please enter the shot you want to make")
      val x = Helper.EntryParameters("x")
      val y = Helper.EntryParameters("y")
      val pos = Point(x, y)
      if (HumanPlayer.goodHit(this, pos)) {
        // if the player2 has one ship hit, changes him
        val newPlayer2 = hitShip(player2, pos)
        val newPlayer1 = this.copyPlayerPlacedHits(placedHits :+ pos)
        // now is the player2 turn to play
        newPlayer2.hit(newPlayer1)
      }
      else {
        // if the hit is not good, the player 1 hits again
        this.hit(player2)
      }
    }
    else {
      println("The player " + player2.name + " won")
      player2
    }
  }


}

object HumanPlayer {


  /**
    *
    * @param player1 the player that makes the hit
    * @param pos     the position that the player1 wants to hit
    * @return true if the position is right and if the player didn't hit here before, false otherwise
    */
  def goodHit(player1: Player, pos: Point): Boolean = {
    if (!player1.placedHits.contains(pos) && Point.isGood(pos.x, pos.y)) true
    else {
      println("You have already made this shot or the coordinate are not good")
      false
    }
  }

  /**
    *
    * @param player the player that was hit
    * @param pos the position he was hit
    * @return if one of his ship was hit, the player with the list of hits of the ship modified
    */
  def hitShip(player: Player, pos: Point): Player = {
    if(Ship.oneShipIsHit(player.ships, pos)) {
      player.copyPlayerShips(player.ships.map(x => Ship.hitShip(x, pos)))
    }
    else {
      player
    }
  }


}
