package com.example.blooddonation

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null ){
            val backToLogin = Intent(this, MainActivity::class.java)
            startActivity(backToLogin)
            finish()
        }
    }

    fun registerValidation(view: View) {
        val email = emailAddressRegister.text.toString()
        val password = passwordRegister.text.toString()
        val fullname = fullname.text.toString()
        val nric = icNumber.text.toString()
        val phoneNo =  phoneNumber.text.toString()

        if(fieldEmptyCheck(email,password,fullname,nric,phoneNo)){

            auth.createUserWithEmailAndPassword(email,password)
                    .addOnSuccessListener { result ->
                        val changeRequest = UserProfileChangeRequest.Builder()
                                .setDisplayName(fullname)
                                .build()
                        result.user!!.updateProfile(changeRequest)
                                .addOnFailureListener{ exception ->
                                    Toast.makeText(this, "Could not update fullname: ${exception.message}", Toast.LENGTH_LONG).show()
                                }

                        val data = HashMap<String , Any>()
                        data.put(FULLNAME,fullname)
                        data.put(NRIC,nric)
                        data.put(PHONENUMBER,phoneNo)
                        data.put(DATE_CREATED,FieldValue.serverTimestamp())

                        FirebaseFirestore.getInstance().collection("donor").document(result.user!!.uid)
                                .set(data)
                                .addOnSuccessListener {
                                    Toast.makeText(this,"Register Success",Toast.LENGTH_LONG).show()
                                    val intent = Intent(this,LoginActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                .addOnFailureListener{exception ->
                                    Toast.makeText(this, "Could not register user : ${exception.message}", Toast.LENGTH_LONG).show()
                                }
                    }
                    .addOnFailureListener {exception ->
                        Toast.makeText(this, "Could not register user: ${exception.message}", Toast.LENGTH_LONG).show()
                    }

        }//field empty validate


    }//function registerValidate

    private fun fieldEmptyCheck(email: String, password: String, fullname: String, nric: String, phoneNo: String): Boolean {

        if(email.isEmpty()) {
            displayError("Enter Email")
            return false
        }

        if(password.isEmpty()) {
            displayError("Enter Password")
            return false
        }

        if(fullname.isEmpty()) {
            displayError("Enter Fullname")
            return false
        }

        if(nric.isEmpty()) {
            displayError("Enter Ic Number")
            return false
        }

        if(phoneNo.isEmpty()) {
            displayError("Enter Phone Number")
            return false
        }

        return true
    }

    private fun displayError(error: String) {
        Toast.makeText(this,"${error}",Toast.LENGTH_LONG).show()
    }

    fun loginPage(view: View) {
        finish()
        val loginPage = Intent(this, LoginActivity::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            startActivity(loginPage,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }



    }
}