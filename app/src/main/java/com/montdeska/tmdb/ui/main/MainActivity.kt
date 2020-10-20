package com.montdeska.tmdb.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.montdeska.tmdb.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main)
    }
}