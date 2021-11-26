package com.chaubacho.basicandroid

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: Called")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: Called")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: Called")
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.buttonSwitchToSecond).setOnClickListener {
            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.addToBackStack(null)
                ?.replace(R.id.fragmentContainer, SecondFragment())
                ?.commit()
        }

        savedInstanceState?.let {
            position = it.getInt("position")
            Log.d(TAG, "onCreate: $position")
        }

        mediaPlayer = MediaPlayer()
        val uri = Uri.parse("android.resource://" + activity?.packageName + "/" + R.raw.buocquanhau)
        try {
            mediaPlayer?.let {
                context?.let { c -> it.setDataSource(c, uri) }
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
        super.onResume()
        mediaPlayer?.let {
            if (it.isPlaying) {
                Log.d(TAG, "onStart: Media playing")
            }
        }
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
        private const val TAG = "FirstFragment"
    }
}