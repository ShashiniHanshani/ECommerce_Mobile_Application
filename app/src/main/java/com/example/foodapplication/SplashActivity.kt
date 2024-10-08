package com.example.foodapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private val splashTimeOut: Long = 3000 // Duration to show the video (in milliseconds)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val videoView: VideoView = findViewById(R.id.videoView)
        val uri: Uri = Uri.parse("android.resource://${packageName}/${R.raw.shopping_cart}")
        videoView.setVideoURI(uri)

        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = false //set to false if you don't want the video to loop
            mediaPlayer.start()

            Handler().postDelayed({
                // Transition to MainActivity after 4 seconds
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Finish this activity so it can't be returned to
            }, splashTimeOut)
        }
    }
}
