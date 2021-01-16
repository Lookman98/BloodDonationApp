package com.example.blooddonation.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.budiyev.android.codescanner.*
import com.example.blooddonation.LoginActivity
import com.example.blooddonation.MainActivity
import com.example.blooddonation.R
import com.example.blooddonation.donationform.DonationFormPart1
import com.example.blooddonation.donationform.saveAnswer
import com.google.firebase.auth.FirebaseAuth

class DonateFragment : Fragment() {

    private lateinit var codeScanner: CodeScanner
    val CAMERA_PERMISSION_REQUEST = 111;

    val auth =  FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val donateView = inflater.inflate(R.layout.fragment_donate, container, false)
        return donateView


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        //check user credential
        if(auth.currentUser == null ){
            val backToLogin = Intent(activity, LoginActivity::class.java)
            startActivity(backToLogin)
        }

        val scannerView = view.findViewById<CodeScannerView>(R.id.scannerView)
        val activity = requireActivity()
        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = CodeScanner.ALL_FORMATS
        codeScanner.autoFocusMode = AutoFocusMode.SAFE
        codeScanner.scanMode = ScanMode.SINGLE
        codeScanner.isAutoFocusEnabled = true
        codeScanner.isFlashEnabled = false

        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                //save result from qrCode Scan
                saveAnswer.campaignID = it.text;
                //link to DonationForm page
                val questionForm = Intent(activity, DonationFormPart1::class.java)
                startActivity(questionForm)

            }
            val intent = Intent()
            intent.setClass(activity,MainActivity::class.java)
            startActivity(intent)
        }
        codeScanner.errorCallback = ErrorCallback {
            activity.runOnUiThread {
                Toast.makeText(activity,"Camera Error: ${it.message}", Toast.LENGTH_LONG).show()
            }
        }
        checkPermission()
    }



    fun checkPermission(){
        if(this.activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) } != PackageManager.PERMISSION_GRANTED){
            activity?.let {
                ActivityCompat.requestPermissions(it, arrayOf(Manifest.permission.CAMERA),CAMERA_PERMISSION_REQUEST)
            }
        }else{
            codeScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_PERMISSION_REQUEST
            &&grantResults.isNotEmpty()
            &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            codeScanner.startPreview()
        }else{
            Toast.makeText(activity, "Application need camera access permission", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview();
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}