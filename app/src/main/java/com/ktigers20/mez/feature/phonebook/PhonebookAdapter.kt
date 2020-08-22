package com.ktigers20.mez.feature.phonebook

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ktigers20.mez.R
import com.ktigers20.mez.data.entity.PhoneBookInfo
import kotlinx.android.synthetic.main.item_phone_book.view.*

class PhonebookAdapter(
    private val phoneBookList: ArrayList<PhoneBookInfo>
) : RecyclerView.Adapter<PhonebookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return phoneBookList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = phoneBookList[position]
        holder.bind(item.userNm, item.deptName, item.teamName, item.phoneNumber)
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_phone_book, parent, false)
        ) {
        private val mPhoneNumberTv: TextView = itemView.phone_number
        private val mPersonInfoTv: TextView = itemView.phone_book_person_info

        @SuppressLint("SetTextI18n")
        fun bind(
            userName: String, deptName: String, jobName: String, phoneNumber: String
        ) {
            mPhoneNumberTv.text = phoneNumber
            mPersonInfoTv.text = "$userName / $deptName ($jobName)"
        }
    }

}
