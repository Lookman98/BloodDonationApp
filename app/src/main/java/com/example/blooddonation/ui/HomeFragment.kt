package com.example.blooddonation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blooddonation.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    lateinit var historyAdapter: DonationHistoryAdapter
    val donationhistory = arrayListOf<DonationHistoryModel>()
    val donationCollectionRef =  FirebaseFirestore.getInstance()
                                 .collection(DONATION).whereEqualTo("donation_status","approve")
    lateinit var donationHistoryListener: ListenerRegistration

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

        val userID = auth.currentUser!!.uid

        userDataRef.document(userID).addSnapshotListener { snapshot, error ->
            var bloodType = snapshot?.getString("donor_bloodType")
            var ic = snapshot?.getString("donor_ic")
            var name = snapshot?.getString("donor_name")?.toUpperCase()

            UserData.Name = name.toString()
            UserData.bloodType = bloodType.toString()
            UserData.ic = ic.toString()

            blood_type.text = UserData.bloodType
            donor_ic.text = UserData.ic
            donor_name.text = UserData.Name

            if(error != null)
                 Toast.makeText(activity,"ERROR: ${error?.localizedMessage}",Toast.LENGTH_LONG).show()
        }


        historyAdapter = DonationHistoryAdapter(donationhistory)
        historyRecycleView.adapter = historyAdapter
        val layoutManager = LinearLayoutManager(activity)
        historyRecycleView.layoutManager = layoutManager

        setListener()
    }

     override fun onResume() {
         super.onResume()
         setListener()

     }

     override fun onStart() {
         super.onStart()
         setListener()
     }


     fun setListener(){
         donationHistoryListener = donationCollectionRef
                 .whereEqualTo(D_IC,UserData.ic)
                 .addSnapshotListener{snapshot,exception ->

             if(exception != null){
                 Toast.makeText(activity,"${exception.localizedMessage}",Toast.LENGTH_LONG).show()
             }

             if(snapshot != null){
                donationhistory.clear()

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
             }
         }
     }


}










