package com.suslanium.hackathon.assignment.presentation

import androidx.lifecycle.ViewModel
import java.io.File

class AssignmentViewModel : ViewModel() {
    var audioFile: File? = null
    var cacheDir: File? = null
}