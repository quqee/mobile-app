package com.suslanium.hackathon.assignment.presentation

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suslanium.hackathon.assignment.playback.AndroidAudioPlayer
import com.suslanium.hackathon.assignment.recorder.AndroidAudioRecorder
import java.io.File

class AssignmentViewModel : ViewModel() {
    var audioFile: File? = null
    var cacheDir: File? = null
}