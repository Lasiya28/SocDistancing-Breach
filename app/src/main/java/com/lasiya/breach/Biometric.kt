package com.lasiya.breach

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_biometric.*
import java.util.concurrent.Executor
import kotlinx.android.synthetic.main.activity_biometric.*
import kotlinx.android.synthetic.main.activity_biometric.logoutbtn
import kotlinx.android.synthetic.main.activity_landing.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_landing.*
import kotlinx.android.synthetic.main.activity_register.*


class Biometric : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt//
    private lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo//

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biometric)

        //init biometric
        executor = ContextCompat.getMainExecutor(this)

        biometricPrompt = androidx.biometric.BiometricPrompt(this@Biometric, executor, object : androidx.biometric.BiometricPrompt.AuthenticationCallback(){ //
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                //auth error, stop tasks that requires auth
                authStatusTv.text = "Authentication Error : $errString"
                Toast.makeText(this@Biometric, "Authentication Error ! : \n $errString", Toast.LENGTH_SHORT).show()
            }

            override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                //if auth success, do tasks that requires auth
                authStatusTv.text = "Authentication Success !"
                Toast.makeText(this@Biometric, "Authentication Success !", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@Biometric, ChooseActivity::class.java)
                startActivity(intent)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                //if auth fail, stop all tasks that requires auth
                authStatusTv.text = "Authentication Failed !"
                Toast.makeText(this@Biometric, "Authentication Failed !", Toast.LENGTH_SHORT).show()
            }

        })

        //set properties like title and description on auth dialog
        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric Authentication")
                .setSubtitle("Login using fingerprint authentication")
                .setNegativeButtonText("Use App Password instead")
                .build()

        //handle click and start authentication dialog
        authBtn.setOnClickListener{
            //show auth dialog
            biometricPrompt.authenticate(promptInfo)
        }

        logoutbtn.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this@Biometric, LoginActivity::class.java))
            finish()
        }

    }
}
