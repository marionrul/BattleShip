package player

import ship._

trait AI extends Player {

  /**
    * For the AI Hard, so it can remember the goodHits placed
    */
  val goodHits: List[Point]

  /**
    * Creates a new player
    * @param goodHits a new list of goodHits placed
    * @return a new player with the new lis of goodHits
    */
  def copyPlayerGoodHits(goodHits: List[Point]): Player


}


