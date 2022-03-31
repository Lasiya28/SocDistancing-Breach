package com.lasiya.breach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_landing.*
//import com.amier.Landing
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*


class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        RegLoginBtn.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        test.setOnClickListener {
            val intent = Intent(this, Biometric::class.java)
            startActivity(intent)
        }

        LoginBtn.setOnClickListener {
            val intent = Intent(this, Biometric::class.java)
            startActivity(intent)
        }

        //LoginBtn.setOnClickListener {
        //    auth = FirebaseAuth.getInstance()
        //    //login()
        //    val intent = Intent(this, Biometric::class.java)
        //    startActivity(intent)
            //transition here!!
        //}

        //auth = FirebaseAuth.getInstance()

        //val currentuser = auth.currentUser
        //if(currentuser != null) {
        //    startActivity(Intent(this@LoginActivity, LandingActivity::class.java))
        //    finish()
        //}
        //login()
    }


    //private fun login(){
    //    LoginBtn.setOnClickListener{
    //        if(TextUtils.isEmpty(Email.text.toString())){
    //            Name.error = "Please Enter E-mail"
    //            return@setOnClickListener
    //        }
    //        else if(TextUtils.isEmpty(Pass.text.toString())){
    //            Name.error = "Please Enter Password"
    //            return@setOnClickListener
    //        }
    //        auth.signInWithEmailAndPassword(EmailText.text.toString(), Pass.text.toString())
    //            .addOnCompleteListener{
    //                if(it.isSuccessful){
    //                    if (auth.currentUser!!.isEmailVerified){
    //                        val intent = Intent(this@LoginActivity, LandingActivity::class.java)
    //                        startActivity(intent)
    //                    }else{
    //                        Toast.makeText(this@LoginActivity, "Please go to your e-mail and verify your account.", Toast.LENGTH_LONG).show()
    //                    }
    //                    startActivity(Intent(this@LoginActivity, LandingActivity::class.java))//BiometricActivity -> LandingActivity
    //                    finish()
    //                }else{
    //                    Toast.makeText(this@LoginActivity, "E-mail or Password incorrect! ", Toast.LENGTH_LONG).show()
    //                }
    //            }

    //    }
    //}
//!!!!!!!!If error check back indentation/curly bracket!!!!!!!!!

}
