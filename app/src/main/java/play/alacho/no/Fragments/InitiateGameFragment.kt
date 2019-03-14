package play.alacho.no.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.initiate_game_fragment.*
import play.alacho.no.Game.Player
import play.alacho.no.pgr202_tictactoe.R

class InitiateGameFragment : Fragment(), View.OnClickListener{

  private var currentValue: String = ""
  private var playerOne: Player = Player()
  private var playerTwo: Player = Player()
  private var playerOneSelected: Boolean = false


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
    when(v.id){
      R.id.botSelector -> { botSelector() }
      R.id.startGameBtn -> { beginGame() }
      else -> { handleImageClick(v.tag.toString()) }
    }
  }

  private fun botSelector(){
    if (botSelector.isChecked) {
      playerTwoNameInput.isEnabled = false
      currentValue = playerTwoNameInput.text.toString()
      playerTwoNameInput.setText(getString(R.string.botName))
    } else {
      playerTwoNameInput.isEnabled = true
      playerTwoNameInput.setText(currentValue)
    }
  }

  private fun handleImageClick(tag: String){
    //Sett verdien til en variabel og clear den variabelen hver gang man velger
    //Handle om resource allerede er valgt

    var imageButton: ImageButton = pacmanImage

    //if(!playerOneSelected) {
      when (tag) {
        "Inky" -> { imageButton = inkyImage }
        "Cherry" -> { imageButton = cherryImage }
        "Pacman" -> { imageButton = pacmanImage }
      }

      with(imageButton){
        setBackgroundResource(R.drawable.player_button_border)
        isEnabled = false
      }

      playerOneSelected = true

    /*} else {
      when(tag) {
        "Inky" -> {
          inkyImage.setBackgroundResource(R.drawable.playertwo_button_border)
        }
        "Pacman" -> {
          pacmanImage.setBackgroundResource(R.drawable.playertwo_button_border)
        }
      }
    } */
  }

  private fun beginGame() {

  }

}