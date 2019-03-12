package play.alacho.no

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import play.alacho.no.Fragments.FragmentHelper

import play.alacho.no.pgr202_tictactoe.R
import play.alacho.no.Fragments.MainPageFragment

class MainActivity : AppCompatActivity() {

  private val fragmentManager = supportFragmentManager
  private val fragmentHelper: FragmentHelper = FragmentHelper()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    mainLogo.typeface = Typeface.createFromAsset(applicationContext.assets, "fonts/PAC-FONT.TTF")

    //setFirstFragment(R.id.mainActivityFragment, MainPageFragment())
    /*fragmentManager
      .beginTransaction()
      .replace(R.id.mainActivityFragment, MainPageFragment())
      .addToBackStack(null)
      .commit()
    //Set the first fragment

  }

  fun setFirstFragment(id: Int, fragment: Fragment){
    fragmentManager.beginTransaction().replace(R.id.mainActivityFragment, MainPageFragment()).addToBackStack(null).commit()
  } */
  }

  override fun onStart() {
    super.onStart()
    fragmentHelper.changeFragment(R.id.mainActivityFragment, MainPageFragment())
  }





}
