package play.alacho.no.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_game.*
import org.json.JSONObject
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
  private lateinit var packageName: String
  private lateinit var prefsName: String
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
    packageName = getString(R.string.packageName)
    prefsName = getString(R.string.sharedPrefs)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_game, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    0.until(10).forEach {idx ->
      val resourceId: Int = resources.getIdentifier("button$idx", "id", activity!!.packageName)
      val button: ImageButton? = view?.findViewById(resourceId)
      button?.setOnClickListener(this)
    }
    playerOne.moveList = mutableListOf()
    playerTwo.moveList = mutableListOf()

    gameLogic = GameLogic(playerOne, playerTwo)
    playerOneIcon.setImageDrawable(playerOne.image?.drawable)
    playerTwoIcon.setImageDrawable(playerTwo.image?.drawable)
    playerOneName.text = playerOne.name
    playerTwoName.text = playerTwo.name
    activity!!.findViewById<Chronometer>(R.id.gameTimer).start()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    gameTimer.stop()
  }

  override fun onClick(v: View) {
    gameButton(v)
  }

  private fun gameButton(v: View) {

    val playerMove = view?.findViewById<ImageButton>(v.id)
    //TODO(Håvard): Clean up before exam
    if (isAgainstAi) {
      makeMoveFor(playerOne, playerMove, null)
      if (gameLogic.board.filterNotNull().size <= 7) {
        val aiMove = gameLogic.nextMove()
        val playerTwoMove = view?.findViewWithTag<ImageButton>(aiMove.toString())
        makeMoveFor(playerTwo, playerTwoMove, null)
      }
    } else {
      if(playerOnesTurn) {
        makeMoveFor(playerOne, playerMove, false)
      } else {
        makeMoveFor(playerTwo, playerMove, true)
      }
    }

    val winner = findWinner()
    if(winner != null && gameLogic.board.size > 4){
      activity!!.findViewById<Chronometer>(R.id.gameTimer).stop()
        makeSnackbar("${winner.name} won the game").show()
      1.until(10).forEach { idx ->
        val resourceId: Int = resources.getIdentifier("button$idx", "id", activity!!.packageName)
        val button: ImageButton? = view?.findViewById(resourceId)
        button?.isEnabled = false
      }
      //extract to method

      val prefs = activity!!.getSharedPreferences(packageName, Context.MODE_PRIVATE)
      val highScoreList = prefs.getStringSet(prefsName, setOf()).toMutableSet()

      highScoreList.forEach {
      val jsonObject = JSONObject(it)
        val itemName = jsonObject.getString("Name")
        var wins = jsonObject.getInt("Wins")

        if(winner.name == itemName) {
          winner.wins = ++wins
          highScoreList.add(winner.toJSON().toString())
          highScoreList.remove(it)
          prefs.edit().putStringSet(getString(R.string.sharedPrefs), highScoreList).apply()
          return
        }
      }

      winner.wins = 1
      highScoreList.add(winner.toJSON().toString())

      prefs.edit().putStringSet(getString(R.string.sharedPrefs), highScoreList).apply()

    } else if(winner == null && gameLogic.board.filterNotNull().size == 9){
      activity!!.findViewById<Chronometer>(R.id.gameTimer).stop()
        makeSnackbar("A draw has been made").show()
    }
  }

  private fun makeSnackbar(message: String): Snackbar{
    return Snackbar.make(activity!!.findViewById<FrameLayout>(R.id.mainActivityFragment),
      message,
      Snackbar.LENGTH_LONG)
      .setAction("RESTART") {
        listener.changeFragment(R.id.mainActivityFragment, Game())
      }
  }

  private fun makeMoveFor(player: Player, move: ImageButton?, playerOnesTurn: Boolean?){
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