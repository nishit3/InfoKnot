package com.example.wholeprojectfiles

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class university : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_university)

        var university = findViewById<TextView>(R.id.university_change)
        var univ_change_btn = findViewById<Button>(R.id.apply_change_university)
        var SP = getSharedPreferences("sharedusercreds", Context.MODE_PRIVATE)
        var userUID=SP?.getString("UID","null")

        var dbref = FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/")
        dbref.getReference("User Account Info").child(userUID!!).addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    university.text=snapshot.child("UniversityName").value.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // handles error
            }
        })

        univ_change_btn.setOnClickListener {
            if(university.text.toString().isEmpty())
            {
                Toast.makeText(applicationContext,"University Field Can't Be Empty", Toast.LENGTH_SHORT).show()
            }
            else
            {
                dbref.getReference("User Account Info").child(userUID).child("UniversityName").setValue(university.text.toString().trim())
                Toast.makeText(applicationContext,"University Updated", Toast.LENGTH_SHORT).show()
            }
        }


    }
}