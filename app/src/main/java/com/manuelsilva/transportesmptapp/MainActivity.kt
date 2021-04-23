package com.manuelsilva.transportesmptapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
  private val _splashScreen = 4000

  lateinit var topAnimation : Animation
  lateinit var bottomAnimation: Animation

  lateinit var imageView: ImageView
  lateinit var welcome: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    setContentView(R.layout.activity_main)

    // Animations
    topAnimation = AnimationUtils.loadAnimation( this, R.anim.top_animation)
    bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

    // Hooks
    imageView = findViewById(R.id.ivLogo)
    welcome = findViewById(R.id.tvWelcomeMessage)

    imageView.animation = topAnimation
    welcome.animation = bottomAnimation

    Handler(Looper.getMainLooper()).postDelayed({
      val intent = Intent(this@MainActivity, HomeActivity::class.java)
      startActivity(intent)
      finish()
    }, _splashScreen.toLong())
  }
}