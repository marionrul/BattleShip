import player._
import ship._
import game._

object Main extends App {

 val mode = Helper.EntryModeGame()
  mode match {
    case 1 => val name1 = Helper.EntryName("player 1")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val name2 = Helper.EntryName("player 2")
      val player2 = HumanPlayer(name2, Nil, Nil)
      play(player1, player2)
    case 2 => val name1 = Helper.EntryName("player 1")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val player2 = AIBeginner("AI Beginner", Nil, Nil, Nil)
      play(player1, player2)
  }

 def play(player1: Player, player2: Player) : Unit = {

  // The players place the ships
  println("The battleship is about to start")
  println(player1.name + " your turn")
  val newPlayer1 = player1.createFleet(Ship.typeShipList, player1)
  println(newPlayer1.name + " this is the grid with your ships")
  Grid.displayGrid1(1, 1, newPlayer1)

  println(player2.name + " your turn")
  val newPlayer2 = player2.createFleet(Ship.typeShipList, player2)
  println(newPlayer2.name + " this is the grid with your ships")
  Grid.displayGrid1(1, 1, newPlayer2)


  // The players play
  val winner = newPlayer1.hit(newPlayer2)

  }



}
