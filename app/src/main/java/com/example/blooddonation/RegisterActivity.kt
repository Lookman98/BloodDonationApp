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
import kotlinx.android.synthetic.main.activity_login.emailAddress
import kotlinx.android.synthetic.main.activity_login.password
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_form.*

class RegisterActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
    }

    fun registerValidation(view: View) {
        val email = emailAddress.text.toString()
        val password = password.text.toString()
        val fullname = fullname.text.toString()
        val nric = icNumber.text.toString()
        val phoneNo =  phoneNumber.text.toString()
//        val address1 = address1.text.toString()
//        val address2 = address2.text.toString()
//        val postcode = postcode.text.toString()

        auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener { result ->
                    val changeRequest = UserProfileChangeRequest.Builder()
                            .setDisplayName(fullname)
                            .build()
                    result.user?.updateProfile(changeRequest)
                        ?.addOnFailureListener{ exception ->
                            Toast.makeText(this, "Could not update fullname: ${exception.message}", Toast.LENGTH_LONG).show()
                        }
                    Toast.makeText(this, "Success Registered As User", Toast.LENGTH_LONG).show()
                    val data = HashMap<String , Any>()
                    data.put(FULLNAME,fullname)
                    data.put(NRIC,nric)
                    data.put(PHONENUMBER,phoneNo)
//                    data.put(ADDRESS1,phoneNo)
//                    data.put(ADDRESS2,phoneNo)
//                    data.put(POSTCODE,phoneNo)
                    data.put(DATE_CREATED,FieldValue.serverTimestamp())

                    FirebaseFirestore.getInstance().collection("donor").document(nric)
                            .set(data)
                            .addOnSuccessListener {
                                val loginPage = Intent(this,LoginActivity::class.java)
                                startActivity(loginPage)
                                finish()
                            }
                            .addOnFailureListener{exception ->
                                Toast.makeText(this, "Could not register user : ${exception.message}", Toast.LENGTH_LONG).show()
                            }
                }
                .addOnFailureListener {exception ->
                    Toast.makeText(this, "Could not create user: ${exception.message}", Toast.LENGTH_LONG).show()
                }
    }

    fun loginPage(view: View) {
        finish()
        val loginPage = Intent(this, LoginActivity::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            startActivity(loginPage,ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }

    }
}