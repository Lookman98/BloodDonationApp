package com.example.blooddonation.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blooddonation.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.fragment_home.*

 class HomeFragment : Fragment() {

    lateinit var historyAdapter: DonationHistoryAdapter
    val donationhistory = arrayListOf<DonationHistoryModel>()
    val donationCollectionRef =  FirebaseFirestore.getInstance()
                                 .collection(DONATION)

    val auth = FirebaseAuth.getInstance()
    val userDataRef = FirebaseFirestore.getInstance().collection("donor")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeView = inflater.inflate(R.layout.fragment_home, container, false)
        return homeView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        //check user credential
        if(auth.currentUser == null ){
                val backToLogin = Intent(activity, LoginActivity::class.java)
                startActivity(backToLogin)
        }

        val UserID = auth.currentUser!!.uid

        userDataRef.document(UserID).addSnapshotListener { snapshot, error ->
            blood_type.setText(snapshot?.getString("donor_bloodType"))
            donor_ic.setText(snapshot?.getString("donor_ic"))
            donor_name.setText(snapshot?.getString("donor_name")?.toUpperCase())

            if(error != null)
                 Toast.makeText(activity,"${error?.localizedMessage}",Toast.LENGTH_LONG).show()
        }


        historyAdapter = DonationHistoryAdapter(donationhistory)
        historyRecycleView.adapter = historyAdapter
        val layoutManager = LinearLayoutManager(activity)
        historyRecycleView.layoutManager = layoutManager

        donationCollectionRef.get()
                .addOnSuccessListener {snapshot ->
                    for (document in snapshot.documents){
                        val data = document.data
                        val date = data!![D_DATE].toString()
                        val time = data!![D_TIME].toString()
                        val type = data!![D_NOTE].toString()
                        val id = document.id

                        val newHistory = DonationHistoryModel(date,time,type,id)
                        donationhistory.add(newHistory)
                    }
                    historyAdapter.notifyDataSetChanged()

                }.addOnFailureListener {exception ->
                    Toast.makeText(activity,"${exception}", Toast.LENGTH_LONG).show()
                }

    }


}








