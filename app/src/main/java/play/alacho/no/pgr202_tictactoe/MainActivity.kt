package play.alacho.no.pgr202_tictactoe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import play.alacho.no.pgr202_tictactoe.Fragments.InitiateGameFragment
import play.alacho.no.pgr202_tictactoe.Fragments.MainPageFragment

class MainActivity : AppCompatActivity() {

  private val fragmentManager = supportFragmentManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    fragmentChanger(R.id.mainActivityFragment, MainPageFragment())
  }

  fun playGame(view: View) {
    fragmentChanger(R.id.mainActivityFragment, InitiateGameFragment())
  }

  fun fragmentChanger(id: Int, fragment: Fragment){
    fragmentManager.beginTransaction().replace(id, fragment).addToBackStack(null).commit()
  }
}
