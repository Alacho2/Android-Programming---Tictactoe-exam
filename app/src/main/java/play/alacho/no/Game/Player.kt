package play.alacho.no.Game

import android.widget.ImageButton

class Player {

  private var name: String = ""
  private var resource: Int = 0
  private var image: ImageButton? = null

  constructor()


  constructor(name: String, image: ImageButton, resource: Int = 0){
    this.name = name
    this.image = image
    this.resource = resource
  }
}