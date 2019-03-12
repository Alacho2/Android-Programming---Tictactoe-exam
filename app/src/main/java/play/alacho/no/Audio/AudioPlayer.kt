package play.alacho.no.Audio

import android.content.Context
import android.media.MediaPlayer

object AudioPlayer : MediaPlayer() {
  private lateinit var mediaPlayer: MediaPlayer

  fun init(context: Context, resource: Int) {
    mediaPlayer = MediaPlayer.create(context, resource)
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