package play.alacho.no.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_main.*
import play.alacho.no.viewmodel.SharedViewModel
import play.alacho.no.pgr202_tictactoe.R

class MainPageFragment : FragmentHelper(), View.OnClickListener{

  private lateinit var sharedViewModel: SharedViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedViewModel = activity?.run {
      ViewModelProviders.of(this).get(SharedViewModel::class.java)
    } ?: throw Exception("Invalid Activity")
  }

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
        listener.changeFragment(R.id.mainActivityFragment, HighScoreFragment())
      }
    }
  }
}
