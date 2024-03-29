package com.suslanium.hackathon.assignment.recorder

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}