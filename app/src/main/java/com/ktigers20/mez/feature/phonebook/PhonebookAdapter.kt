package com.ktigers20.mez.feature.phonebook

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
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
        holder.bind(item.uSER_NM, item.dEPT_NM, item.uSER_JOB, item.uSER_HP)
    }

    fun submitList(newPhoneBookList: ArrayList<PhoneBookInfo>) {
        val oldList: ArrayList<PhoneBookInfo> = ArrayList()
        phoneBookList.map {
            oldList.add(it)
        }
        newPhoneBookList.map {
            phoneBookList.add(it)
        }
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            PhoneBookItemDiffCallback(oldList, phoneBookList)
        )
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_phone_book, parent, false)
        ) {
        private val mPhoneNumberTv: TextView = itemView.phone_number
        private val mJobInfo: TextView = itemView.phone_book_job_info
        private val mPersonInfoTv: TextView = itemView.phone_book_person_info

        @SuppressLint("SetTextI18n")
        fun bind(
            userName: String, deptName: String, jobName: String?, phoneNumber: String?
        ) {
            mPhoneNumberTv.text = phoneNumber
            if (jobName == null) {
                mPersonInfoTv.text = "$userName / $deptName"
                mJobInfo.text = "(업무정보 없음)"
            } else {
                mPersonInfoTv.text = "$userName / $deptName"
                mJobInfo.text = "$jobName"
            }
        }
    }

    class PhoneBookItemDiffCallback(
        private var oldPhoneBookList: List<PhoneBookInfo>,
        private var newPhoneBookList: List<PhoneBookInfo>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldPhoneBookList[oldItemPosition].uSER_CD == newPhoneBookList[newItemPosition].uSER_CD)
        }

        override fun getOldListSize(): Int {
            return oldPhoneBookList.size
        }

        override fun getNewListSize(): Int {
            return newPhoneBookList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldPhoneBookList[oldItemPosition] == newPhoneBookList[newItemPosition])
        }
    }

}
