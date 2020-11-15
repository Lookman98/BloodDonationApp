package com.example.blooddonation

import android.app.ActivityOptions
import android.app.KeyguardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

/*************************************************/

class LoginActivity: AppCompatActivity(){

    lateinit var auth: FirebaseAuth

    private var cancellationSignal: CancellationSignal? = null

    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
    get() =
        @RequiresApi(Build.VERSION_CODES.P)
        object : BiometricPrompt.AuthenticationCallback(){

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                notifyUser("Authentication Error: ${errString}")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)

                val email = emailAddressLogin.text.toString()
                val password = passwordLogin.text.toString()
                if(checkEmptyField(email,password)){

                    auth.signInWithEmailAndPassword(email,password)
                        .addOnSuccessListener {
                            val home = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(home);
                            finish()
                        }.addOnFailureListener{exception ->
                            Toast.makeText(this@LoginActivity,"${exception.localizedMessage}",Toast.LENGTH_LONG).show()
                        }
                }
//                notifyUser("Authentication success!")
//                startActivity(Intent(this@LoginActivity,MainActivity::class.java))
//                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null ){
            val backToLogin = Intent(this, MainActivity::class.java)
            startActivity(backToLogin)
            finish()
        }

        checkBioMetricSupport()

        
    }

    //check login credential
    fun loginValidation(view: View) {

        val email = emailAddressLogin.text.toString()
        val password = passwordLogin.text.toString()
        if(checkEmptyField(email,password)){

            auth.signInWithEmailAndPassword(email,password)
                    .addOnSuccessListener {
                        val home = Intent(this, MainActivity::class.java)
                        startActivity(home);
                        finish()
                    }.addOnFailureListener{exception ->
                        Toast.makeText(this,"${exception.localizedMessage}",Toast.LENGTH_LONG).show()
                    }
        }


    }


    // notify success or not - fingerprint login
    private fun notifyUser(message: String){
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    fun fingerprintLogin(view: View) {

        val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(this)
                .setTitle("Fingperint Login")
                .setNegativeButton("Cancel", this.mainExecutor, DialogInterface.OnClickListener{
                    dialog, which ->
                    notifyUser("Authentication Cancelled")
                }).build()

        biometricPrompt.authenticate(getcancellationSignal(), mainExecutor, authenticationCallback)
    }

    private fun getcancellationSignal(): CancellationSignal{
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener{
            notifyUser("Authentication was cancelled by the user")
        }
        return cancellationSignal as CancellationSignal
    }


    private fun checkBioMetricSupport(): Boolean {
        val keyguardManager: KeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        //check fingpering is enable at setting
        if(!keyguardManager.isKeyguardSecure){
            notifyUser("Fingprint authentication has not been enable in setting")
            return false
        }

        //check developer enable fingperint permission for the application
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.USE_BIOMETRIC) != PackageManager.PERMISSION_GRANTED){
            //develeper notification
            notifyUser("Fingperint authentication permission is not enabled");
            return false

        }

        return  if(packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)){
            return true
        }else{
            return true
        }
    }


    //check empty field
    private fun checkEmptyField(email: String, password: String): Boolean {

            if(email.isEmpty()){
                Toast.makeText(this,"Please insert email",Toast.LENGTH_LONG).show()
                return false
            }

            if(password.isEmpty()){
                Toast.makeText(this,"Please insert password",Toast.LENGTH_LONG).show()
                return false
            }

        return true
    }

    //register page
    fun registerPage(view: View) {
        val register = Intent(this, RegisterActivity::class.java)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            startActivity(register, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
        }
    }



}
