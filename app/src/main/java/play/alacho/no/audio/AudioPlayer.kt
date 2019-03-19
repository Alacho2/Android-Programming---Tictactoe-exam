package play.alacho.no.audio

import android.content.Context
import android.media.MediaPlayer

object AudioPlayer : MediaPlayer() {
  private lateinit var mediaPlayer: MediaPlayer
  var soundID: Int = 0

  fun init(context: Context, resource: Int): AudioPlayer {
    mediaPlayer = MediaPlayer.create(context, resource)
    return this
  }

  /*fun startAudio(context: Context, resource: Int){
    mediaPlayer = MediaPlayer.create(context, resource)
    mediaPlayer.start()
  }

  fun playNext(context: Context, resource: Int){
    mediaPlayer.setOnCompletionListener {
      it.
    }

   }*/

  fun startAudio(){
    mediaPlayer.start()
  }

  // Basefragment som extender fragment
}