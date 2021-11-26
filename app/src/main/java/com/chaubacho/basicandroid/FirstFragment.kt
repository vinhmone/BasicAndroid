package com.chaubacho.basicandroid

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: Called")
        super.onViewCreated(view, savedInstanceState)

        if (mediaPlayer == null ) mediaPlayer = MediaPlayer()
        val uri = Uri.parse("android.resource://" + activity?.packageName + "/" + R.raw.buocquanhau)
        mediaPlayer?.let {
            context?.let { c -> it.setDataSource(c, uri) }
            it.prepare()
            Log.d(TAG, "mediaPlayer: Started")
        }
    }

    override fun onStart() {
        Log.d(TAG, "onStart: Called________________________")
        super.onStart()
        mediaPlayer?.apply {
            start()
            seekTo(position)
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            position = it.getInt("position")
            Log.d(TAG, "onCreate: $position")
        }
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

    override fun onSaveInstanceState(outState: Bundle) {
        mediaPlayer?.let {
            position = it.currentPosition
            Log.d(TAG, "onSaveInstanceState: $position")
            outState.putInt("position", position)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Called")
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    companion object {
        private const val TAG = "FirstFragment"
    }
}
