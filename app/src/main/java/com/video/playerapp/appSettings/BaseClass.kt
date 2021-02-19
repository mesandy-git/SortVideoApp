package com.video.playerapp.appSettings

import android.util.Log
import androidx.appcompat.app.AppCompatActivity

open class BaseClass : AppCompatActivity() {
    private val TAG: String = this.javaClass.simpleName

    override fun onNightModeChanged(mode: Int) {
        super.onNightModeChanged(mode)
        Log.d(TAG, "onNightModeChanged: ")
    }
}