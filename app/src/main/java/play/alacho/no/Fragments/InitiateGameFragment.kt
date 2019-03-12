package play.alacho.no.Fragments

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.initiate_game_fragment.*
import org.apache.commons.lang3.time.StopWatch
import play.alacho.no.MainActivity
import play.alacho.no.pgr202_tictactoe.R

class InitiateGameFragment : androidx.fragment.app.Fragment(), View.OnClickListener{

  private var currentValue: String = ""

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     return inflater.inflate(R.layout.initiate_game_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    botSelector.setOnClickListener(this)
  }

  override fun onClick(v: View) {
    when(v.id){
      R.id.botSelector -> { botSelector() }
      //R.id.something2 -> { Log.d("Dust", "Dust") }
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
}