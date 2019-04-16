package play.alacho.no.game

class GameLogic(private val humanPlayer: Player?, private val botPlayer: Player?) {

  val board: Array<Player?> = arrayOfNulls(9)

  fun nextMove() = findNextMove().apply {
    board[this] = botPlayer
  }

  private fun findNextMove(): Int{
    val filledSpots = board.mapIndexedNotNull { index, value -> value?.let { index } }
    return if(filledSpots.size == 1){
      firstMove(filledSpots.first())
    } else {
      val winTile = findWinConditionFor(botPlayer, 1)
      val lossTile = findWinConditionFor(humanPlayer, 1)
      val possibleWinCondition = findWinConditionFor(botPlayer, 2)
      when {
        winTile != null -> winTile
        lossTile != null -> lossTile
        possibleWinCondition != null -> possibleWinCondition
        else -> 0.until(3).flatMap { horizontalIndexesFor(it, null) }.first()
      }
    }
  }

  private fun firstMove(firstMove: Int) =  when(firstMove) {
    in arrayOf(0,2,6,8,1,3) -> 4
    5 -> 8
    7 -> 8
    else -> 0
  }

  private fun findWinConditionFor(targetPlayer: Player?, requiredSpots: Int): Int? {
    if(board[4] == humanPlayer && board[6] == humanPlayer && board[2] == null) return 2
    if(board[2] == humanPlayer && board[4] == humanPlayer && board[6] == null) return 6
    0.until(3).forEach { idx ->
      val openSpotsHorizontal = horizontalIndexesFor(idx, null)
      val playerSpotsHorizontal = horizontalIndexesFor(idx, targetPlayer)
      if (openSpotsHorizontal.size == requiredSpots && playerSpotsHorizontal.size == 3-requiredSpots) {
        return openSpotsHorizontal.first()
      }
      val openSpotsVertical = verticalIndexesFor(idx, null)
      val playerSpotsVertical = verticalIndexesFor(idx, targetPlayer)
      if (openSpotsVertical.size == requiredSpots  && playerSpotsVertical.size == 3-requiredSpots) {
        return openSpotsVertical.first()
      }
    }
    return null
  }

  private fun horizontalIndexesFor(row: Int, player: Player?) =
    (row * 3).until(row * 3 + 3).filter { board[it] == player }

  private fun verticalIndexesFor(column: Int, player: Player?) =
    0.until(3).map { it * 3 + column }.filter { board[it] == player }
}