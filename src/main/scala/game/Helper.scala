package game

import ship._
import scala.annotation.tailrec
import scala.util.Random
import java.io._

object Helper {

  /**
    * Gets the name of the player
    * @return the name entered
    */
  def EntryName(player: String): String = {
    println("Please enter the " + player + " name")
    val x = scala.io.StdIn.readLine()
    x.toString()
  }

  /**
    * Gets the game mode input
    * @return the mode chosen
    */
  @tailrec
  def EntryModeGame(): Int = {
    println("Please choose your game mode between : " )
    println("1 - Human vs Human")
    println("2 - Human vs AI Beginner")
    println("3 - Human vs AI Medium")
    println("4 - Human vs AI Hard")
    println("5 - Fight of the AI")
    val x = scala.io.StdIn.readLine()
    val pattern = "(^[1-5]$)".r
    x match {
      case pattern(p) => p.toInt
      case _ => println("Your number must be between 1 and 5")
        EntryModeGame()
    }
  }

  /**
    * Gets the player input
    * @param t the name of the entry
    * @return one of the coordinate entered by the player
    */
  @tailrec
  def EntryParameters(t: String): Int = {
    println("Please enter the" + " " + t + " " + "coordinate (between 1 and 10)")
    val x = scala.io.StdIn.readLine()
    val pattern = "(^[1-9]|10$)".r
    x match {
      case pattern(p) => p.toInt
      case _ => println("Your number must be between 1 and 10")
                EntryParameters(t)
    }
  }

  /**
    * Gets the IA input
    * @param randomX the random
    * @param randomY the random
    * @return the two coordinates x and y generated randomly
    */
  def EntryParametersAI(randomX: Random, randomY: Random): (Int, Int) = {
    val x = 1 + randomX.nextInt(10)
    val y = 1 + randomY.nextInt(10)
    (x,y)
  }

  /**
    * Gets the player input
    * @return the direction entered by the player
    */
  @tailrec
  def EntryDirection(): String = {
    println("Please enter the direction of the ship ('h' for horizontal and 'v' for vertical)")
    val dir = scala.io.StdIn.readLine()
    dir match {
      case Ship.horizontal => dir
      case Ship.vertical => dir
      case _ => println("The direction must be h or v")
        EntryDirection()
    }
  }

  /**
    * Gets the IA input
    * @param randomD the random
    * @return the direction generated randomly
    */
  def EntryDirectionAI(randomD: Random): String = {
    val dir = randomD.nextInt(2)
    dir match  {
      case 0 => Ship.horizontal
      case 1 => Ship.vertical
    }
  }

  /**
    * Export the content to a CSV file
    * @param content the content of the file
    */
  def exportToCSV(content: String) : Unit = {
    val bufferedWriter = new BufferedWriter(new FileWriter("./ai_proof.csv"))
    bufferedWriter.write(content)
    bufferedWriter.flush()
    bufferedWriter.close()
  }


}
