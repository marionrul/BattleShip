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
  def goodHit(player1: Player, player2: Player, pos: Point) : Boolean ={
    if(player1.getPlacedHits.contains(pos)) false
    else if(Player.oneIsHit(player2.getShips, pos)) {
      true
    }
    else false
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
  def hit(player1: Player, player2: Player, pos: Point) : (Player, Player) = {
    if(goodHit(player1, player2, pos)) {
      print("Ok hit")
      (player1.copy(placedHits = player1.getPlacedHits:+pos), player2.copy(ships = player2.ships.map(x => Ship.hitShip(x, pos) )))
    }
    else {
      (player1.copy(placedHits = player1.getPlacedHits:+pos), player2)
    }
  }
}
