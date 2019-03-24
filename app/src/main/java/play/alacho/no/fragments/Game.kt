package play.alacho.no.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_game.*
import play.alacho.no.game.GameLogic
import play.alacho.no.game.Player
import play.alacho.no.viewmodel.SharedViewModel
import play.alacho.no.pgr202_tictactoe.R

class Game : FragmentHelper(), View.OnClickListener {
  private lateinit var sharedViewModel: SharedViewModel
  private var isAgainstAi: Boolean = false
  private lateinit var gameLogic: GameLogic
  private lateinit var playerOne: Player
  private lateinit var playerTwo: Player
  private var playerOnesTurn: Boolean = true
  private var winningList: List<List<Int>> =
    listOf( listOf(0,1,2), listOf(3,4,5), listOf(6,7,8), listOf(0,3,6),
      listOf(1,4,7), listOf(2,5,8), listOf(0,4,8), listOf(2,4,6)
    )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedViewModel = activity?.run {
      ViewModelProviders.of(this).get(SharedViewModel::class.java)
    } ?: throw Exception("Invalid Activity")
    playerOne = sharedViewModel.playerOne
    playerTwo = sharedViewModel.playerTwo
    isAgainstAi = sharedViewModel.shouldAiPlay
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_game, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    for (i in 1..9) {
      val resourceId: Int = resources.getIdentifier("button$i", "id", activity!!.packageName)
      val button: ImageButton? = view?.findViewById(resourceId)
      button?.setOnClickListener(this)
    }
    Log.d("AI", "${sharedViewModel.shouldAiPlay}")

    gameLogic = GameLogic(playerOne, playerTwo)
    playerOneIcon.setImageDrawable(playerOne.image?.drawable)
    playerTwoIcon.setImageDrawable(playerTwo.image?.drawable)
    playerOneName.text = playerOne.name
    playerTwoName.text = playerTwo.name
  }

  override fun onClick(v: View) {
    gameButton(v)
  }


  private fun gameButton(v: View) {

    val playerMove = view?.findViewById<ImageButton>(v.id)

    //TODO(Håvard): Clean up before exam
    if (isAgainstAi) {
      playHandler(playerOne, playerMove, null)
      if (gameLogic.board.filterNotNull().size <= 7) {
        val aiMove = gameLogic.nextMove()
        val playerTwoMove = view?.findViewWithTag<ImageButton>(aiMove.toString())
        playHandler(playerTwo, playerTwoMove, null)
      }
    } else {
      if(playerOnesTurn) {
        playHandler(playerOne, playerMove, false)
      } else {
        playHandler(playerTwo, playerMove, true)
      }
    }

    val winner = findWinner()
    if(winner != null && gameLogic.board.size > 4){
      Snackbar.make(activity!!.findViewById<FrameLayout>(R.id.mainActivityFragment),
        "${winner.name} won the game",
        Snackbar.LENGTH_LONG)
        .setAction("RESTART") { listener.changeFragment(R.id.mainActivityFragment, MainPageFragment()) }
        .show()
      1.until(10).forEach { idx ->
        val resourceId: Int = resources.getIdentifier("button$idx", "id", activity!!.packageName)
        val button: ImageButton? = view?.findViewById(resourceId)
        button?.isEnabled = false
      }
    } else if(winner == null && gameLogic.board.filterNotNull().size == 9){
      Snackbar.make(activity!!.findViewById<FrameLayout>(R.id.mainActivityFragment),
        "A draw has been made",
        Snackbar.LENGTH_LONG)
        .setAction("RESTART") { listener.changeFragment(R.id.mainActivityFragment, MainPageFragment()) }
        .show()
    }
  }

  private fun playHandler(player: Player, move: ImageButton?, playerOnesTurn: Boolean?){
    Log.d(player.name, "Message")
    move?.setImageDrawable(player.image?.drawable)
    move?.isEnabled = false
    player.moveList.add(move?.tag.toString().toInt())
    gameLogic.board[move?.tag.toString().toInt()] = player
    if(playerOnesTurn != null){
      this.playerOnesTurn = playerOnesTurn
    }
  }

  private fun findWinner() = when {
    winningList.any { item -> playerOne.moveList.containsAll(item) } -> playerOne
    winningList.any { item -> playerTwo.moveList.containsAll(item) } -> playerTwo
    else -> null
  }
}