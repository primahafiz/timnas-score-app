package com.example.timnasscore

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var ivFootball : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        ivFootball = findViewById(R.id.iv_football)
        ivFootball.alpha=0f
        ivFootball.animate().setDuration(1500).alpha(1f).withEndAction {
            val mainIntent = Intent(this,MainActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }

    }
}