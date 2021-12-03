package com.example.savetofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.show_details_bs.*

class SearchActivity : AppCompatActivity() {
    private lateinit var mydb : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        ReadButton.setOnClickListener {


            val gotUsername = SearchUser_ET.text.toString()


            if (gotUsername.isNotEmpty())
            {

                // Function
                readData(gotUsername)

            }

            else
            {
                Toast.makeText(this, "Please enter the Username", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun readData(gotUsername: String) {

        mydb = FirebaseDatabase.getInstance().getReference("")
        mydb.child(gotUsername).get().addOnSuccessListener {


            if(it.exists())
            {
                val gotFirstname = it.child("firstName").value
                val gotLastname  = it.child("lastName").value
                val gotAge = it.child("age").value
                val gotEmail = it.child("email").value


                Toast.makeText(this, "Successfully Read", Toast.LENGTH_SHORT).show()


                // CLEAR SEARCH BAR
                   SearchUser_ET.text.clear()

                // SET DATA TO SHOW
                findViewById<TextView>(R.id.show_Firstname_ET).text = gotFirstname.toString()

            //  show_Firstname_ET.text = gotFirstname.toString()
                show_Lastname_ET.text = gotLastname.toString()
                show_Age_ET.text = gotAge.toString()
                show_Email_ET.text = gotEmail.toString()


                // FUNCTION FOR BOTTOM SHEET
               // showDetailsBottomSheet()

               // (gotFirstname.toString() + gotLastname.toString() + gotAge.toString() + gotEmail.toString()).also { showInOne.text = it }

            }
            else
            {
                Toast.makeText(this, "Can't find the User", Toast.LENGTH_SHORT).show()
            }


        }.addOnFailureListener {

            Toast.makeText(this, "Failed to connect", Toast.LENGTH_SHORT).show()
        }

    }

    private fun showDetailsBottomSheet()
    {
        val modalBS = BottomSheetDialog(this)

        val view = layoutInflater.inflate(R.layout.show_details_bs, null)

        modalBS.setContentView(view)

        modalBS.show()
    }
}