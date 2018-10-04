package ship

import scala.annotation.tailrec

object Helper {

  /**
    *
    * @param t the name of the entry
    * @return one of the coordinate entered by the player
    */
  @tailrec
  def EntryParameters(t: String): Int = {
    println("Please enter the" + " " + t + " " + "coordinate")
    val x = scala.io.StdIn.readLine()
    val pattern = "(^[0-9]$)".r
    x match {
      case pattern(p) => p.toInt
      case _ => println("Your number must be between 0 and 9")
                EntryParameters(t)
    }
  }

  /**
    *
    * @return the direction entered by the player
    */
  @tailrec
  def EntryDirection(): String = {
    println("Please enter the direction of the ship")
    val dir = scala.io.StdIn.readLine()
    dir match {
      case Ship.horizontal => dir
      case Ship.vertical => dir
      case _ => println("The direction must be vertical or horizontal")
        EntryDirection()
    }
  }

}
