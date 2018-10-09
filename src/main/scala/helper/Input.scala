package helper

import ship._
import scala.annotation.tailrec
import scala.util.Random
import java.io._

object Input {

  /**
    * Gets the name of the player
    * @return the name entered
    */
  def EntryName(player: String): String = {
    Output.chooseName(player)
    val x = scala.io.StdIn.readLine()
    x
  }

  /**
    * Gets the game mode input
    * @return the mode chosen
    */
  @tailrec
  def EntryModeGame(): Int = {
    Output.chooseGameMode()
    val x = scala.io.StdIn.readLine()
    val pattern = "(^[1-5]$)".r
    x match {
      case pattern(p) => p.toInt
      case _ => Output.invalidMode()
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
   Output.chooseCoordinate(t)
    val x = scala.io.StdIn.readLine()
    val pattern = "(^[1-9]|10$)".r
    x match {
      case pattern(p) => p.toInt
      case _ => Output.invalidCoordinate()
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
   Output.chooseDirection()
    val dir = scala.io.StdIn.readLine()
    dir match {
      case Ship.horizontal => dir
      case Ship.vertical => dir
      case _ => Output.invalidDirection()
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


}
