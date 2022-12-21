package com.example.wholeprojectfiles

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)


        var apply_btn = findViewById<Button>(R.id.change_pp_btn)

        var token = getSharedPreferences("Username", Context.MODE_PRIVATE)
        var hideml=token?.getString("usreml",null).toString().trim()
        var hidpass=token?.getString("userpas",null).toString().trim()


        var auth= FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(hideml,hidpass).addOnCompleteListener(object : OnCompleteListener<AuthResult> {
            override fun onComplete(p0: Task<AuthResult>) {

                if(p0.isSuccessful)
                {
                    apply_btn.setOnClickListener {

                            auth.sendPasswordResetEmail(hideml)
                            Toast.makeText(applicationContext,"Password Reset Link Sent To Registered Email",
                                Toast.LENGTH_SHORT).show()
                            var editor = token.edit()
                            editor.putString("LoginRemember","")
                            editor.commit()
                            startActivity(Intent(this@password,MainActivity::class.java))

                    }
                }
                else
                {
                    var error = p0.exception?.message
                    Toast.makeText(applicationContext,error, Toast.LENGTH_SHORT).show()
                }
            }
        })

    } // END OF MAIN METHOD
} // END OF CLASS