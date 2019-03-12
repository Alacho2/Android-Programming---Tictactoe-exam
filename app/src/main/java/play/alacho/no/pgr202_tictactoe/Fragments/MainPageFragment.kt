package play.alacho.no.pgr202_tictactoe.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import play.alacho.no.pgr202_tictactoe.R

class MainPageFragment : Fragment(){

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.main_fragment, container, false)
  }
}