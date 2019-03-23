package play.alacho.no.viewmodel

import androidx.lifecycle.ViewModel
import play.alacho.no.game.Player

class SharedViewModel:ViewModel() {
  var playerOne: Player = Player()
  var playerTwo: Player = Player()
}