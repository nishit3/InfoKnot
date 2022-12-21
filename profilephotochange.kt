package com.example.wholeprojectfiles

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView


class profilephotochange : AppCompatActivity() {

    var profile_show:CircleImageView?=null
    lateinit var imguri:Uri
    var isnewimgselected:Boolean=false
    var backbool:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilephotochange)
        profile_show = findViewById<CircleImageView>(R.id.account_pp_show)
        var select_pp_btn = findViewById<Button>(R.id.selectprofileimg_btn)
        var apply_change_btn=findViewById<Button>(R.id.change_pp_btn)
        var fbstrgref= FirebaseStorage.getInstance("gs://infoknot-18e3f.appspot.com")
        var PPfbstrgref= fbstrgref.getReference().child("UserProfilePicture")
        var SP = getSharedPreferences("sharedusercreds", Context.MODE_PRIVATE)
        var userUID=SP.getString("UID",null).toString()

       var imgbolref= FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("User Account Info").child(userUID)
       imgbolref.addValueEventListener(object:ValueEventListener{
           override fun onDataChange(snapshot: DataSnapshot) {
               var boool = snapshot.child("ProfilePicSelected?").value.toString()
               if(boool.equals("Yes"))
               {
                   var photoReference = PPfbstrgref.child(userUID)
                   val ONE_MEGABYTE = (1024 * 1024).toLong()
                   photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(OnSuccessListener<ByteArray> { bytes ->
                       val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                       profile_show?.setImageBitmap(bmp)
                   })
               }
               else
               {
                   var defaultimguri = Uri.parse("android.resource://"+packageName+"/"+R.drawable.infoknotlogo)
                   profile_show?.setImageURI(defaultimguri)
               }
           }

           override fun onCancelled(error: DatabaseError) {
             // handles error
           }

       })


        select_pp_btn.setOnClickListener{
            selectimagefromgallary()
        }
        apply_change_btn.setOnClickListener {
            if(isnewimgselected)
            {
                imgbolref.child("ProfilePicSelected?").setValue("Yes")
                PPfbstrgref.child(userUID).delete()
                PPfbstrgref.child(userUID).putFile(imguri)
                Toast.makeText(applicationContext, "Profile Photo Changed", Toast.LENGTH_SHORT).show()
                backbool = true
            }
            else
            {
                Toast.makeText(applicationContext, "Please Select New Photo First", Toast.LENGTH_SHORT).show()
            }
        }

    } // END OF MAIN METHOD

    fun selectimagefromgallary()
    {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent, signup.IMAGE_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== signup.IMAGE_REQ_CODE && resultCode==RESULT_OK)
        {
            imguri= data?.data!!
            profile_show?.setImageURI(imguri)
            isnewimgselected=true
        }
    }

    override fun onBackPressed() {
        if(backbool)
        {
            super.onBackPressed()
            startActivity(Intent(this, mainmenu::class.java))
            finish()
        }
        else
        {
            super.onBackPressed()
        }
    }





} // END OF CLASS