package com.example.blooddonation

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoginActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


    fun loginValidation(view: View) {
        val home = Intent(this, MainActivity::class.java)
        startActivity(home);

    }

    fun registerPage(view: View) {
        val register = Intent(this, RegisterActivity::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            startActivity(register, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }

}
