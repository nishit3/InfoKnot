package com.example.wholeprojectfiles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [setting.newInstance] factory method to
 * create an instance of this fragment.
 */
class setting : Fragment() {
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
        var layout = inflater.inflate(R.layout.fragment_setting, container, false)


        var notification = layout.findViewById<Button>(R.id.notification)
        var help = layout.findViewById<Button>(R.id.help)
        var shareapp = layout.findViewById<Button>(R.id.shareapp)
        var theme = layout.findViewById<Button>(R.id.theme)
        var security = layout.findViewById<Button>(R.id.security)


        notification.setOnClickListener {
         startActivity(Intent(this.activity,notifications::class.java))
        }

        help.setOnClickListener {
            startActivity(Intent(this.activity,help_settings::class.java))
        }



        shareapp.setOnClickListener {
            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "InfoKnot")
                var shareMessage = "\nAll in one app for fulfilling your scientific research,news,articles needs. Which even keeps you updated with major scientific event's happening around globe!\n\n"
                shareMessage =
                    """
                 ${shareMessage}https://drive.google.com/drive/folders/1IJoMv1Mg1DCWxxophOMDH3s7Z3SS-1-B?usp=sharing
                 
                 
                 """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                Toast.makeText(this.activity, e.message,Toast.LENGTH_LONG).show()
            }
        }

//        theme.setOnClickListener {
//
//        }

//        security.setOnClickListener {
//
//        }





















        return layout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment setting.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            setting().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}