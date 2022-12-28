package com.example.samrajyalakshmitemple.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.samrajyalakshmitemple.databinding.ItemDonationRecordBinding
import com.example.samrajyalakshmitemple.databinding.ItemUserPanelBinding
import com.example.samrajyalakshmitemple.models.Data4
import com.example.samrajyalakshmitemple.models.Data5
import com.example.samrajyalakshmitemple.models.GetStartedSlider
import com.example.samrajyalakshmitemple.utils.ChangeRoleInterface


class DonationRecordAdapter(
    val context: Context,
    var data: List<Data5> = arrayListOf()
) :
    RecyclerView.Adapter<DonationRecordAdapter.GetStartedViewHolder>() {


    class GetStartedViewHolder(val itemBinding: ViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetStartedViewHolder {
        return GetStartedViewHolder(
            ItemDonationRecordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: GetStartedViewHolder, position: Int) {

        with(holder) {
            val result=data.get(position)
            (this.itemBinding as ItemDonationRecordBinding).userEmail.setText(result.email)
            this.itemBinding.srNo.setText(position+1)
            this.itemBinding.userDescription.setText(result.description)
            this.itemBinding.userAmount.setText("${result.currencyType?.uppercase()} - ${result.amount}")
            this.itemBinding.userTrId.setText(result.transactionId)
            this.itemBinding.userDate.setText(result.date)

            }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}
