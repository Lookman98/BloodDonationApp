package com.example.blooddonation

import com.google.firebase.firestore.DocumentId

data class DonationHistoryModel(
        val donation_date: String,
        val donation_time: String,
        val donation_type: String,
        val documentId: String)