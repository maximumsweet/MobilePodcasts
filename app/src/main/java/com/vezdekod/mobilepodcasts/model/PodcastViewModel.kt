package com.vezdekod.mobilepodcasts.model

import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.vezdekod.mobilepodcasts.ui.data.TimecodeData

class PodcastViewModel : ViewModel() {

    var mediaPlayer: MediaPlayer? = null
    var fileUri: Uri? = null

    val timecodes = mutableListOf<TimecodeData>()
}