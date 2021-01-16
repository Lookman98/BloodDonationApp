package com.example.blooddonation.donationform

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonation.R
import kotlinx.android.synthetic.main.donation_form_part1.*

class DonationFormPart1: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.donation_form_part1)

        var answer: String

       oneRadio.setOnCheckedChangeListener{ _, checkedId ->
           when (checkedId) {
               R.id.oneNo -> {
                   saveAnswer.data["one"] = oneNo.text.toString()
               }

               R.id.oneYes -> {
                   saveAnswer.data["one"] = oneYes.text.toString()
               }
               else -> {
                   answer = "1"
                   errorMessage(answer)
               }


           }

       }

        twoRadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.twoNo -> {
                    saveAnswer.data["two"] == twoNo.text.toString()
                }
                R.id.twoYes -> {
                    saveAnswer.data["two"] = twoYes.text.toString()
                }
                else -> {
                    answer = "2";
                    errorMessage(answer);
                }
            }
        }

        //question Three
        threeRadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.threeNo -> {
                    saveAnswer.data["three"] = threeNo.text.toString()
                }
                R.id.threeYes -> {
                    saveAnswer.data["three"] = threeNo.text.toString()
                }
                else -> {
                    answer = "3";
                    errorMessage(answer);
                }
            }
        }

        //questionFour
        fourARadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fourANo -> {
                    saveAnswer.data["fourA"] = fourANo.text.toString()
                }
                R.id.fourAYes -> {
                    saveAnswer.data["fourA"] = fourAYes.text.toString()
                }
                else -> {
                    answer = "4. A";
                    errorMessage(answer);
                }
            }
        }



        fourBradio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fourBNo -> {
                    saveAnswer.data["fourB"] = fourBNo.text.toString()
                }
                R.id.fourBYes -> {
                    saveAnswer.data["fourB"] = fourBYes.text.toString()
                }
                else -> {
                    answer = "4. B";
                    errorMessage(answer);
                }
            }
        }

        saveAnswer.data["fourBInput"] = fourBInput.text.toString()


        fourCradio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fourCNo -> {
                    saveAnswer.data["fourC"] = fourCNo.text.toString()
                }
                R.id.fourCYes -> {
                    saveAnswer.data["fourC"] = fourCYes.text.toString()
                }
                else -> {
                    answer = "4. C";
                    errorMessage(answer);
                }
            }
        }

        fourDradio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fourDNo -> {
                    saveAnswer.data["fourD"] = fourDNo.text.toString()
                }
                R.id.fourDYes -> {
                    saveAnswer.data["fourD"] = fourDYes.text.toString()
                }
                else -> {
                    answer = "4. D";
                    errorMessage(answer);
                }
            }
        }

        saveAnswer.data["fourDInput"] = fourDInput.text.toString();


        fiveRadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.fiveNo -> {
                    saveAnswer.data["five"] = fiveNo.text.toString()
                }
                R.id.fiveYes -> {
                    saveAnswer.data["five"] = fiveYes.text.toString()
                }
                else -> {
                    answer = "5";
                    errorMessage(answer);
                }
            }
        }

        sixRadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.sixNo -> {
                    saveAnswer.data["six"] = sixNo.text.toString()
                }
                R.id.sixYes -> {
                    saveAnswer.data["six"] = sixYes.text.toString()
                }
                else -> {
                    answer = "6";
                    errorMessage(answer);
                }
            }
        }

        sevenRadio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.sevenNo -> {
                    saveAnswer.data["seven"] = sevenNo.text.toString()
                }
                R.id.sevenYes -> {
                    saveAnswer.data["seven"] = sevenYes.text.toString()
                }
                else -> {
                    answer = "7";
                    errorMessage(answer);
                }
            }
        }


        eightradio.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.eightNo -> {
                    saveAnswer.data["eight"] = eightNo.text.toString()
                }
                R.id.eightYes -> {
                    saveAnswer.data["eight"] = eightYes.text.toString()
                }
                else -> {
                    answer = "8";
                    errorMessage(answer);
                }
            }
        }

        if(eightInput.text.toString() != "")
          saveAnswer.data["eightText"] = eightInput.text.toString()

    }

    private fun errorMessage(answer: String) {
        makeText(this, "Please answer question $answer", LENGTH_LONG).show()
    }

    fun nextToForm2(view: View) {
        val next = Intent(this,DonationFormPart2::class.java)
        startActivity(next)
    }


}