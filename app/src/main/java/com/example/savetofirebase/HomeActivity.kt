package com.example.savetofirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<ModalUser>

    lateinit var AccessAdap : MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userRecyclerView = findViewById(R.id.recyclerView_id)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<ModalUser>()


        // FUNCTION FOR GETTING DATA FROM FIREBASE
        getUserData()



        searchBar_home.setOnClickListener {

            val i = Intent(this,SearchActivity::class.java)

            // To Remove very previous Activity
           // i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(i)
        }



        HomeFloatingActionButton.setOnClickListener {

            val i = Intent(this,AddUser::class.java)

            // To Remove very previous Activity
            // i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(i)
        }
    }


    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance().getReference("")

        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists())
                {
                    for(userSnapshot in snapshot.children){

                        val user = userSnapshot.getValue(ModalUser::class.java)
                        userArrayList.add(user!!)
                    }

                    userRecyclerView.adapter = MyAdapter(userArrayList)
                }

               AccessAdap.UserList(userArrayList)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }
}