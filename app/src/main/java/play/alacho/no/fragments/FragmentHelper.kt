package play.alacho.no.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

open class FragmentHelper : Fragment() {

  internal lateinit var listener: OnFragmentChange

  private lateinit var fragmentTransaction: FragmentManager

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    fragmentTransaction = activity!!.supportFragmentManager
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if(context is OnFragmentChange){
      listener = context
    }
  }

  interface OnFragmentChange {
    fun changeFragment(id: Int, fragment: Fragment)
  }

}