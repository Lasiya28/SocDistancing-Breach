package com.lasiya.breach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()

        btnLogRegister.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }

    private fun register(){
        regButton.setOnClickListener{
            if(TextUtils.isEmpty(Name.text.toString())) {
                Name.error = "Please enter your Full Name"
                return@setOnClickListener
            }else if(TextUtils.isEmpty(Hpnum.text.toString())) {
                Hpnum.error = "Please enter valid Phone Number"
                return@setOnClickListener
            }else if(TextUtils.isEmpty(Email.text.toString())) {
                Email.error = "Please enter valid E-mail address"
                return@setOnClickListener
            }else if(TextUtils.isEmpty(Pass.text.toString())) {
                Pass.error = "Please enter Password"
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(Email.text.toString(), Pass.text.toString())
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            auth.currentUser!!.sendEmailVerification().addOnCompleteListener(//check "!!" if error
                                    OnCompleteListener {
                                        if (it.isSuccessful){
                                            Toast.makeText(this@RegisterActivity, "Success! Please verify your e-mail address ;) ", Toast.LENGTH_LONG).show()
                                            //val intent = Intent(this, Biometric::class.java)
                                            //startActivity(intent)//biometric
                                            finish()//check
                                        }else{
                                            Toast.makeText(this@RegisterActivity, "Check network connection and try again :( ", Toast.LENGTH_LONG).show()
                                        }
                                    })

                            val currentUser = auth.currentUser
                            val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                            currentUserDb?.child("Name")?.setValue(Name.text.toString())
                            currentUserDb?.child("Phone")?.setValue(Hpnum.text.toString())
                            //

                        }else{
                            Toast.makeText(this@RegisterActivity, "Account with same info \n    already registered ! ", Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }

}
