package player

import ship._

trait AI extends Player {

  val goodHits: List[Point]
  def copyPlayerGoodHits(goodHits: List[Point]): Player
}


