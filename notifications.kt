package com.example.wholeprojectfiles

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.findViewTreeOnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class notifications : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

     var notifs = findViewById<CheckBox>(R.id.onoffnotis)
     var recent = findViewById<CheckBox>(R.id.recentopic)
     var lockscrn = findViewById<CheckBox>(R.id.lockscrn)

        notifs.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

               Toast.makeText(this,"Notifications Will Not Be Shown",Toast.LENGTH_SHORT).show()

            }
            else {

                Toast.makeText(this,"Notifications Wil Be Shown",Toast.LENGTH_SHORT).show()
            }

        }
        recent.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                Toast.makeText(this,"Recent Topic Notifications Will Be Shown",Toast.LENGTH_SHORT).show()

            }
            else {

                Toast.makeText(this,"Recent Topic Notifications Will Not Show",Toast.LENGTH_SHORT).show()
            }

        }
        lockscrn.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {

                Toast.makeText(this,"Notifications Will Be Shown During Lockscreen",Toast.LENGTH_SHORT).show()

            }
            else {

                Toast.makeText(this,"Notifications Will Not Show During Lockscreen",Toast.LENGTH_SHORT).show()
            }

        }


    } // END OF MAIN METHOD




} // END OF CLASS


