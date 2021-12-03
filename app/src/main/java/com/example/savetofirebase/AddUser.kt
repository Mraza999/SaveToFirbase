package com.example.savetofirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_adduser.*


class AddUser : AppCompatActivity() {

    private lateinit var mydatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adduser)


        add_btn.setOnClickListener {

            val firstName = First_Name_et.text.toString()
            val lastName  = Last_Name_et.text.toString()
            val age = Age_et.text.toString()
            val email = Email_et.text.toString()

            mydatabase = FirebaseDatabase.getInstance().getReference("")
            val primaryKey= databaseRefrence?.child("User")?.push()?.key ?: return

            val UserObj = ModalUser(firstName,lastName,age,email)

            mydatabase.child(User).child(primaryKey).setValue(UserObj).addOnSuccessListener {

                // TO CLEAR FIELD AFTER SAVING DATA
                First_Name_et.text.clear()
                Last_Name_et.text.clear()
                Age_et.text.clear()
                Email_et.text.clear()

                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

                showModalBottomSheet()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun showModalBottomSheet()
    {
        val modalBS = BottomSheetDialog(this)

        modalBS.setContentView(R.layout.success_layout)


        modalBS.show()
    }
}