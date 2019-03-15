package play.alacho.no.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.initiate_game_fragment.*
import play.alacho.no.Game.Player
import play.alacho.no.pgr202_tictactoe.R

class InitiateGameFragment : Fragment(), View.OnClickListener {

  private var currentValue: String = ""
  private var playerOne: Player = Player()
  private var playerTwo: Player = Player()
  private var playerOneSelected: Boolean = false
  private var onePlayerMode: Boolean = false
  private var names: MutableList<String> = mutableListOf("Inky", "Cherry", "Pacman")

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

    onePlayerMode = botSelector.isChecked

    var randomName = names.random()

    while(playerOne.image?.tag == randomName){
      randomName = names.random()
    }


    //Hent ut et navn fra arrayet, sjekk om det navnet er lik det som ligger i playerOne sin tag, bytt
    if (onePlayerMode){
      playerTwoNameInput.isEnabled = false
      currentValue = playerTwoNameInput.text.toString()
      playerTwoNameInput.setText(getString(R.string.botName))
      if(playerTwo.image?.background == null) {
        setImageValues(playerTwo, randomName, R.drawable.playertwo_button_border)
      }
    } else {
      playerTwoNameInput.isEnabled = true
      playerTwoNameInput.setText(currentValue)

      //Clear the values before setting allowing new selection
      playerTwo.image?.setBackgroundResource(0)
      playerTwo.image?.setPadding(0,0,0,0)
      playerTwo.image?.isEnabled = true
      playerTwo.image = null
    }
  }

  private fun handleImageClick(tag: String) {

    if (playerOne.image == null && !playerOneSelected){
      setImageValues(playerOne, tag, R.drawable.player_button_border)
      playerOneSelected = true
    } else if (playerTwo.image == null){
      setImageValues(playerTwo, tag, R.drawable.playertwo_button_border)
    }

    Log.d("Soemthing", playerTwo.image.toString())

    /* if (!playerOneSelected) {
      setImageValues(playerOne, tag, R.drawable.player_button_border)
      playerOneSelected = true
    } else if (!playerTwoSelected && !botSelector.isChecked) {
      setImageValues(playerTwo, tag, R.drawable.playertwo_button_border)
      playerTwoSelected = true
    }

    else if (playerTwoSelected && playerTwoSelected){

    } */

  }
  private fun setImageValues(player: Player, tag: String?, resource: Int){

    when (tag) {
      "Inky" -> { player.image = inkyImage }
      "Cherry" -> { player.image = cherryImage }
      "Pacman" -> { player.image = pacmanImage }
    }
    player.image?.setBackgroundResource(resource)
    player.image?.setPadding(PADDING_VALUE, PADDING_VALUE, PADDING_VALUE, PADDING_VALUE)
    player.image?.isEnabled = false
  }

  private fun beginGame() {

    //Send the data to the next fragment which is the actual game
    //Populate the player objects and pass it into the game
  }

}
