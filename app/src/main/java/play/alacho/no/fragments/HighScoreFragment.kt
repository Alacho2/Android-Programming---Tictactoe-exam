package play.alacho.no.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_highscore.*
import org.json.JSONObject
import play.alacho.no.game.Player
import play.alacho.no.pgr202_tictactoe.R
import play.alacho.no.recyclerview.HighScoreAdapter
import play.alacho.no.viewmodel.SharedViewModel

class HighScoreFragment : FragmentHelper() {

  private lateinit var sharedViewModel: SharedViewModel
  private lateinit var layoutManager: LinearLayoutManager
  private lateinit var adapter: HighScoreAdapter
  private lateinit var packageName: String
  private lateinit var prefsName: String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sharedViewModel = activity?.run {
      ViewModelProviders.of(this).get(SharedViewModel::class.java)
    } ?: throw Exception("Invalid Activity")
    packageName = getString(R.string.packageName)
    prefsName = getString(R.string.sharedPrefs)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_highscore, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val prefs = activity!!.getSharedPreferences(packageName, Context.MODE_PRIVATE)
    val highScoreList = prefs.getStringSet(prefsName, setOf()).toMutableList().map {
      val player = Player.fromJSON(JSONObject(it))
      player
    }.sortedWith(compareByDescending { it.wins })

    /*highScoreList.sortWith(compareBy {
      var player = Player()
      player.fromJSON(JSONObject(it))
      player.wins
    }) */

    layoutManager = LinearLayoutManager(activity)
    highScoreRecycler.layoutManager = LinearLayoutManager(activity)
    adapter = HighScoreAdapter(highScoreList)
    highScoreRecycler.adapter = adapter
  }

}
