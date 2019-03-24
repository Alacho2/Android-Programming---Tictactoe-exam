package play.alacho.no.game

import android.widget.ImageButton

class Player {
  var name: String = ""
  var image: ImageButton? = null
  var moveList: MutableList<Int> = mutableListOf()
}