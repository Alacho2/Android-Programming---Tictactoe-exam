package play.alacho.no.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import play.alacho.no.pgr202_tictactoe.R

class MainPageFragment : FragmentHelper(), View.OnClickListener{

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_main, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    playGameBtn.setOnClickListener(this)
    highScoreBtn.setOnClickListener(this)
  }

  override fun onClick(v: View) {
    when(v.id){
      R.id.playGameBtn -> {
        listener.changeFragment(R.id.mainActivityFragment, InitiateGameFragment())
      }
      R.id.highScoreBtn -> {
        Log.d("Hallo", "Hei")
      }
    }
  }
}
