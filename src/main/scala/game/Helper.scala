package game

import ship.Ship

import scala.annotation.tailrec

object Helper {

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
      case _ => println("Your number must be between 0 and 9")
                EntryParameters(t)
    }
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

}
