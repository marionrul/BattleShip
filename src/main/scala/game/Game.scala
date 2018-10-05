package game

import player.Player
import ship.Point

class Game(player1 : Player, player2: Player) {

  /**
    *
    * @return the player 1 of the game
    */
  def getPlayer1: Player = {
    player1
  }

  /**
    *
    * @return the player 2 of the game
    */
  def getPlayer2: Player = {
    player2
  }
}

object Game {

  /**
    *
    * @param player1 the player that makes the hit
    * @param pos the position that the player1 wants to hit
    * @return true if the position is right and if the player didn't hit here before, false otherwise
    */
  def goodHit(player1: Player, pos: Point) : Boolean ={
    if(!player1.getPlacedHits.contains(pos) && Point.isGood(pos.x, pos.y)) true
    else {
      println("You have already made this shot or the coordinate are not good")
      false
    }
  }

  /**
    * The function that allows the players to play one by one
    * @param player1 the player that makes the hit
    * @param player2 the player whose ships are hit
    * @return if the hit is good a copy of the player 1 with the new list of placed hits
    */
  def hit(player1: Player, player2: Player) : Player = {
    // if the player 1 didn't loose
    if(!Player.isFinished(player1.getShips)) {
      println(player1.getName + " these are our grids ")
      // displays the first grid of the player 1
      Grid.displayGrid1(1, 1, player1)
      println(" ")
      // displays the second grid of the player 1
      Grid.displayGrid2(1, 1, player1, player2)
      println(player1.getName + " Please enter the shot you want to make")
      val x = Helper.EntryParameters("x")
      val y = Helper.EntryParameters("y")
      val pos = Point(x, y)
      if (goodHit(player1, pos)) {
        // if the player2 has one ship hit, changes him
       val newPlayer2 = Player.hitShip(player2, pos)
        val newPlayer1 = player1.copy(placedHits = player1.getPlacedHits :+ pos)
        // now is the player2 turn to play
        hit(newPlayer2, newPlayer1)
        }
      else {
        // if the hit is not good, the player 1 hits again
       hit(player1, player2)
      }
    }
     else {
      println("The player " + player2.getName + " won")
      player2
    }
        }
}
