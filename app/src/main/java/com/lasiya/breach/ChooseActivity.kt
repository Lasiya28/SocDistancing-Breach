package com.lasiya.breach

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        mode1.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }
        //Start Visual Social Distancing module by calling Unity activity
        mode2.setOnClickListener {
            startActivity(Intent(this, UnityHolderActivity::class.java))
            overridePendingTransition(R.anim.nav_default_pop_enter_anim,R.anim.nav_default_pop_exit_anim)
        }

    }
}