package play.alacho.no.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProviders
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

  //TODO(Håvard) Should be a list in the Player object

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
    if (isAgainstAi) {
      view?.findViewById<ImageButton>(v.id)?.let { button ->
        button.setImageDrawable(playerOne.image?.drawable)
        button.isEnabled = false
        gameLogic.board[button.tag.toString().toInt()] = sharedViewModel.playerOne
      }
      if(gameLogic.board.filterNotNull().size <= 7) {
        val something = gameLogic.nextMove()
        view?.findViewWithTag<ImageButton>(something.toString())?.let { button ->
          button.setImageDrawable(playerTwo.image?.drawable)
          button.isEnabled = false
        }
      }


      val winner = gameLogic.findWinner()
      /*if(winner != null && gameLogic.board.filterNotNull().size > 4){
      Log.d("Message", winner.name)
    } */
    }
  }
}