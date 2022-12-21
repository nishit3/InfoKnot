package com.example.wholeprojectfiles

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView
import kotlin.random.Random


class mainmenu : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle
    var toolbar:Toolbar?=null
    lateinit var drawerLayout:DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainmenu)


        toolbar=findViewById(R.id.app_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawerLayout = findViewById(R.id.Drawer)
        var navview = findViewById<NavigationView>(R.id.navig)
        var header = navview.getHeaderView(0)


        var headeremail = header.findViewById<TextView>(R.id.menu_header_useremail_show)          // header things
        var headerusername = header.findViewById<TextView>(R.id.menu_header_username_show)
        var headerPP=header.findViewById<CircleImageView>(R.id.menu_header_img_show)
        var SP = getSharedPreferences("sharedusercreds",Context.MODE_PRIVATE)
        var userUID=SP.getString("UID","null")


        // Parsing username, Profile Photo and email here
         var dbref = FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/")
         dbref.getReference("User Account Info").child(userUID!!).addValueEventListener(object:ValueEventListener{
             override fun onDataChange(snapshot: DataSnapshot) {
                 if(snapshot.exists())
                 {
                     headeremail.text=snapshot.child("UserEmail").value.toString()
                     headerusername.text=snapshot.child("UserName").value.toString()
                     var photoReference = FirebaseStorage.getInstance("gs://infoknot-18e3f.appspot.com").getReference("UserProfilePicture").child(userUID.toString())
                     val ONE_MEGABYTE = (1024 * 1024).toLong()
                     photoReference.getBytes(ONE_MEGABYTE)
                         .addOnSuccessListener(OnSuccessListener<ByteArray> { bytes ->
                             val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                             headerPP.setImageBitmap(bmp)
                         })
                 }
             }
             override fun onCancelled(error: DatabaseError) {
                 // handles error
             }

         })
       // Parsed username, ProfilePhoto and email for header !!



        toggle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        var toolbartitlechange:String="               InfoKnot"
        ReplaceMMfragments(mainhome(),toolbartitlechange)



        navview.setNavigationItemSelectedListener {
            it.isChecked=true                                            // for highlighting the select
            when(it.itemId)
            {
              R.id.mmenu_profile -> {
                  var toolbartitl:String = it.title.toString()
                  var toolbartitlechange:String="               "+toolbartitl
                  ReplaceMMfragments(mainmenu_component_profile(),toolbartitlechange)
                                    }

                R.id.mmenu_settings -> {
                var toolbartitl:String = it.title.toString()
                var toolbartitlechange:String="               "+toolbartitl
                ReplaceMMfragments(setting(),toolbartitlechange)
                                       }
                R.id.mmenu_news -> {
                    var toolbartitl:String = it.title.toString()
                    var toolbartitlechange:String="               "+toolbartitl
                    ReplaceMMfragments(news(),toolbartitlechange)
                }
                R.id.mmenu_articles -> {
                    var toolbartitl:String = it.title.toString()
                    var toolbartitlechange:String="               "+toolbartitl
                    ReplaceMMfragments(articless(),toolbartitlechange)
                }
                R.id.mmenu_research -> {
                    var toolbartitl:String = it.title.toString()
                    var toolbartitlechange:String="               "+toolbartitl
                    ReplaceMMfragments(researchh(),toolbartitlechange)
                }
                R.id.mmenu_aboutus -> {
                    var toolbartitl:String = it.title.toString()
                    var toolbartitlechange:String="               "+toolbartitl
                    ReplaceMMfragments(aboutus(),toolbartitlechange)
                }
                R.id.mmenu_events -> {
                    var toolbartitl: String = it.title.toString()
                    var toolbartitlechange: String = "               " + toolbartitl
                    ReplaceMMfragments(events(), toolbartitlechange)
                }

            }
             true
        }


    }   // END  OF  MAIN  METHOD


    private fun ReplaceMMfragments(fragment: Fragment,toolbartitle:String)
    {
          val fragmentManager = supportFragmentManager.beginTransaction()
          fragmentManager.replace(R.id.mainmenuframe,fragment)
          fragmentManager.commit()
          drawerLayout.closeDrawers()
          toolbar!!.setTitle(toolbartitle)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}  // END  OF  CLASS
