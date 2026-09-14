package com.example.androidpathaoparceljourney.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.androidpathaoparceljourney.R
import com.example.androidpathaoparceljourney.ui.merchant.MerchantHomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bg: RelativeLayout = findViewById<RelativeLayout>(R.id.main)
        val txt: TextView = findViewById<TextView>(R.id.pathao_Journey_text)

        lifecycleScope.launch {
            repeat(3) {
                bg.setBackgroundColor(Color.BLACK)
                txt.setTextColor(Color.WHITE)
                delay(750)
                bg.setBackgroundColor(Color.WHITE)
                txt.setTextColor(Color.BLACK)
                delay(750)
            }
            startActivity(Intent(this@SplashScreenActivity, MerchantHomeActivity::class.java))
            finish()
        }


    }
}