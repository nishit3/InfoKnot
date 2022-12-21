package com.example.wholeprojectfiles

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [mainmenu_component_profile.newInstance] factory method to
 * create an instance of this fragment.
 */
class mainmenu_component_profile : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout=inflater.inflate(R.layout.fragment_mainmenu_component_profile, container, false)


        var ppicshow = layout.findViewById<CircleImageView>(R.id.account_pp_show)
        var username = layout.findViewById<Button>(R.id.profile_username)
        var email = layout.findViewById<Button>(R.id.profile_email)
        var phonenum = layout.findViewById<Button>(R.id.profile_num)
        var university = layout.findViewById<Button>(R.id.profile_university)
        var pp_change = layout.findViewById<Button>(R.id.profile_pp_change)
        var password = layout.findViewById<Button>(R.id.profile_pass)
        var hiddenusername:String?=null
        var hiddenuseremail:String?=null


        var SP = this.activity?.getSharedPreferences("sharedusercreds",Context.MODE_PRIVATE)
        var userUID=SP?.getString("UID","null")



        // Parsing username, Profile Photo and email here
        var dbref = FirebaseDatabase.getInstance("https://infoknot-18e3f-default-rtdb.asia-southeast1.firebasedatabase.app/")
        dbref.getReference("User Account Info").child(userUID!!).addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists())
                {
                    hiddenuseremail=snapshot.child("UserEmail").value.toString()
                    hiddenusername=snapshot.child("UserName").value.toString()
                    var photoReference = FirebaseStorage.getInstance("gs://infoknot-18e3f.appspot.com").getReference("UserProfilePicture").child(userUID.toString())
                    val ONE_MEGABYTE = (1024 * 1024).toLong()
                    photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(OnSuccessListener<ByteArray> { bytes ->
                            val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                            ppicshow.setImageBitmap(bmp)
                        })
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // handles error
            }
        })
        // Parsed username, ProfilePhoto and email for Profile Fragment!!


       username.setOnClickListener {
        startActivity(Intent(this.activity,name::class.java))
       }
       email.setOnClickListener {
        startActivity(Intent(this.activity,emailaddress::class.java))
      }
       phonenum.setOnClickListener {
           startActivity(Intent(this.activity,mobile_no::class.java))
       }
       university.setOnClickListener {
           startActivity(Intent(this.activity,com.example.wholeprojectfiles.university::class.java))
       }
       pp_change.setOnClickListener {
           startActivity(Intent(this.activity,profilephotochange::class.java))
       }
       password.setOnClickListener {
           startActivity(Intent(this.activity,com.example.wholeprojectfiles.password::class.java))
       }

        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment mainmenu_component_profile.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            mainmenu_component_profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}