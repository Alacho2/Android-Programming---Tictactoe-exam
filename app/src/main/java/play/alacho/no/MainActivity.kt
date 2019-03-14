package play.alacho.no

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import play.alacho.no.Fragments.FragmentHelper

import play.alacho.no.pgr202_tictactoe.R
import play.alacho.no.Fragments.MainPageFragment

class MainActivity : AppCompatActivity(), FragmentHelper.OnFragmentChange {

  private val fragmentManager = supportFragmentManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mainLogo.typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/PAC-FONT.TTF")
    changeFragment(R.id.mainActivityFragment, MainPageFragment())
  }

  override fun changeFragment(id: Int, fragment: Fragment) {
    fragmentManager.beginTransaction()
      .setCustomAnimations(R.anim.abc_fade_in,  0)
      .replace(id, fragment)
      .addToBackStack(null)
      .commit()
  }
}
