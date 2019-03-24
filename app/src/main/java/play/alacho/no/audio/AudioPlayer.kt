package play.alacho.no.audio

import android.content.Context
import android.media.MediaPlayer

object AudioPlayer : MediaPlayer() {
  private lateinit var mediaPlayer: MediaPlayer

  fun init(context: Context, resource: Int): AudioPlayer {
    mediaPlayer = MediaPlayer.create(context, resource)
    return this
  }

  fun startAudio(){
    mediaPlayer.start()
  }
}