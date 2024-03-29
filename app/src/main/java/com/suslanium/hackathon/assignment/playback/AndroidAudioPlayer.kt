package com.suslanium.hackathon.assignment.playback

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlayer(
    private val context: Context
): AudioPlayer {
    private var player: MediaPlayer? = null

    override fun playFile(file: File) {
        MediaPlayer.create(context, file.toUri())?.let {
            player = it
            it.start()
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}