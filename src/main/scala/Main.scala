import player._
import ship._
import game._
import scala.util.Random


object Main extends App {
val random = new Random()
val mode = Helper.EntryModeGame()
  mode match {
    case 1 => val name1 = Helper.EntryName("player 1")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val name2 = Helper.EntryName("player 2")
      val player2 = HumanPlayer(name2, Nil, Nil)
      play(player1, player2, random)
    case 2 => val name1 = Helper.EntryName("player")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val player2 = AIBeginner("AI Beginner", Nil, Nil, Nil)
      play(player1, player2, random)
    case 3 => val name1 = Helper.EntryName("player")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val player2 = AIMedium("AI Medium", Nil, Nil, Nil)
      play(player1, player2, random)
    case 4 => val name1 = Helper.EntryName("player")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val player2 = AIHard("AI Hard", Nil, Nil, Nil)
      play(player1, player2, random)
    case 5 => val player1 = AIBeginner("AI Beginner", Nil, Nil, Nil)
      val player2 = AIMedium("AI Medium", Nil, Nil, Nil)
      val player3 = AIHard("AI Hard", Nil, Nil, Nil)
      val score1 = testAI(0, player1, player2, 0, random)
     val score2 = testAI(0, player1, player3, 0, random)
      val score3 = testAI(0, player2, player3, 0, random)
      val test: String = "AI Name; score; AI Name2; score2\n" +
        " AI Level Beginner; " + score1 + "; Level Medium;" + (20-score1) + "\n" +
        "AI Level Beginner;" + score2 + ";Level Hard;" + (20-score2) + "\n" +
        "AI Medium;" + score3 + ";Level Hard;" + (20-score3)
      Helper.exportToCSV(test)
  }


 def play(player1: Player, player2: Player, random: Random) : Player = {

  // The players place the ships
  println("The battleship is about to start")
  println(player1.name + " your turn")
  val newPlayer1 = player1.createFleet(Ship.typeShipList, player1, random)
  println(newPlayer1.name + " this is the grid with your ships")
  Grid.displayGrid1(1, 1, newPlayer1)

  println(player2.name + " your turn")
  val newPlayer2 = player2.createFleet(Ship.typeShipList, player2, random)
  println(newPlayer2.name + " this is the grid with your ships")
  Grid.displayGrid1(1, 1, newPlayer2)


  // The players play
  val winner = newPlayer1.hit(newPlayer2, random)
   winner

  }

  def testAI(cpt: Int, player1: Player, player2: Player, nbWinsPlayer1: Int, random: Random): Int = {
    if(cpt == 20) {
      print("The player 1 won " + nbWinsPlayer1 + " times")
      nbWinsPlayer1
    }
    else {
      val cpt2 = cpt % 2
      val winner = cpt match {
        case 0 => play(player1, player2, random)
        case _ => play(player2, player1, random)
      }

      winner.name match {
        case player1.name => testAI(cpt+1, player1, player2, nbWinsPlayer1+1, random)
        case player2.name => testAI(cpt+1, player1, player2, nbWinsPlayer1, random)
      }

    }
  }



}
