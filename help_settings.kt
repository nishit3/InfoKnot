package com.example.wholeprojectfiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class help_settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help_settings)


        var tandc = findViewById<Button>(R.id.tandc)
        var contactus = findViewById<Button>(R.id.contactus)
        var privacypolicy = findViewById<Button>(R.id.privacypolicy)

        privacypolicy.setOnClickListener {
            startActivity(Intent(this,com.example.wholeprojectfiles.privacypolicy::class.java))
        }

        tandc.setOnClickListener {
            startActivity(Intent(this,com.example.wholeprojectfiles.tandc::class.java))
        }

        contactus.setOnClickListener {
         startActivity(Intent(this,contactus_settings::class.java))
        }

    }
}