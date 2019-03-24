package play.alacho.no.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_initiate_game.*
import play.alacho.no.game.Player
import play.alacho.no.viewmodel.SharedViewModel
import play.alacho.no.pgr202_tictactoe.R

class InitiateGameFragment : FragmentHelper(), View.OnClickListener {

  private var currentValue: String = ""
  private var playerOneSelected: Boolean = false
  private var names: MutableList<String> = mutableListOf()
  private lateinit var sharedViewModel: SharedViewModel
  private lateinit var playerOne: Player
  private lateinit var playerTwo: Player

  companion object {
    private const val PADDING_VALUE: Int = 30
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedViewModel = activity?.run {
      ViewModelProviders.of(this).get(SharedViewModel::class.java)
    } ?: throw Exception("Invalid Activity")

    playerOne = sharedViewModel.playerOne
    playerTwo = sharedViewModel.playerTwo
    resetPlayerInto()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_initiate_game, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    botSelector.setOnClickListener(this)
    startGameBtn.setOnClickListener(this)
    pacmanImage.setOnClickListener(this)
    cherryImage.setOnClickListener(this)
    inkyImage.setOnClickListener(this)
    names = mutableListOf(
      getString(R.string.inkImageDesc),
      getString(R.string.cheImageDesc),
      getString(R.string.pacImageDesc))
  }

  override fun onClick(v: View) {
    when (v.id) {
      R.id.botSelector -> { botSelector() }
      R.id.startGameBtn -> { beginGame() }
      else -> { handleImageClick(v.tag.toString()) }
    }
  }

  private fun botSelector() {

    var randomName = names.random()

    while(playerOne.image?.tag == randomName){
      randomName = names.random()
    }

    if (botSelector.isChecked){
      playerTwoNameInput.isEnabled = false
      currentValue = playerTwoNameInput.text.toString()
      playerTwoNameInput.setText(getString(R.string.botName))

      if(playerTwo.image?.background == null) {
        setImageValues(playerTwo, randomName, R.drawable.playertwo_button_border)
      }
    } else {
      playerTwoNameInput.isEnabled = true
      playerTwoNameInput.setText(currentValue)

      //Clear the values before allowing new selection
      playerTwo.image?.let {
        it.setBackgroundResource(0)
        it.setPadding(0, 0, 0, 0)
        it.isEnabled = true
      }
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
  }

  private fun setImageValues(player: Player, tag: String?, resource: Int){
    when (tag) {
      getString(R.string.inkImageDesc) -> { player.image = inkyImage }
      getString(R.string.cheImageDesc) -> { player.image = cherryImage }
      getString(R.string.pacImageDesc) -> { player.image = pacmanImage }
    }
    player.image?.let {
      it.setBackgroundResource(resource)
      it.isEnabled = false
      it.setPadding(PADDING_VALUE, PADDING_VALUE, PADDING_VALUE, PADDING_VALUE)
    }
  }

  private fun beginGame() {
    playerOne.name = playerOneNameInput.text.toString()
    playerTwo.name = playerTwoNameInput.text.toString()

    if(playerOne.name.isBlank() || playerOne.image == null || playerTwo.name.isBlank() || playerTwo.image == null){
      Snackbar.make(activity!!.findViewById<FrameLayout>(R.id.mainActivityFragment), "You need to set name & image", Snackbar.LENGTH_SHORT).show()
    } else {
      listener.changeFragment(R.id.mainActivityFragment, Game())
    }
  }

  private fun resetPlayerInto() {
    playerOne.name = ""
    playerTwo.name = ""
    playerOne.image = null
    playerTwo.image = null
    playerOne.moveList = mutableListOf()
    playerTwo.moveList = mutableListOf()
  }

}
