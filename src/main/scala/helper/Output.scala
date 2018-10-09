package helper

import java.io._

object Output {

  /**
    * Output to choose the name
    * @param player the player name
    */
  def chooseName(player: String): Unit = {
    println("Please enter the " + player + " name")
  }

  /**
    * Output to choose the game mode
    */
  def chooseGameMode(): Unit = {
    println("Please choose your game mode between : " + "\n" +
      " 1 - Human vs Human " + "\n" +
      " 2 - Human vs AI Beginner " + "\n" +
      " 3 - Human vs AI Medium\" " + "\n" +
      " 4 - Human vs AI Hard "+ "\n" +
      " 5 - Fight of the AI "+ "\n")
  }

  /**
    * Output to indicate an invalid mode
    */
  def invalidMode(): Unit = {
    println("Your number must be between 1 and 5")
  }

  /**
    * Output to choose the coordinate
    * @param cord the name of the coordinate
    */
  def chooseCoordinate(cord: String): Unit = {
    println("Please enter the " + cord + " coordinate (between 1 and 10)")
  }

  /**
    * Output to indicate an invalid coordinate
    */
  def invalidCoordinate(): Unit = {
    println("Your number must be between 1 and 10")
  }

  /**
    * Output to choose the direction
    */
  def chooseDirection(): Unit = {
    println("Please enter the direction of the ship ('h' for horizontal and 'v' for vertical)")
  }

  /**
    * Output to indicate an invalid direction
    */
  def invalidDirection(): Unit = {
    println("The direction must be 'h' or 'v' ")
  }

  /**
    * Output to print the winner
    * @param name the winner name
    */
  def printWinner(name: String): Unit = {
    println("The player " + name + " won")
  }

  /**
    * Output to print the ship to place
    * @param name the name of the ship to place
    * @param len the length of the ship to place
    */
  def printShipToPlace(name: String, len: Int): Unit = {
    println("The ship to place is " + name + " with the length " + len)
  }

  /**
    * Output to choose the parameters
    */
  def chooseParameters(): Unit = {
    println("Please enter its parameters")
  }

  /**
    * Output to indicate that the position is occupied
    */
  def positionOccupied(): Unit = {
    println("The position is already occupied, place again")
  }

  /**
    * Output to indicate that the ship is outside the grid
    */
  def outsideGrid(): Unit = {
    println("The ship is outside the grid, place again")
  }

  /**
    * Output to indicate that the hit is not good
    */
  def badHit(): Unit = {
    println("You have already made this shot or the coordinate are not good")
  }

  /**
    * Output to print the grids
    * @param name the name of the player that sees the grids
    */
  def printGrid(name: String): Unit = {
    println(name + " these are your grids")
  }

  /**
    * Output to print the grid with the ships
    * @param name the name of the players that sees the grid
    */
  def printGridShips(name: String): Unit = {
    println(name + " this is the grid with your ships")
  }

  /**
    * Output to choose the shot
    * @param name the name of the player that makes the shot
    */
  def chooseShot(name: String): Unit = {
    println(name + " Please enter the shot you want to make")
  }

  /**
    * Output to print the ship that was hit
    * @param ship the name of the ship hit
    */
  def printShipHit(ship: String): Unit = {
    println("The ship " + ship + " is hit")
  }

  /**
    * Output to print the ship that was sunk
    * @param ship the name of the ship sunk
    */
  def printShipSunk(ship: String): Unit = {
    println("The ship " + ship + " is sunk")
  }

  /**
    * Output to start the game
    */
  def startGame(): Unit = {
    println("The battleship is about to start")
  }

  /**
    * Output to print the player turn
    * @param name the name of the player
    */
  def printTurn(name: String): Unit = {
    println(name + " your turn")
  }

  /**
    * Output to print the number of wins of a player
    * @param name the player name
    * @param nbWins the number of wins
    */
  def printNbWins(name: String, nbWins: Int): Unit = {
    print("The player " + name + " won" + nbWins + " times")
  }

  /**
    * Output to export the content to a CSV file
    * @param content the content of the file
    */
  def exportToCSV(content: String) : Unit = {
    val bufferedWriter = new BufferedWriter(new FileWriter("./ai_proof.csv"))
    bufferedWriter.write(content)
    bufferedWriter.flush()
    bufferedWriter.close()
  }



}
