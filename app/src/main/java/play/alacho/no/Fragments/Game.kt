package play.alacho.no.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_game.*
import play.alacho.no.pgr202_tictactoe.R

class Game : FragmentHelper(), View.OnClickListener {

  private var isPlayerOne: Boolean = true
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

  //TODO(Håvard) Should be a list in the Player object
  private var playerOneList: MutableList<Int> = mutableListOf()
  private var playerTwoList: MutableList<Int> = mutableListOf()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_game, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    //Place a click listener on all buttons
    //TODO(Håvard) Might initiate as it's own button-class, to easily pass the ID. 9 objects????

    for(i in 2..9){
      val resourceId: Int = resources.getIdentifier("button$i", "id", activity!!.packageName)
      val button: Button? = view?.findViewById(resourceId)
      button?.setOnClickListener(this)
    }
    button1.setOnClickListener(this)
  }


  override fun onClick(v: View) {

    //val imageButton: ImageButton? = view?.findViewById(v.id)

    if(isPlayerOne){
      //imageButton?.setBackgroundResource(R.mipmap.pacman) //Get it from the players resource
      isPlayerOne = false
      //playerOneList.add(v.tag.toString().toInt()) // Ikke så dum idé å bruke tag når man bruker image
    } else {
      view?.findViewById<Button>(v.id)?.text = "O"
      isPlayerOne = true
      playerTwoList.add(v.tag.toString().toInt())
    }

    checkWinner()

    view?.findViewById<Button>(v.id)?.isEnabled = false

  }

  private fun checkWinner(): Int{
    for(item: List<Int> in winningList){
      if(playerOneList.containsAll(item)){
        Toast.makeText(context, "You did it!", Toast.LENGTH_SHORT).show()
        return 1
      } else if (playerTwoList.containsAll(item)) {
        Toast.makeText(context, "Player 2 did it!", Toast.LENGTH_SHORT).show()
        return 2
      }
    }
    return -1
  }

}