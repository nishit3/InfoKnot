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

class mobile_no : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobile_no)

        var mobilenumber = findViewById<TextView>(R.id.mobile_number)
        var applychange_mobnum__btn = findViewById<Button>(R.id.apply_change_university)
        var SP = getSharedPreferences("sharedusercreds", Context.MODE_PRIVATE)
        var userUID=SP?.getString("UID","null")

        var dbref = FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/")
        dbref.getReference("User Account Info").child(userUID!!).addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    mobilenumber.text=snapshot.child("PhoneNumber").value.toString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // handles error
            }
        })

        applychange_mobnum__btn.setOnClickListener {
            if(mobilenumber.text.toString().isEmpty())
            {
                Toast.makeText(applicationContext,"PhoneNumber Field Can't Be Empty", Toast.LENGTH_SHORT).show()
            }
            else
            {
                dbref.getReference("User Account Info").child(userUID).child("PhoneNumber").setValue(mobilenumber.text.toString().trim())
                Toast.makeText(applicationContext,"PhoneNumber Updated", Toast.LENGTH_SHORT).show()
            }
        }

    } // END OF MAIN METHOD
} // END OF CLASS