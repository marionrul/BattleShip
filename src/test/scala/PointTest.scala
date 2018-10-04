import org.scalatest._
import ship._

class PointTest extends FlatSpec {

  "Point" should "get x coordinate" in {
    val point = Point(2, 3)
    assert(point.getX == 2)
  }

  it should "get y coordinate" in {
    val point = Point(2, 3)
    assert(point.getY == 3)
  }

  it should "not get x coordinate" in {
    val point = Point(2, 3)
    assert(!(point.getX == 3))
  }

  it should "not get y coordinate" in {
    val point = Point(2, 3)
    assert(!(point.getY == 2))
  }

  it should "have good x and y positions" in {
    assert(Point.isGood(1, 10))
  }

  it should "not have good x and y positions" in {
    assert(!Point.isGood(-1, 11))
  }

  it should "not have a good x position" in {
    assert(!Point.isGood(0, 8))
  }

  it should "not have a good y position" in {
    assert(!Point.isGood(1, -2))
  }

}
