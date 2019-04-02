package play.alacho.no.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.high_score_item.view.*
import play.alacho.no.game.Player
import play.alacho.no.pgr202_tictactoe.R

class HighScoreAdapter(private val highScoreList: List<Player>) : RecyclerView.Adapter<HighScoreAdapter.HighScoreHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HighScoreHolder {
    return HighScoreHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.high_score_item, parent, false))
  }

  override fun getItemCount(): Int {
    return highScoreList.count()
  }

  override fun onBindViewHolder(holder: HighScoreHolder, position: Int) {
    val highScoreItem = highScoreList[position]
    holder.bindHighScore(highScoreItem, position)
  }

  class HighScoreHolder(v: View) : RecyclerView.ViewHolder(v){

    private lateinit var highScoreItem: Player
    private var view = v
    var highScorePlace: Int = 0

    fun bindHighScore(highScoreItem: Player, position: Int){
      this.highScoreItem = highScoreItem
      this.highScorePlace = position
      view.name.text = highScoreItem.name
      view.position.text = this.highScorePlace.inc().toString()
      view.numberOfWins.text = highScoreItem.wins.toString()
    }
  }

}