package com.example.blooddonation.donationform

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonation.R

class DonationForm: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_form)

       val campaignID = intent.getStringExtra("campaignID")

//        campaignidText.setText(campaignID)

    }
}


