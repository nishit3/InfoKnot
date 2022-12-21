package com.example.wholeprojectfiles

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.VideoView
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    var email_user_input:EditText?=null
    var pass_user_input:EditText?=null
    var login_button:Button?=null
    var forget_password:Button?=null
    var auth:FirebaseAuth?=null
    var createacc:Button?=null
    var logoplay:VideoView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var SP = getSharedPreferences("sharedusercreds",Context.MODE_PRIVATE)
        var userUID=SP.getString("UID","null").toString()
        var check:DatabaseReference = FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User Account Info").child(userUID)
        check.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              var hehe=snapshot.child("Access").value.toString().trim()
                if(hehe.equals("deny")||hehe.equals("Deny")||hehe.equals("DENY"))
                {
                    exitProcess(-1)
                }
            }
            override fun onCancelled(error: DatabaseError) {
               // handles error
            }
        })




        email_user_input=findViewById(R.id.signup_user_email_field)
        pass_user_input=findViewById(R.id.signup_user_pass_field)
        login_button=findViewById(R.id.signup_btn)
        forget_password=findViewById(R.id.alreadyhaveacc_btn)
        createacc=findViewById(R.id.createacc_btn)
        auth= FirebaseAuth.getInstance()
        logoplay=findViewById(R.id.logo_player)


        var token = getSharedPreferences("Username",Context.MODE_PRIVATE)
        if(token.getString("LoginRemember",null).equals("true"))
        {
            sendtohomeactivity()
            finish()
        }

        playlogo()

        login_button?.setOnClickListener {
            login_testandstart()
        }
        forget_password?.setOnClickListener {
            forgetpass()
        }
        createacc?.setOnClickListener {
            sendtosignup()
        }


    }

    fun login_testandstart() {
        if(email_user_input?.text!!.isEmpty()&&pass_user_input?.text!!.isEmpty())
        {
            Toast.makeText(applicationContext, "Email And Password Field's Are Empty!", Toast.LENGTH_SHORT).show()
        }
        else if (email_user_input?.text!!.isEmpty()) {
            Toast.makeText(applicationContext, "Email Field Is Empty!", Toast.LENGTH_SHORT).show()
        } else if (pass_user_input?.text!!.isEmpty()) {
            Toast.makeText(applicationContext, "Password Field Is Empty!", Toast.LENGTH_SHORT).show()
        }
        else
        {
            verifyandlogin()
        }
    }

    fun verifyandlogin()
    {
        var email = email_user_input?.text.toString().trim()
        var pass = pass_user_input?.text.toString().trim()

                auth?.signInWithEmailAndPassword(email,pass)?.addOnCompleteListener(object : OnCompleteListener<AuthResult> {
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful) {
                            if(auth?.currentUser!!.isEmailVerified) {
                                var token = getSharedPreferences("Username",Context.MODE_PRIVATE)
                                var editor = token?.edit()
                                editor?.putString("LoginRemember","true")
                                editor?.putString("usreml",auth?.currentUser!!.email.toString().trim())
                                editor?.putString("userpas",pass)
                                editor?.commit()
                                var SP = getSharedPreferences("sharedusercreds",Context.MODE_PRIVATE)
                                var editSP = SP.edit()
                                editSP.putString("UID",auth?.currentUser!!.uid)
                                editSP.commit()
                                var rfrnc = FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User Account Info").child(auth?.currentUser!!.uid).child("UserEmail")
                                rfrnc.setValue(auth?.currentUser!!.email.toString())
                                Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                                sendtohomeactivity()
                            }
                            else
                            {
                                Toast.makeText(applicationContext,"Email Not Verified, Please Check For Verification Email", Toast.LENGTH_SHORT).show()
                            }

                        } else {
                            val error = p0.exception!!.message
                            Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
    }


    fun forgetpass()
    {

        if (email_user_input?.text!!.isEmpty()) {
            Toast.makeText(applicationContext, "Email Field Is Empty!", Toast.LENGTH_SHORT).show()
        }
        else
        {
            var emaill = email_user_input?.text.toString().trim()
            auth?.sendPasswordResetEmail(emaill)?.addOnCompleteListener(object : OnCompleteListener<Void>{
                override fun onComplete(p0: Task<Void>) {

                    if(p0.isSuccessful)
                    {
                        Toast.makeText(applicationContext, "Password Reset Link Sent", Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        val error = p0.exception!!.message
                        Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }


    fun sendtosignup()
    {
     startActivity(Intent(this@MainActivity,signup::class.java))
    }
    fun sendtohomeactivity()
    {
        startActivity(Intent(this@MainActivity,mainmenu::class.java))
        finish()
    }
   fun playlogo()
   {
       var logo_uri = Uri.parse("android.resource://"+packageName+"/"+R.raw.infoknotlogovid)
       logoplay?.setVideoURI(logo_uri)
       logoplay?.start()
       logoplay?.setOnPreparedListener({mp -> mp.isLooping =true})
   }

    override fun onPause() {
        logoplay?.pause()
        super.onPause()
    }

    override fun onRestart() {
        logoplay?.start()
        super.onRestart()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


} // end of class