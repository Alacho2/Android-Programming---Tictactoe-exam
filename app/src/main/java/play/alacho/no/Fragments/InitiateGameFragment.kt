package play.alacho.no.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.initiate_game_fragment.*
import play.alacho.no.pgr202_tictactoe.R

class InitiateGameFragment : Fragment(), View.OnClickListener{

   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     return inflater.inflate(R.layout.initiate_game_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    something.setOnClickListener(this)
    something2.setOnClickListener(this)
  }

  override fun onClick(v: View) {
    when(v.id){
      R.id.something -> { Log.d("Hallo", "Hallo") }
      R.id.something2 -> { Log.d("Dust", "Dust") }
    }
  }
}