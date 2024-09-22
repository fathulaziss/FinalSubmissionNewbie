package com.example.finalsubmissionnewbie

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DestinationDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DESTINATION = "extra_destination"
    }

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_destination_detail)
        // showing the back button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvDataReceived: TextView = findViewById(R.id.tv_data_received)
        val tvDataPhoto: ImageView = findViewById(R.id.tv_data_photo)

        val destination = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DESTINATION, Destination::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DESTINATION)
        }

        if (destination != null) {
            val text = "Name : ${destination.name}\nDescription : ${destination.description}"
            tvDataPhoto.setImageResource(destination.photo)
            tvDataReceived.text = text
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.destination_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share_content -> {
                Toast.makeText(this, "Share This Content", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}