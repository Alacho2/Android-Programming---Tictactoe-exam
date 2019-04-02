package play.alacho.no.game

import android.widget.ImageButton
import org.json.JSONObject

data class Player(var name: String = "",
                  var image: ImageButton? = null,
                  var wins: Int = 0,
                  var moveList: MutableList<Int> = mutableListOf()) {

  fun toJSON(): JSONObject {
    val playerJSON = JSONObject()
    playerJSON.put("Name", name)
    playerJSON.put("Wins", wins)
    return playerJSON
  }

  override fun toString(): String {
    return "Player(name='$name' wins=$wins)"
  }

  companion object {
    fun fromJSON(jsonObject: JSONObject): Player {
      val name = jsonObject.getString("Name")
      val wins = jsonObject.getInt("Wins")
      return Player(name, wins = wins)
    }
  }


}