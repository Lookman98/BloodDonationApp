package com.example.blooddonation.donationform

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonation.R
import kotlinx.android.synthetic.main.donation_form_part2.*

class DonationFormPart2: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donation_form_part2)

        var answer: String

        diseaseOne.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease1: String = buttonView.text.toString()
            if(isChecked){
                saveAnswer.questionNine.add(disease1)
            }else{
                saveAnswer.questionNine.remove(disease1)
            }
        }


        diseaseTwo.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease2: String = buttonView.text.toString()
            if(isChecked){
                saveAnswer.questionNine.add(disease2)
            }else{
                saveAnswer.questionNine.remove(disease2)
            }
        }

        diseaseThree.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease3: String = buttonView.text.toString()
            if(isChecked){
                saveAnswer.questionNine.add(disease3)
            }else{
                saveAnswer.questionNine.remove(disease3)
            }
        }

        diseaseFour.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease4: String = buttonView.text.toString()
            if(isChecked){
                saveAnswer.questionNine.add(disease4)
            }else{
                saveAnswer.questionNine.remove(disease4)
            }
        }

        diseaseFive.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease5: String = buttonView.text.toString()
            if(isChecked){
                saveAnswer.questionNine.add(disease5)
            }else{
                saveAnswer.questionNine.remove(disease5)
            }
        }

        diseaseSix.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease6: String = buttonView.text.toString()

            if(isChecked){
                saveAnswer.questionNine.add(disease6)
            }else{
                saveAnswer.questionNine.remove(disease6)
            }
        }

        diseaseSeven.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease7: String = buttonView.text.toString()
            if(isChecked){
                saveAnswer.questionNine.add(disease7)
            }else{
                saveAnswer.questionNine.remove(disease7)
            }
        }

        diseaseEight.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease8: String = buttonView.text.toString()

            if(isChecked){
                saveAnswer.questionNine.add(disease8)
            }else{
                saveAnswer.questionNine.remove(disease8)
            }
        }

        diseaseNine.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease9: String = buttonView.text.toString()

            if(isChecked){
                saveAnswer.questionNine.add(disease9)
            }else{
                saveAnswer.questionNine.remove(disease9)
            }
        }

        diseaseTen.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease10: String = buttonView.text.toString()

            if(isChecked){
                saveAnswer.questionNine.add(disease10)
            }else{
                saveAnswer.questionNine.remove(disease10)
            }
        }

        diseaseEleven.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease11: String = buttonView.text.toString()

            if(isChecked){
                saveAnswer.questionNine.add(disease11)
            }else{
                saveAnswer.questionNine.remove(disease11)
            }
        }

        diseaseTwelve.setOnCheckedChangeListener { buttonView, isChecked ->
            var disease12: String = buttonView.text.toString()

            if(isChecked){
                saveAnswer.questionNine.add(disease12)
            }else{
                saveAnswer.questionNine.remove(disease12)
            }
        }

        tenRadio.setOnCheckedChangeListener{group, checkedId ->

            when (checkedId) {
                R.id.tenNo -> {
                    saveAnswer.data["ten"] = tenNo.text.toString()
                }
                R.id.tenYes -> {
                    saveAnswer.data["ten"] = tenYes.text.toString()
                }
                else -> {
                    answer = "10"
                    errorMessage(answer)
                }
            }
        }

        saveAnswer.data["tenText"] = teninput.text.toString()

        elevenARadio.setOnCheckedChangeListener{group, checkedId ->
            when (checkedId) {
                R.id.elevenANo -> {
                    saveAnswer.data["elevenA"] = elevenANo.text.toString()
                }
                R.id.elevenAYes -> {
                    saveAnswer.data["elevenA"] = elevenAYes.text.toString()
                }
                else -> {
                    answer = "11. A"
                    errorMessage(answer)
                }
            }
        }

        elevenBRadio.setOnCheckedChangeListener{group, checkedId ->
            when (checkedId) {
                R.id.elevenBNo -> {
                    saveAnswer.data["elevenB"] = elevenBNo.text.toString()
                }
                R.id.elevenBYes -> {
                    saveAnswer.data["elevenB"] = elevenBYes.text.toString()
                }
                else -> {
                    answer = "11. B"
                    errorMessage(answer)
                }
            }
        }


        twelveARadio.setOnCheckedChangeListener{group, checkedId ->
            when (checkedId) {
                R.id.twelveANo -> {
                    saveAnswer.data["twelveA"] = twelveANo.text.toString()
                }
                R.id.twelveAYes -> {
                    saveAnswer.data["twelveA"] = twelveAYes.text.toString()
                }
                else -> {
                    answer = "12. A"
                    errorMessage(answer)
                }
            }
        }

        twelveBRadio.setOnCheckedChangeListener{group, checkedId ->
            when (checkedId) {
                R.id.twelveBNo -> {
                    saveAnswer.data["twelveB"] = twelveBNo.text.toString()
                }
                R.id.twelveBYes -> {
                    saveAnswer.data["twelveB"] = twelveBYes.text.toString()
                }
                else -> {
                    answer = "12. B"
                    errorMessage(answer)
                }
            }
        }

        thirteenARadio.setOnCheckedChangeListener{group, checkedId ->
            when (checkedId) {
                R.id. thirteenANo -> {
                    saveAnswer.data["thirteenA"] =  thirteenANo.text.toString()
                }
                R.id.thirteenAYes -> {
                    saveAnswer.data["thirteenA"] = thirteenAYes.text.toString()
                }
                else -> {
                    answer = "13. A"
                    errorMessage(answer)
                }
            }
        }

        thirteenBRadio.setOnCheckedChangeListener{group, checkedId ->
            when (checkedId) {
                R.id.thirteenBNo -> {
                    saveAnswer.data["thirteenB"] = thirteenBNo.text.toString()
                }
                R.id.thirteenBYes -> {
                    saveAnswer.data["thirteenB"] = thirteenBYes.text.toString()
                }
                else -> {
                    answer = "13. B"
                    errorMessage(answer)
                }
            }
        }

    }

    fun nextToForm3(view: View) {
        val next = Intent(this,DonationFormPart3::class.java)
        startActivity(next)
    }

    private fun errorMessage(answer: String) {
        Toast.makeText(this, "Please answer question $answer", Toast.LENGTH_LONG).show()
    }
}