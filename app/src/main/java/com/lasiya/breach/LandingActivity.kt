package com.lasiya.breach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.activity_register.*

class LandingActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        //actionBar!!.title = "Welcome!"
        //actionBar!!.setDisplayHomeAsUpEnabled(true)//check "!!" if error

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")
        loadProfile()
    }

    private fun loadProfile(){
        val user = auth.currentUser
        val UserReference = databaseReference?.child(user?.uid!!)

        EmailText.text = "Email -- > "+ user?.email
        UserReference?.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                NameText.text = "Name -- >"+snapshot.child("Name").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        logoutbtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@LandingActivity, LoginActivity::class.java))
            finish()
        }
    }
}
