import org.scalatest._
import ship._

class ShipTest extends FlatSpec {

  val point1 = Point(2,3)
  val point2 = Point(4,2)
  val hitPoint = Point(2,3)
  val typeS = TypeShip("Carrier", 5)
  val pos = List(point1, point2)
  val hits = List(hitPoint)
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



}
