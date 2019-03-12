package play.alacho.no

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

import play.alacho.no.pgr202_tictactoe.R
import play.alacho.no.Audio.Fragments.MainPageFragment

class MainActivity : AppCompatActivity() {

  private val fragmentManager = supportFragmentManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mainLogo.typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/PAC-FONT.TTF")

    initiate(R.id.mainActivityFragment, MainPageFragment())
  }

  fun initiate(id: Int, fragment: Fragment){
    fragmentManager.beginTransaction().replace(id, fragment).addToBackStack(null).commit()
  }
}
