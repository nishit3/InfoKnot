package com.example.wholeprojectfiles

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class contactus_settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contactus_settings)
        var SP = getSharedPreferences("sharedusercreds", Context.MODE_PRIVATE)
        var userUID=SP.getString("UID",null).toString()
        var ref = FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User Account Info").child(userUID).child("Issue")
        var submit = findViewById<Button>(R.id.submit)
        var clear = findViewById<Button>(R.id.clear)
        var askq =  findViewById<EditText>(R.id.askq)
        var respons = findViewById<TextView>(R.id.response)
        var send_email = findViewById<Button>(R.id.send_email)


      ref.addValueEventListener(object : ValueEventListener{
          override fun onDataChange(snapshot: DataSnapshot) {
             var currentissue = snapshot.child("CurrentIssue").value.toString()
              if(!(currentissue.equals("null")))
              {
                  submit.visibility=View.INVISIBLE
                  clear.visibility=View.VISIBLE
                  askq.visibility=View.INVISIBLE
                  respons.visibility=View.VISIBLE
                  respons.text =snapshot.child("Response").value.toString()
              }
              else
              {
                  submit.visibility=View.VISIBLE
                  clear.visibility=View.INVISIBLE
                  askq.visibility=View.VISIBLE
                  respons.visibility=View.INVISIBLE
              }
          }
          override fun onCancelled(error: DatabaseError) {
              // handles error
          }
      })
        clear.setOnClickListener {
            submit.visibility=View.VISIBLE
            clear.visibility=View.INVISIBLE
            respons.visibility=View.INVISIBLE
            askq.visibility=View.VISIBLE
            ref.child("CurrentIssue").removeValue()
            ref.child("Response").removeValue()
            askq.text.clear()
        }
        submit.setOnClickListener {
            if(askq.text.isEmpty())
            {
                Toast.makeText(this,"Question/Issue Field Can't Be Empty",Toast.LENGTH_SHORT).show()
            }
            else
            {
                submit.visibility=View.INVISIBLE
                clear.visibility=View.VISIBLE
                askq.visibility=View.INVISIBLE
                respons.visibility=View.VISIBLE
                var issuehash =HashMap<String,Any>()
                issuehash.put("CurrentIssue",askq.text.toString().trim())
                issuehash.put("Response","Response Will Be Shown Here, Kindly Wait.")
                ref.setValue(issuehash)
            }
        }
        send_email.setOnClickListener {
            startActivity(Intent(this,sendemail::class.java))
        }
    }
}