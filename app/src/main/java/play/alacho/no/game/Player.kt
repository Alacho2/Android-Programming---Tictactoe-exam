package play.alacho.no.game

import android.widget.ImageButton

class Player {

  var name: String = ""
  var resource: Int = 0
  var image: ImageButton? = null

  constructor()
  
  constructor(name: String, image: ImageButton, resource: Int = 0){
    this.name = name
    this.image = image
    this.resource = resource
  }
}