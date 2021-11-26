package com.chaubacho.basicandroid

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: Called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val list = listOf("Tran", "Dinh", "Vinh")
        val adapter = Adapter(list)
        val manager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        recyclerView.layoutManager = manager
        recyclerView.adapter = adapter
    }

    override fun onStart() {
        Log.d(TAG, "onStart: Called")
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
        super.onDestroy()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
