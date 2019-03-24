package play.alacho.no.game

import android.graphics.drawable.Drawable
import android.widget.ImageButton

class Player {

  var name: String = ""
  var image: ImageButton? = null

  constructor()
  
  constructor(name: String, image: ImageButton){
    this.name = name
    this.image = image
  }
}