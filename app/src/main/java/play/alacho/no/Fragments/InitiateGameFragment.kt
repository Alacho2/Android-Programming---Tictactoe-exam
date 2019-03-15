package play.alacho.no.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.initiate_game_fragment.*
import play.alacho.no.Game.Player
import play.alacho.no.pgr202_tictactoe.R

class InitiateGameFragment : Fragment(), View.OnClickListener {

  private var currentValue: String = ""
  private var playerOne: Player = Player()
  private var playerTwo: Player = Player()
  private var playerOneSelected: Boolean = false
  private var playerTwoSelected: Boolean = false

  companion object {
    private const val PADDING_VALUE: Int = 30
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.initiate_game_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    botSelector.setOnClickListener(this)
    pacmanImage.setOnClickListener(this)
    cherryImage.setOnClickListener(this)
    inkyImage.setOnClickListener(this)
  }

  override fun onClick(v: View) {
    when (v.id) {
      R.id.botSelector -> {
        botSelector()
      }
      R.id.startGameBtn -> {
        beginGame()
      }
      else -> {
        handleImageClick(v.tag.toString())
      }
    }
  }

  private fun botSelector() {
    Log.d("Inkyimage", inkyImage.paddingBottom.toString())
    if (botSelector.isChecked) {
      playerTwoNameInput.isEnabled = false
      currentValue = playerTwoNameInput.text.toString()
      setImageValues("Inky", R.drawable.playertwo_button_border)
      playerTwoNameInput.setText(getString(R.string.botName))
      playerTwoSelected = true
    } else {
      playerTwoNameInput.isEnabled = true
      playerTwoNameInput.setText(currentValue)
      inkyImage.isEnabled = true
      inkyImage.setBackgroundResource(0)
      inkyImage.setPadding(0, 0, 0, 0)
      playerTwoSelected = false
    }
  }

  /*
    We need to save a reference to which image a player picked.
    0 = Pacman
    1 = Cherry
    2 = Inky
   */

  private fun handleImageClick(tag: String) {

    if (!playerOneSelected) {
      setImageValues(tag, R.drawable.player_button_border)
      playerOneSelected = true
    } else if (!playerTwoSelected) {
      setImageValues(tag, R.drawable.playertwo_button_border)
      playerTwoSelected = true
    }

    else if (playerTwoSelected && playerTwoSelected){

    }

  }
  private fun setImageValues(tag: String?, resource: Int){

    var image: ImageButton? = null

    when (tag) {
      "Inky" -> { image = inkyImage }
      "Cherry" -> { image = cherryImage }
      "Pacman" -> { image = pacmanImage }
    }
    image?.setBackgroundResource(resource)
    image?.setPadding(PADDING_VALUE, PADDING_VALUE, PADDING_VALUE, PADDING_VALUE)
    image?.isEnabled = false
  }

  private fun beginGame() {
    //Send the data to the next fragment which is the actual game
  }

}
