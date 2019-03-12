package play.alacho.no.Fragments

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class FragmentHelper{


  private var fragmentTransaction: FragmentManager = activity!!.supportFragmentManager

  fun changeFragment(id: Int, fragment: Fragment){
    fragmentTransaction.beginTransaction().replace(id, fragment).addToBackStack(null).commit()
  }

}