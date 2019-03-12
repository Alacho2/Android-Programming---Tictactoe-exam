package play.alacho.no.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_fragment.*
import play.alacho.no.Audio.AudioPlayer
import play.alacho.no.pgr202_tictactoe.R

class MainPageFragment : Fragment(), FragmentHelper, View.OnClickListener{

  private lateinit var fragmentTransaction: FragmentManager

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.main_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    //fragmentTransaction = activity!!.supportFragmentManager
    playGameBtn.setOnClickListener(this)
    highScoreBtn.setOnClickListener(this)
  }

  override fun onClick(v: View) {
    when(v.id){
      R.id.playGameBtn -> {
        fragmentChanger(R.id.mainActivityFragment, InitiateGameFragment())
      }
      R.id.highScoreBtn -> {
        Log.d("Hallo", "Hei")
      }
    }
  }

  fun fragmentChanger(id: Int, fragment: Fragment){
    fragmentTransaction.beginTransaction().replace(id, fragment).addToBackStack(null).commit()

    AudioPlayer.init(activity!!.applicationContext, R.raw.pacman_eatfruit)
    AudioPlayer.startAudio()
    //AudioPlayer.startAudio(activity!!.applicationContext, R.raw.pacman_eatfruit)
  }
}
