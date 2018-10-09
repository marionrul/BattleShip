package game

import java.io._

object Output {

  def chooseName(player: String): Unit = {
    println("Please enter the " + player + " name")
  }

  def chooseGameMode(): Unit = {
    println("Please choose your game mode between : " + "\n" +
      " 1 - Human vs Human " + "\n" +
      " 2 - Human vs AI Beginner " + "\n" +
      " 3 - Human vs AI Medium\" " + "\n" +
      " 4 - Human vs AI Hard "+ "\n" +
      " 5 - Fight of the AI "+ "\n")
  }

  def invalidMode(): Unit = {
    println("Your number must be between 1 and 5")
  }

  def chooseCoordinate(cord: String): Unit = {
    println("Please enter the " + cord + " coordinate (between 1 and 10)")
  }

  def invalidCoordinate(): Unit = {
    println("Your number must be between 1 and 10")
  }

  def chooseDirection(): Unit = {
    println("Please enter the direction of the ship ('h' for horizontal and 'v' for vertical)")
  }

  def invalidDirection(): Unit = {
    println("The direction must be 'h' or 'v' ")
  }

  def printWinner(name: String): Unit = {
    println("The player " + name + " won")
  }

  def printShipToPlace(name: String, len: Int): Unit = {
    println("The ship to place is " + name + " with the length " + len)
  }

  def chooseParameters(): Unit = {
    println("Please enter its parameters")
  }

  def positionOccupied(): Unit = {
    println("The position is already occupied, place again")
  }

  def outsideGrid(): Unit = {
    println("The ship is outside the grid, place again")
  }

  def badHit(): Unit = {
    println("You have already made this shot or the coordinate are not good")
  }

  def printGrid(name: String): Unit = {
    println(name + " these are your grids")
  }

  def printGridShips(name: String): Unit = {
    println(name + " this is the grid with your ships")
  }

  def chooseShot(name: String): Unit = {
    println(name + " Please enter the shot you want to make")
  }

  def printShipHit(ship: String): Unit = {
    println("The ship " + ship + " is hit")
  }

  def printShipSunk(ship: String): Unit = {
    println("The ship " + ship + " is sunk")
  }

  def startGame(): Unit = {
    println("The battleship is about to start")
  }

  def printTurn(name: String): Unit = {
    println(name + " your turn")
  }

  def printNbWins(name: String, nbWins: Int): Unit = {
    print("The player " + name + " won" + nbWins + " times")
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
