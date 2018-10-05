import player.Player
import ship.Ship
import game.{Grid, Game}

object Main extends App {

  val player1 = Player("Marion", Nil, Nil)
  val player2 = Player("Achraf", Nil, Nil)

  // The players place the ships
  println("The battleship is about to start")
  println(player1.getName + " your turn")
  val newPlayer1 = Player.createFleet(Ship.typeShipList, player1)
  println(newPlayer1.getName + " this is the grid with your ships")
  Grid.displayGrid1(1, 1, newPlayer1)

  println(player2.getName + " your turn")
  val newPlayer2 = Player.createFleet(Ship.typeShipList, player2)
  println(newPlayer2.getName + " this is the grid with your ships")
  Grid.displayGrid1(1, 1, newPlayer2)


  // The players play
  val winner = Game.hit(newPlayer1, newPlayer2)




}
