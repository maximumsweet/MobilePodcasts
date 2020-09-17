package com.vezdekod.mobilepodcasts.ui.audioedit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vezdekod.mobilepodcasts.ui.data.TimecodeData
import com.vezdekod.mobilepodcasts.ui.data.TimecodeDataSource

class AudioEditingViewModel: ViewModel() {

    val timecodesLiveData = MutableLiveData<TimecodeDataSource>()
    private val timecodes = TimecodeDataSource()

    init {
        timecodesLiveData.value = timecodes
    }

    fun addTimecode() {
        timecodes.add(TimecodeData("", ""))
        timecodesLiveData.value = timecodes
    }
}