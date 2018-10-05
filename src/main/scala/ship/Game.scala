package ship

class Game(player1 : Player, player2: Player)

object Game {
  /**
    *
    * @param player1 the player that makes the hit
    * @param player2 the player whose ships are hit
    * @param pos the position that the player1 wants to hit
    * @return true if the position is right and a ship is hit, false otherwise
    */
  def goodHit(player1: Player, pos: Point) : Boolean ={
    if(!player1.getPlacedHits.contains(pos) && Point.isGood(pos.x, pos.y)) true
    else {
      println("You have already made this shot or the coordinate are not good")
      false
    }
  }

  /**
    *
    * @param player1 the player that makes the hit
    * @param player2 the player whose ships are hit
    * @param pos the position that the player1 wants to hit
    * @return if the hit is good :
    *         - a copy of the player1 with the new list of placed hits
    *         - a copy of the player2 with the new list of ships (with a new hit list for the ship hit)
    */
  def hit(player1: Player, player2: Player) : Player = {
    if(!Player.isFinished(player1.getShips)) {
      println(player1.getName + " Please enter the shot you want to make")
      val x = Helper.EntryParameters("x")
      val y = Helper.EntryParameters("y")
      val pos = Point(x, y)
      if (goodHit(player1, pos)) {
       val newPlayer2 = Player.hitShip(player2, pos)
        val newPlayer1 = player1.copy(placedHits = player1.getPlacedHits :+ pos)
        Grid.displayGrid1(1, 1, newPlayer1)
        println(" ")
        Grid.displayGrid2(1, 1, newPlayer1)
        hit(newPlayer2, newPlayer1)
        }
      else {
       hit(player1, player2)
      }
    }
     else {
      println("The player " + player1.getName + "won")
      player1
    }
        }
}
