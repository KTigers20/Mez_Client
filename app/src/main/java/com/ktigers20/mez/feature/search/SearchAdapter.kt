package com.ktigers20.mez.feature.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ktigers20.mez.R
import com.ktigers20.mez.data.entity.BatchInfo
import kotlinx.android.synthetic.main.item_batch_info.view.*

class SearchAdapter(
    private val batchInfoList: ArrayList<BatchInfo>
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return batchInfoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = batchInfoList[position]
        holder.bind(item.jOB_NAME, item.uSER_NM, item.dEPT_NM, item.oRDER_TIME)
    }

    fun submitList(newBatchInfoList: ArrayList<BatchInfo>) {
        val oldList: ArrayList<BatchInfo> = ArrayList()
        batchInfoList.map {
            oldList.add(it)
        }
        newBatchInfoList.map {
            batchInfoList.add(it)
        }
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            SearchAdapter.BatchInfoDiffCallback(oldList, batchInfoList)
        )
        diffResult.dispatchUpdatesTo(this)
    }


    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_batch_info, parent, false)
        ) {
        private val mBatchNameTv: TextView = itemView.batch_name
        private val mDeptAndUserTv: TextView = itemView.dept_and_user
        private val mOrderTimeTV: TextView = itemView.order_time

        @SuppressLint("SetTextI18n")
        fun bind(batchName: String, userName: String, deptName: String, orderTime: String) {
            mBatchNameTv.text = batchName
            mDeptAndUserTv.text = "$userName / $deptName"
            mOrderTimeTV.text = "시작 시간 : $orderTime"
        }
    }


    class BatchInfoDiffCallback(
        private var oldBatchInfoList: List<BatchInfo>,
        private var newBatchInfoList: List<BatchInfo>
    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldBatchInfoList[oldItemPosition].oRDER_ID == newBatchInfoList[newItemPosition].oRDER_ID)
        }

        override fun getOldListSize(): Int {
            return oldBatchInfoList.size
        }

        override fun getNewListSize(): Int {
            return newBatchInfoList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldBatchInfoList[oldItemPosition] == newBatchInfoList[newItemPosition])
        }

    }
}
