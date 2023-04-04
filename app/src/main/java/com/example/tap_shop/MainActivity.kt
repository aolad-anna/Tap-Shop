package com.example.tap_shop

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tap_shop.databinding.ActivityMainBinding
import com.example.tap_shop.network.InternetConnection


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (InternetConnection.isOnline(this@MainActivity)){
            Looper.myLooper()?.let {
                Handler(it).postDelayed({
                    val intent = Intent(baseContext, NavActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1500)
            }
        }
        else{
            Toast.makeText(this, "Check Internet Connection!", Toast.LENGTH_SHORT).show()
        }

    }
}