package com.example.samrajyalakshmitemple.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.samrajyalakshmitemple.databinding.ItemSliderBinding
import com.example.samrajyalakshmitemple.models.GetStartedSlider



class GetStartedSliderAdapter(
    val context: Context,
    var data: List<GetStartedSlider> = arrayListOf()
) :
    RecyclerView.Adapter<GetStartedSliderAdapter.GetStartedViewHolder>() {


    class GetStartedViewHolder(val itemBinding: ViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetStartedViewHolder {
        return GetStartedViewHolder(
            ItemSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: GetStartedViewHolder, position: Int) {

        with(holder) {
            (this.itemBinding as ItemSliderBinding).image.setImageDrawable(ContextCompat.getDrawable(context,data[position].image))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
