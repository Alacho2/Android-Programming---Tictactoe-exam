package play.alacho.no

import android.graphics.Typeface
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

import play.alacho.no.pgr202_tictactoe.R
import play.alacho.no.Fragments.InitiateGameFragment
import play.alacho.no.Fragments.MainPageFragment

class MainActivity : AppCompatActivity() {

  private val fragmentManager = supportFragmentManager
  private lateinit var mediaPLayer: MediaPlayer

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    mediaPLayer = MediaPlayer.create(this, R.raw.pacman_eatfruit)
    mainLogo.typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/PAC-FONT.TTF")
    fragmentChanger(R.id.mainActivityFragment, MainPageFragment())
  }

  fun playGame(view: View) {
    fragmentChanger(R.id.mainActivityFragment, InitiateGameFragment())
    mediaPLayer.start()
  }

  fun fragmentChanger(id: Int, fragment: Fragment){
    fragmentManager.beginTransaction().replace(id, fragment).addToBackStack(null).commit()
  }
}
