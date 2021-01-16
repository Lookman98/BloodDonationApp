package com.example.blooddonation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.blooddonation.LoginActivity
import com.example.blooddonation.R
import com.example.blooddonation.UserData
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    val auth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val settingView = inflater.inflate(R.layout.fragment_settings, container, false)
        return settingView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //check user credential
        if(auth.currentUser == null ){
            val backToLogin = Intent(activity, LoginActivity::class.java)
            startActivity(backToLogin)
        }

        signout.setOnClickListener {
            UserData.isVerify = false
            auth.signOut()
            startActivity(Intent(activity,LoginActivity::class.java))

        }

    }

    override fun onStop() {
        super.onStop()
    }

}