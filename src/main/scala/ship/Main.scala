package ship

object Main extends App {

  var player1:Player = Player("Marion", Nil, Nil)
  var player2 = Player("H", Nil, Nil)

println("x ?")
  val x = scala.io.StdIn.readLine()
  println("y ?")
  val y = scala.io.StdIn.readLine()
  val len = 2
  println("direction ?")
  val dir = scala.io.StdIn.readLine()
  if(Ship.canPlace(dir, x.toInt, y.toInt, len)) {
val typeS: TypeShip = Ship.destroyer
    val liste = Ship.createList(dir, x.toInt, y.toInt, len)
    println("LISTE DE POINTS", liste)
    val destroyer = Ship(liste, Nil, typeS)
    player1= player1.placeShip(destroyer)
    println("OKKKK", player1.ships)

  }
  else {
    println("ERRRREUR le bateau sera en dehors de la grille")
  }



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
