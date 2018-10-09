import player._
import ship._
import helper._
import scala.util.Random


object Main extends App {

val random = new Random()

val mode = Input.EntryModeGame()
  mode match {
    case 1 => val name1 = Input.EntryName("player 1")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val name2 = Input.EntryName("player 2")
      val player2 = HumanPlayer(name2, Nil, Nil)
      play(player1, player2, random)
    case 2 => val name1 = Input.EntryName("player")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val player2 = AIBeginner("AI Beginner", Nil, Nil, Nil)
      play(player1, player2, random)
    case 3 => val name1 = Input.EntryName("player")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val player2 = AIMedium("AI Medium", Nil, Nil, Nil)
      play(player1, player2, random)
    case 4 => val name1 = Input.EntryName("player")
      val player1 = HumanPlayer(name1, Nil, Nil)
      val player2 = AIHard("AI Hard", Nil, Nil, Nil)
      play(player1, player2, random)
    case 5 => val player1 = AIBeginner("AI Beginner", Nil, Nil, Nil)
      val player2 = AIMedium("AI Medium", Nil, Nil, Nil)
      val player3 = AIHard("AI Hard", Nil, Nil, Nil)
      val score1 = fightAI(0, player1, player2, 0, random)
     val score2 = fightAI(0, player1, player3, 0, random)
      val score3 = fightAI(0, player2, player3, 0, random)
      val test: String = "AI Name; score; AI Name2; score2\n" +
        " AI Level Beginner; " + score1 + "; Level Medium;" + (100-score1) + "\n" +
        "AI Level Beginner;" + score2 + ";Level Hard;" + (100-score2) + "\n" +
        "AI Medium;" + score3 + ";Level Hard;" + (100-score3)
      Output.exportToCSV(test)
  }

  /**
    * Makes the players play
    * @param player1 the player 1 that plays the game
    * @param player2 the player 2 that plays the game
    * @param random the random
    * @return the winner of the game
    */
 def play(player1: Player, player2: Player, random: Random) : Player = {

  // The players place the ships
  Output.startGame()
  Output.printTurn(player1.name)
  val newPlayer1 = player1.createFleet(Ship.typeShipList, player1, random)
  Output.printGridShips(newPlayer1.name)
  Grid.displayGrid1(1, 1, newPlayer1, player2)

  Output.printTurn(player2.name)
  val newPlayer2 = player2.createFleet(Ship.typeShipList, player2, random)
   Output.printGridShips(newPlayer2.name)
  Grid.displayGrid1(1, 1, newPlayer2, newPlayer1)


  // The players play
  val winner = newPlayer1.hit(newPlayer2, random)
   winner

  }

  /**
    * Makes the AI fight
    * @param cpt the number of games they make
    * @param player1 the first AI that fights
    * @param player2 the second AI that fights
    * @param nbWinsPlayer1 the number of wins of the first AI
    * @param random the random
    * @return the number of wins of the first AI
    */
  def fightAI(cpt: Int, player1: Player, player2: Player, nbWinsPlayer1: Int, random: Random): Int = {
    if(cpt == 100) {
      Output.printNbWins(player1.name, nbWinsPlayer1)
      nbWinsPlayer1
    }
    else {
      val cpt2 = cpt % 2
      val winner = cpt match {
        case 0 => play(player1, player2, random)
        case _ => play(player2, player1, random)
      }

      winner.name match {
        case player1.name => fightAI(cpt+1, player1, player2, nbWinsPlayer1+1, random)
        case player2.name => fightAI(cpt+1, player1, player2, nbWinsPlayer1, random)
      }

    }
  }



}
