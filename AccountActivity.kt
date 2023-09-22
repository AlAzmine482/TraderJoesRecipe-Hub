package com.example.traderjoes20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounting)

        val buttontoPantrySettings = findViewById<Button>(R.id.pantrysettings)
        buttontoPantrySettings.setOnClickListener{
            val intent = Intent(this, settings::class.java)
            startActivity(intent)
        }
    }
}