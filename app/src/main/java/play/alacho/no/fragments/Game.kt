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
  private var isAgainstAi: Boolean = true
  private lateinit var gameLogic: GameLogic
  private lateinit var playerOne: Player
  private lateinit var playerTwo: Player
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
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_game, container, false)
  }

  //Push trekket til en playerliste, sjekke på den listen

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    for (i in 1..9) {
      val resourceId: Int = resources.getIdentifier("button$i", "id", activity!!.packageName)
      val button: ImageButton? = view?.findViewById(resourceId)
      button?.setOnClickListener(this)
    }
    gameLogic = GameLogic(playerOne, playerTwo) //SharedViewModel removed
    playerOneIcon.setImageDrawable(playerOne.image?.drawable)
    playerTwoIcon.setImageDrawable(playerTwo.image?.drawable)
  }

  override fun onClick(v: View) {
    gameButton(v)
  }

  private fun gameButton(v: View) {

    //TODO(Håvard): Clean up before exam
    if (isAgainstAi) {
      view?.findViewById<ImageButton>(v.id)?.let { button ->
        button.setImageDrawable(playerOne.image?.drawable)
        button.isEnabled = false
        gameLogic.board[button.tag.toString().toInt()] = sharedViewModel.playerOne
        playerOne.moveList.add(button.tag.toString().toInt())
      }
      playerOne.moveList.forEach { Log.d("something", it.toString()) }
      if(gameLogic.board.filterNotNull().size <= 7) {
        val something = gameLogic.nextMove()
        view?.findViewWithTag<ImageButton>(something.toString())?.let { button ->
          button.setImageDrawable(playerTwo.image?.drawable)
          button.isEnabled = false
        playerTwo.moveList.add(button.tag.toString().toInt())
        }
      playerTwo.moveList.forEach { Log.d("Player Two", it.toString()) }
      }
    }
    val winner = checkWinner()
    if(winner != null && gameLogic.board.size > 4){
      Snackbar.make(activity!!.findViewById<FrameLayout>(R.id.mainActivityFragment), "${winner.name} won the game", Snackbar.LENGTH_SHORT).show()
    }
  }

  private fun checkWinner() = when {
    winningList.any { item -> playerOne.moveList.containsAll(item) } -> playerOne
    winningList.any { item -> playerTwo.moveList.containsAll(item) } -> playerTwo
    else -> null
  }
}