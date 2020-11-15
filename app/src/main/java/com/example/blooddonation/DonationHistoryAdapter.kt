package com.example.blooddonation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DonationHistoryAdapter(val history: ArrayList<DonationHistoryModel>) : RecyclerView.Adapter<DonationHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val date = itemView.findViewById<TextView>(R.id.dateTextView)
        val time = itemView.findViewById<TextView>(R.id.timeTextView)
        val type = itemView.findViewById<TextView>(R.id.typeTextView)

        fun bindHistory(historyModel: DonationHistoryModel){
                date.text = historyModel.donation_date
                time.text = historyModel.donation_time
                type.text =  historyModel.donation_type
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.donationhistory_list_view,parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHistory(history[position])
    }

    override fun getItemCount(): Int {
        return history.count()
    }

}