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

class name : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)


        var username = findViewById<TextView>(R.id.university_change)
        var applychange = findViewById<Button>(R.id.apply_change_university)
        var SP = getSharedPreferences("sharedusercreds", Context.MODE_PRIVATE)
        var userUID=SP?.getString("UID","null")

        var dbref = FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/")
        dbref.getReference("User Account Info").child(userUID!!).addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    username.text=snapshot.child("UserName").value.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // handles error
            }
        })

        applychange.setOnClickListener {
         if(username.text.toString().isEmpty())
         {
             Toast.makeText(applicationContext,"Username Field Can't Be Empty",Toast.LENGTH_SHORT).show()
         }
            else
         {
             dbref.getReference("User Account Info").child(userUID!!).child("UserName").setValue(username.text.toString().trim())
             Toast.makeText(applicationContext,"Username Updated",Toast.LENGTH_SHORT).show()
         }
        }

    } // END OF MAIN METHORD

} // END OF CLASS