package play.alacho.no.game

import androidx.lifecycle.ViewModel

class SharedViewModel:ViewModel() {
  var safet = "Is cool"
  var playerOne: Player = Player()
  var playerTwo: Player = Player()
}