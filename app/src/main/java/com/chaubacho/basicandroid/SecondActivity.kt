package com.chaubacho.basicandroid

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: Called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.buttonBackToMain).setOnClickListener {
            finish()
        }

        mediaPlayer = MediaPlayer()
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.laaitubo)
        try {
            mediaPlayer?.let {
                it.setDataSource(applicationContext, uri)
                it.prepare()
                Log.d(TAG, "onCreate: Started")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        Log.d(TAG, "onStart: Called")
        mediaPlayer?.let {
            if (!it.isPlaying) {
                    it.start()
            } else {
                Log.d(TAG, "onStart: Media playing")
            }
        }
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: Called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: Called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: Called")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Called")
        mediaPlayer?.stop()
        mediaPlayer = null
        super.onDestroy()
    }

    companion object {
        private const val TAG = "SecondActivity"
    }
}
