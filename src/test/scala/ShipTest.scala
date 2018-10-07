import org.scalatest._
import ship._

class ShipTest extends FlatSpec {

  val point1 = Point(2,3)
  val point2 = Point(3,3)
  val typeS = TypeShip("Carrier", 5)
  val pos = List(point1, point2)
  val hits = List(point1)
  val ship = Ship(pos, hits, typeS)

  "Ship" should "be created" in {
    assert(ship.isInstanceOf[Ship])
  }

  it should "get the list of positions" in {
    assert(ship.getPos == pos)
  }

  it should "get the list of positions hit" in {
    assert(ship.getHits == hits)
  }

  it should "get the type" in {
    assert(ship.getType == typeS)
  }

  "The method" should "say a ship is well placed" in {
    assert(Ship.canPlace(Ship.horizontal, 1, 2, 2))
    assert(Ship.canPlace(Ship.vertical, 2, 4, 5))

  }

  it should "not say a ship is well placed" in {
    assert(!Ship.canPlace(Ship.horizontal, 9, 3, 3))
    assert(!Ship.canPlace(Ship.vertical, 5, 9, 3))
  }

  it should "create the right ship" in {
    val pos2 = Ship.createList(Ship.horizontal, 2, 3, Ship.destroyer)
    val ship2 = Ship(pos2, Nil, Ship.destroyer)
    assert(ship.getPos == ship2.getPos)
  }

  it should "say that a ship is hit" in {
    assert(Ship.isHit(ship, point1))
    assert(Ship.isHit(ship, point2))
  }

  it should "not say that a ship is hit" in {
    val point3 = Point(1,4)
    val point4 = Point(6, 4)
    assert(!Ship.isHit(ship, point3))
    assert(!Ship.isHit(ship, point4))

  }

  it should "not say that a ship is sunk" in {
    assert(!Ship.isSunk(ship))
  }

  it should "say that a ship is sunk" in {
    val newHits = ship.getHits :+ point2
    val ship2 = Ship(pos, newHits, Ship.destroyer)
    assert(Ship.isSunk(ship2))
  }

  it should "not hit a ship" in {
    val point3 = Point(6,4)
    val ship3 = Ship.hitShip(ship, point3)
    assert(ship.getHits == ship3.getHits)
  }

  it should "hit a ship" in {
    val ship4 = Ship.hitShip(ship, point2)
    assert(!(ship.getHits == ship4.getHits))
  }



}
