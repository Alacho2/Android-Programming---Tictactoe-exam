package play.alacho.no.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import play.alacho.no.game.GameLogic
import play.alacho.no.game.SharedViewModel
import play.alacho.no.pgr202_tictactoe.R

class Game : FragmentHelper(), View.OnClickListener {
  private lateinit var sharedViewModel: SharedViewModel
  private var isPlayerOne: Boolean = true
  private var isAgainstAi: Boolean = true
  var winningList: List<List<Int>> =
    listOf(
      listOf(1,2,3), //First row - Horizontal
      listOf(4,5,6), //Second row - Horizontal
      listOf(7,8,9), //Third row - Horizontal
      listOf(1,4,7), //First row - Vertical
      listOf(2,5,8), //Second row - Vertical
      listOf(3,6,9), //Third row - Vertical
      listOf(1,5,9), //Cross left to right
      listOf(3,5,7) //Cross right to left
    )
  private lateinit var gameLogic: GameLogic

  //TODO(Håvard) Should be a list in the Player object
  private var playerOneList: MutableList<Int> = mutableListOf()
  private var playerTwoList: MutableList<Int> = mutableListOf()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedViewModel = activity?.run {
      ViewModelProviders.of(this).get(SharedViewModel::class.java)
    } ?: throw Exception("Invalid Activity")
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_game, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    Log.d("Dust", sharedViewModel.safet)

    //Place a click listener on all buttons
    //TODO(Håvard) Might initiate as it's own button-class, to easily pass the ID. 9 objects????

    for(i in 1..9){
      val resourceId: Int = resources.getIdentifier("button$i", "id", activity!!.packageName)
      val button: Button? = view?.findViewById(resourceId)
      button?.setOnClickListener(this)
    }
    gameLogic = GameLogic(sharedViewModel.playerOne, sharedViewModel.playerTwo)
  }

  //Sende inn spilleren
  //Sjekke om det er onePlayerMode

  override fun onClick(v: View) {

    //val imageButton: ImageButton? = view?.findViewById(v.id)
    if (isAgainstAi) {

      view?.findViewById<Button>(v.id)?.let { text ->
        text.text = "X"
        text.isEnabled = false
        gameLogic.board[text.tag.toString().toInt()] = sharedViewModel.playerOne
      }
      val winner = gameLogic.findWinner()
      /*if (winner != null) {
        Toast.makeText(activity!!.applicationContext, "${winner.name} has won!", Toast.LENGTH_SHORT).show()
        return
      }*/
      val something = gameLogic.nextMove()
      view?.findViewWithTag<Button>(something.toString())?.text = "O"
      view?.findViewWithTag<Button>(something.toString())?.isEnabled = false
    }
    /*val winner = gameLogic.findWinner()
    if (winner != null) {
      Toast.makeText(activity!!.applicationContext, "${winner.name} has won!", Toast.LENGTH_SHORT).show()
      return
    }*/
    /*view?.findViewById<Button>(v.id)?.let {  text ->
      if(isPlayerOne){
        //text?.setBackgroundResource(R.mipmap.pacman) //Get it from the players resource
        text.text = "X"
        isPlayerOne = false
        playerOneList.add(v.tag.toString().toInt()) // Ikke så dum idé å bruke tag når man bruker image
      } else {
        text.text = "O"
        isPlayerOne = true
        playerTwoList.add(v.tag.toString().toInt())
      }
    } */

    val isFound: Int = checkWinner()
    if(isFound != -1){
      /*(1..9)
        .map { resources.getIdentifier("button$it", "id", activity!!.packageName) }
        .mapNotNull { view?.findViewById<Button>(it) }
        .forEach { it.isEnabled =  false }
        */
      Log.d("Found", "Found")
      for(i in 0..9){
        val resourceId: Int = resources.getIdentifier("button$i", "id", activity!!.packageName)
        val button: Button? = view?.findViewById(resourceId)
        button?.isEnabled = false
      }
    }

    view?.findViewById<Button>(v.id)?.isEnabled = false

  }

  private fun checkWinner() = when {
    winningList.any { item -> playerOneList.containsAll(item) } -> 1
    winningList.any { item -> playerTwoList.containsAll(item) } -> 2
    else -> -1
  }

}