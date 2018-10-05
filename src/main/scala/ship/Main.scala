package ship

object Main extends App {

  var player1:Player = Player("Marion", Nil, Nil)
  var player2 = Player("Achraf", Nil, Nil)

  println("The battleship is about to start")
  val grille : Grid = Grid.createRowGrid(1)
  println(player1.getName + " your turn")
  player1 = Player.createFleet(Ship.typeShipList, player1)
  Grid.displayGrid1(1, 1, player1)
  println("")
  Grid.displayGrid2(1, 1, player1, player2)

  println(player2.getName + " your turn")
  player2 = Player.createFleet(Ship.typeShipList, player2)
  Grid.displayGrid1(1, 1, player2)
  println(" ")
  Grid.displayGrid2(1, 1, player2, player1)



  val winner = Game.hit(player1, player2)




}
