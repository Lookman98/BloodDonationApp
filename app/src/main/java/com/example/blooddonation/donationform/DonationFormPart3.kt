package com.example.blooddonation.donationform

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonation.MainActivity
import com.example.blooddonation.R
import com.example.blooddonation.UserData
import com.example.blooddonation.donationform.saveAnswer.Companion.data
import com.example.blooddonation.donationform.saveAnswer.Companion.donation
import com.example.blooddonation.donationform.saveAnswer.Companion.questionNine
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.donation_form_part3.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DonationFormPart3: AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donation_form_part3)


        var answer: String

        fourteenARadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fourteenANo -> {
                    data["fourteenA"] = fourteenANo.text.toString()
                }
                R.id.thirteenAYes -> {
                    data["fourteenA"] = fourteenAYes.text.toString()
                }
                else -> {
                    answer = "14. A"
                    errorMessage(answer)
                }
            }
        }

        fourteenBradio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fourteenBNo -> {
                    data["fourteenB"] = fourteenBNo.text.toString()
                }
                R.id.fourteenBYes -> {
                    data["fourteenB"] = fourteenBYes.text.toString()
                }
                else -> {
                    answer = "14. B"
                    errorMessage(answer)
                }
            }
        }

        fourteenCradio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fourteenCNo -> {
                    data["fourteenC"] = fourteenCNo.text.toString()
                }
                R.id.fourteenCYes -> {
                    data["fourteenC"] = fourteenCYes.text.toString()
                }
                else -> {
                    answer = "14. C"
                    errorMessage(answer)
                }
            }
        }

        fifteenARadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fifteenANo -> {
                    data["fifteenA"] = fifteenANo.text.toString()
                }
                R.id.fifteenAYes -> {
                    data["fifteenA"] = fifteenAYes.text.toString()
                }
                else -> {
                    answer = "15. A"
                    errorMessage(answer)
                }
            }
        }

        fifteenBradio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fifteenBNo -> {
                    data["fifteenB"] = fifteenBNo.text.toString()
                }
                R.id.fifteenBYes -> {
                    data["fifteenB"] = fifteenBYes.text.toString()
                }
                else -> {
                    answer = "15. B"
                    errorMessage(answer)
                }
            }
        }

        sixteenRadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.sixteenNo -> {
                    data["sixteen"] = sixteenNo.text.toString()
                }
                R.id.sixteenYes -> {
                    data["sixteen"] = sixteenYes.text.toString()
                }
                else -> {
                    answer = "16"
                    errorMessage(answer)
                }
            }
        }

        seventeenRadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.seventeenNo -> {
                    data["seventeen"] = seventeenNo.text.toString()
                }
                R.id.seventeenYes -> {
                    data["seventeen"] = seventeenYes.text.toString()
                }
                else -> {
                    answer = "17"
                    errorMessage(answer)
                }
            }
        }


        eighteenRadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.eighteenNo -> {
                    data["eighteen"] = eighteenNo.text.toString()
                }
                R.id.eighteenYes -> {
                    data["eighteen"] = eighteenYes.text.toString()
                }
                else -> {
                    answer = "18"
                    errorMessage(answer)
                }
            }
        }


   submit.setOnClickListener{

       //question Data
       var donationID = randomString("D");
       data["disease"] = questionNine.toList()
       data["donor_ic"] = UserData.ic
       data["campaign_id"] = saveAnswer.campaignID
       data["donation_id"] = donationID


       db.collection("donationForm")
               .document(donationID)
               .set(data)
               .addOnSuccessListener {
                   data.clear()
                   questionNine.clear()
//                   val backToHome = Intent(this, MainActivity::class.java)
//                   startActivity(backToHome)
//                   Toast.makeText(this, "Data Add Success", Toast.LENGTH_LONG).show()

               }.addOnFailureListener { exception ->
                   Toast.makeText(this, "Error Add: $exception", Toast.LENGTH_LONG).show()
               }



       val cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"))
       val currentLocalTime = cal.time
       val time: DateFormat = SimpleDateFormat("HH:mm a")
       time.timeZone = TimeZone.getTimeZone("GMT+8:00")


       val formatDate = SimpleDateFormat("dd/MM/yyyy ", Locale.getDefault()).format(Date());
       val formatTime = time.format(currentLocalTime)



       var bloodID = randomString("A");

       donation["donation_id"] = donationID
       donation["donor_ic"] = UserData.ic
       donation["campaign_id"] = saveAnswer.campaignID
       donation["donation_bloodid"] = bloodID
       donation["donation_type"] = "PDN"
       donation["donation_date"]  = formatDate
       donation["donation_status"] = "notyet"
       donation["donation_bloodtype"] = UserData.bloodType
       donation["donation_time"] = formatTime

       db.collection("donation")
               .document(donationID)
               .set(donation)
               .addOnSuccessListener {
                   val backToHome = Intent(this, MainActivity::class.java)
                   startActivity(backToHome)
                   Toast.makeText(this, "Data Add Success", Toast.LENGTH_LONG).show()

               }.addOnFailureListener { exception ->
                   Toast.makeText(this, "Error Add: $exception", Toast.LENGTH_LONG).show()
               }

       db.collection("donor").document()


   }


    }

    private fun errorMessage(answer: String) {
        Toast.makeText(this, "Please answer question $answer", Toast.LENGTH_LONG).show()
    }

    fun randomString(initial: String): String {
        val list = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray()
        var randomS = ""
        var stringLength = 5
        for (i in 1..stringLength) {
            randomS += list[getRandomNumber(0, list.size - 1)]
        }
        return initial+randomS
    }

    fun getRandomNumber(min: Int, max: Int): Int {
        return Random().nextInt((max - min) + 1) + min
    }


}
