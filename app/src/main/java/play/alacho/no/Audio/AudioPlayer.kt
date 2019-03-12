package play.alacho.no.Audio

import android.content.Context
import android.media.MediaPlayer

object AudioPlayer {
  private lateinit var mediaPLayer: MediaPlayer

  fun startAudio(context: Context, resource: Int){
    mediaPLayer = MediaPlayer.create(context, resource)
    mediaPLayer.start()
  }
}