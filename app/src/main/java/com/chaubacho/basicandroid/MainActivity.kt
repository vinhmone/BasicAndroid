package com.chaubacho.basicandroid

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: Called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonSwitchToSecond).setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        savedInstanceState?.let {
            position = it.getInt("position")
            Log.d(TAG, "onCreate: $position")
        }

        mediaPlayer = MediaPlayer()
        val uri = Uri.parse("android.resource://" + packageName + "/" + R.raw.buocquanhau)
        try {
            mediaPlayer?.let {
                it.setDataSource(applicationContext, uri)
                it.prepare()
                Log.d(TAG, "mediaPlayer: Started")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        Log.d(TAG, "onStart: Called")
        super.onStart()
        mediaPlayer?.let {
            if (!it.isPlaying) {
                if (position == 0) {
                    it.start()
                } else {
                    it.seekTo(position)
                    it.start()
                }
            } else {
                Log.d(TAG, "onStart: Media playing")
            }
        }

    }

    override fun onResume() {
        Log.d(TAG, "onResume: Called")
        mediaPlayer?.let {
            if (it.isPlaying) {
                Log.d(TAG, "onStart: Media playing")
            }
        }
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: Called")
        mediaPlayer?.let {
            position = it.currentPosition
            Log.d(TAG, "onPause: $position")
            it.pause()
        }
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: Called")
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        mediaPlayer?.let {
            position = it.currentPosition
            outState.putInt("position", position)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Called")
        mediaPlayer?.stop()
        mediaPlayer = null
        super.onDestroy()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
