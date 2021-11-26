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

class SecondFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null

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
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: Called")
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonSwitchToFirst).setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
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
        mediaPlayer?.stop()
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Called")
        super.onDestroy()
    }

    companion object {
        private const val TAG = "SecondFragment"
    }
}
