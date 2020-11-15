package com.example.blooddonation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.blooddonation.LoginActivity
import com.example.blooddonation.R
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
            auth.signOut()
            startActivity(Intent(activity,LoginActivity::class.java))

        }

    }

    override fun onStop() {
        super.onStop()

    }

}