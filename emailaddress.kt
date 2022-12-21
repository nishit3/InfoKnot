package com.example.wholeprojectfiles

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class emailaddress : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emailaddress)


        var newemailentry = findViewById<TextView>(R.id.email_add)
        var apply_btn = findViewById<Button>(R.id.change_pp_btn)

        var token = getSharedPreferences("Username", Context.MODE_PRIVATE)
        var hideml=token?.getString("usreml",null).toString().trim()
        var hidpass=token?.getString("userpas",null).toString().trim()
        var SP = getSharedPreferences("sharedusercreds",Context.MODE_PRIVATE)
        var userUID=SP.getString("UID",null).toString()
        var check: DatabaseReference = FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User Account Info").child(userUID)
        check.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                newemailentry.text=snapshot.child("UserEmail").value.toString().trim()
            }
            override fun onCancelled(error: DatabaseError) {
                // handles error
            }
        })

           var auth=FirebaseAuth.getInstance()
           auth.signInWithEmailAndPassword(hideml,hidpass).addOnCompleteListener(object :OnCompleteListener<AuthResult>{
           override fun onComplete(p0: Task<AuthResult>) {

              if(p0.isSuccessful)
              {
               apply_btn.setOnClickListener {
                   if(newemailentry.text.toString().isEmpty())
                   {
                       Toast.makeText(applicationContext,"New Email Cant Be Empty",Toast.LENGTH_SHORT).show()
                   }
                   else
                   {
                       auth.currentUser?.verifyBeforeUpdateEmail(newemailentry.text.toString().trim())
                       Toast.makeText(applicationContext,"Verification Email Sent On New Email Address",Toast.LENGTH_SHORT).show()
                       var editor = token.edit()
                       editor.putString("LoginRemember","")
                       editor.commit()
                       Toast.makeText(applicationContext,"Email Changed Successfully",Toast.LENGTH_SHORT).show()
                       startActivity(Intent(this@emailaddress,MainActivity::class.java))
                   }
               }
              }
               else
              {
                  var error = p0.exception?.message
                  Toast.makeText(applicationContext,error,Toast.LENGTH_SHORT).show()
              }
           }
       })

    }// END OF MAIN METHOD
} // END OF CLASS