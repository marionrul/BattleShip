package ship

object Main extends App {

  var player1:Player = Player("Marion", Nil, Nil)
  var player2 = Player("H", Nil, Nil)

  println("The battleship is about to start")
  val grille : Grid = Grid.createRowGrid(1)
  println(player1.getName + " your turn")
  player1 = Player.createFleet(Ship.typeShipList, player1)
  Grid.displayGrid(grille.getCells, 1, 1, player1)

  println(player2.getName + " your turn")
  player2 = Player.createFleet(Ship.typeShipList, player2)
  Grid.displayGrid(grille.getCells, 1, 1, player2)



  println("x tir?")
  val xtir = scala.io.StdIn.readLine()
  println("y tir?")
  val ytir = scala.io.StdIn.readLine()
  if(Point.isGood(xtir.toInt, ytir.toInt)) {
    val p = Point(xtir.toInt, ytir.toInt)
    var tuple = (player2, player1)
    tuple = Game.hit(player2, player1, p)
    println("Tirs places", tuple._1.getPlacedHits)
    tuple._2.ships.foreach(x => println("Cell hits", x.getHits))
  }




}
