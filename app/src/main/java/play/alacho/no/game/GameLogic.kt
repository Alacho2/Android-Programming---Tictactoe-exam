package play.alacho.no.game

import android.util.Log

class GameLogic(val humanPlayer: Player, val botPlayer: Player) {

  val board: Array<Player?> = arrayOfNulls(9)

  fun nextMove() = findNextMove().apply {
    board[this] = botPlayer
  }

  private fun findNextMove(): Int{
    val filledSpots = board.mapIndexedNotNull { index, value -> value?.let { index } }
    return if(filledSpots.size == 1){
      firstMove(filledSpots.first())
    } else {
      // Try to find a win condition
      val winTile = findWinConditionFor(botPlayer, 1)
      // If we can't find a loss condition check for a loss condition and block it
      val lossTile = findWinConditionFor(humanPlayer, 1)
      val possibleWinCondition = findWinConditionFor(botPlayer, 2)

      Log.d("Loss Tile", lossTile.toString())
      Log.d("Win Tile", winTile.toString())
      Log.d("Potential", possibleWinCondition.toString())
      when {
        lossTile != null -> lossTile
        winTile != null -> winTile
        possibleWinCondition != null -> possibleWinCondition
        else -> 0.until(3).flatMap { horizontalIndexesFor(it, null) }.first() // No possible win conditions, take first open
      }
    }
  }

  private fun firstMove(firstMove: Int) =  when(firstMove) { // check first placement
    in arrayOf(0,2,6,8,7) -> 4
    in arrayOf(1,3) -> 8
    else -> 0
  }

  fun findWinner(): Player? = when {
    findWinConditionFor(botPlayer, 3) == -1 -> botPlayer
    findWinConditionFor(humanPlayer, 3) == -1 -> humanPlayer
    else -> null
  }

  private fun findWinConditionFor(targetPlayer: Player, requiredSpots: Int): Int? {
    if(board[4] == humanPlayer && board[6] == humanPlayer && board[2] == null){ return 2 }
    if(board[4] == humanPlayer && board[2] == humanPlayer && board[6] == null){ return 6 }
    if(board[6] == humanPlayer && board[7] == humanPlayer && board[8] == null){ return 8 }
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
  /*fun horizontalIndexesFor(row: Int, player: Player?) = mutableListOf<Int>().apply {
    for (idx in (row * 3).until(row * 3 + 3)) {
      if (board[idx] == player) {
        add(idx)
      }
    }
  }*/

  private fun verticalIndexesFor(column: Int, player: Player?) =
    0.until(3).map { it * 3 + column }.filter { board[it] == player }
  /*fun verticalIndexesFor(column: Int, player: Player?) = mutableListOf<Int>().apply {
    for (idx in 0.until(3).map { it * 3 + column }) {
      if (board[idx] == player) {
        add(idx)
      }
    }
  }*/
}