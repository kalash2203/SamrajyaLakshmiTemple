package com.example.samrajyalakshmitemple.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.samrajyalakshmitemple.databinding.ItemMyDonationBinding
import com.example.samrajyalakshmitemple.models.Data8


class MyDonationAdapter(
    val context: Context,
    var data: List<Data8> = arrayListOf()
) :
    RecyclerView.Adapter<MyDonationAdapter.GetStartedViewHolder>() {


    class GetStartedViewHolder(val itemBinding: ViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetStartedViewHolder {
        return GetStartedViewHolder(
            ItemMyDonationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: GetStartedViewHolder, position: Int) {

        with(holder) {
            val result=data.get(position)
            (this.itemBinding as ItemMyDonationBinding).userDescription.setText(result.description)
            this.itemBinding.userAmount.setText("${result.currencyType?.uppercase()} - ${result.amount}")
            this.itemBinding.userTrId.setText(result.transactionId)
            this.itemBinding.userDate.setText(result.date)

            }

    }

    override fun getItemCount(): Int {
        return data.size
    }
}
