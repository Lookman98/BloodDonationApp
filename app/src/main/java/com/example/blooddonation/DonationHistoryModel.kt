package com.example.blooddonation

data class DonationHistoryModel constructor(
        val donation_date: String,
        val donation_time: String,
        val donation_type: String,
        val documentId: String)