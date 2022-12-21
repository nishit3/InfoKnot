package com.example.wholeprojectfiles

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView


class signup : AppCompatActivity() {

    var username:EditText?=null                                                    // declaring variables
    var phonenumber:EditText?=null
    var univname:EditText?=null
    var user_entered_email:EditText?=null
    var user_entered_pass:EditText?=null
    var upload_pp_btn:Button?=null
    var signup:Button?=null
    var alreadyhvacc:Button?=null
    var pp:CircleImageView?=null
    var fbauth:FirebaseAuth?=null
    var fbrtdb:FirebaseDatabase?=null
    var fbstrgref=FirebaseStorage.getInstance("gs://infoknot-18e3f.appspot.com")
    var PPfbstrgref= fbstrgref.getReference().child("UserProfilePicture")
    lateinit var fbimguri:Uri
    lateinit var imgfbname:String
    var isimgfetched:Boolean=false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        username=findViewById(R.id.Username)                                     // Init. Variables and fetching views
        phonenumber=findViewById(R.id.phonenumber)
        univname=findViewById(R.id.univname)
        user_entered_email=findViewById(R.id.signup_user_email_field)
        user_entered_pass=findViewById(R.id.signup_user_pass)
        upload_pp_btn=findViewById(R.id.selectprofileimg_btn)
        signup=findViewById(R.id.signup_btn)
        alreadyhvacc=findViewById(R.id.alreadyhaveacc_btn)
        pp=findViewById(R.id.account_pp_show)
        fbauth=FirebaseAuth.getInstance()
        fbrtdb=FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/")




        signup?.setOnClickListener {
            signup_testandstart()
        }

        alreadyhvacc?.setOnClickListener {
            sendtologin()
        }

        upload_pp_btn?.setOnClickListener {
            selectimagefromgallary()
        }

    }
    companion object{
        val IMAGE_REQ_CODE=111
    }

    fun signup_testandstart() {

        if(phonenumber?.text.toString().trim().isEmpty()||univname?.text.toString().trim().isEmpty()||username?.text.toString().trim().isEmpty())
        {
            Toast.makeText(applicationContext, "Please Fill All The Detail Fields", Toast.LENGTH_SHORT).show()
        }
        else if (user_entered_email?.text.toString().trim().isEmpty()) {
            Toast.makeText(applicationContext, "Email Field Is Empty!", Toast.LENGTH_SHORT).show()
        } else if (user_entered_pass?.text.toString().trim().isEmpty()) {
            Toast.makeText(applicationContext, "Password Field Is Empty!", Toast.LENGTH_SHORT).show()
        }
         if(phonenumber?.text.toString().trim().isEmpty()||univname?.text.toString().trim().isEmpty()||username?.text.toString().trim().isEmpty())
        {
            Toast.makeText(applicationContext, "Please Fill All The Detail Fields", Toast.LENGTH_SHORT).show()
        }
        else
        {
            signupUser()
        }
    }

    fun signupUser()
    {
        var t_user_entered_email=user_entered_email?.text.toString().trim()
        var t_user_entered_pass=user_entered_pass?.text.toString().trim()


        fbauth?.createUserWithEmailAndPassword(t_user_entered_email,t_user_entered_pass)?.addOnCompleteListener(object : OnCompleteListener<AuthResult> {
            override fun onComplete(p0: Task<AuthResult>) {

                if (p0.isSuccessful) {
                   fbauth?.currentUser!!.sendEmailVerification()?.addOnCompleteListener(object: OnCompleteListener<Void>{
                       override fun onComplete(p0: Task<Void>) {
                           if(p0.isSuccessful)
                           {
                               Toast.makeText(applicationContext,"Successfully SignedUp", Toast.LENGTH_SHORT).show()
                               Toast.makeText(applicationContext,"Verification E-Mail sent, Please verify and Login", Toast.LENGTH_SHORT).show()
                               pass_userinfo_to_rtdb()
                              if(isimgfetched)
                              {
                                  PPfbstrgref.child(fbauth?.currentUser!!.uid).putFile(fbimguri)
                                  fbrtdb?.getReference("User Account Info")?.child(fbauth?.currentUser!!.uid)?.child("ProfilePicSelected?")?.setValue("Yes")
                              }
                               else
                              {
                                  fbrtdb?.getReference("User Account Info")?.child(fbauth?.currentUser!!.uid)?.child("ProfilePicSelected?")?.setValue("No")
                              }
                               sendtologin()
                           }
                           else
                           {
                               val errorr = p0.exception!!.message
                               Toast.makeText(applicationContext, errorr, Toast.LENGTH_SHORT).show()
                           }
                       }
                   })
                }
                else {
                    val error = p0.exception!!.message
                    Toast.makeText(applicationContext, error, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

   fun pass_userinfo_to_rtdb()
   {
       var t_username=username?.text.toString().trim()
       var t_phonenumber=phonenumber?.text.toString().trim()
       var t_univname=univname?.text.toString().trim()
       var userinfo=HashMap<String,Any>()
       userinfo.put("UserName",t_username)
       userinfo.put("PhoneNumber",t_phonenumber)
       userinfo.put("UniversityName",t_univname)
       userinfo.put("Access","Allow")
       var rfrnc = fbrtdb?.getReference("User Account Info")?.child(fbauth?.currentUser!!.uid)?.setValue(userinfo)
   }

   fun sendtologin()
   {
       startActivity(Intent(this@signup,MainActivity::class.java))
       finish()
   }

    override fun onBackPressed() {
        finish()
    }

    fun selectimagefromgallary()
    {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,IMAGE_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== IMAGE_REQ_CODE && resultCode==RESULT_OK)
        {
            fbimguri= data?.data!!
            pp?.setImageURI(fbimguri)
            isimgfetched=true
        }
    }
        }    // end of class












