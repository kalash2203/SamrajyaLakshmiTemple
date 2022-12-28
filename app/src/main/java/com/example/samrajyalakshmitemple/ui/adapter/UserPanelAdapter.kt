package com.example.samrajyalakshmitemple.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.samrajyalakshmitemple.databinding.ItemUserPanelBinding
import com.example.samrajyalakshmitemple.models.Data4
import com.example.samrajyalakshmitemple.models.GetStartedSlider
import com.example.samrajyalakshmitemple.utils.ChangeRoleInterface


class UserPanelAdapter(
    val context: Context,
    var data: List<Data4> = arrayListOf(),
    val changeRoleInterface: ChangeRoleInterface
) :
    RecyclerView.Adapter<UserPanelAdapter.GetStartedViewHolder>() {


    class GetStartedViewHolder(val itemBinding: ViewBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetStartedViewHolder {
        return GetStartedViewHolder(
            ItemUserPanelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: GetStartedViewHolder, position: Int) {

        with(holder) {
            val result=data.get(position)
            (this.itemBinding as ItemUserPanelBinding).userEmail.setText(result.email)
            this.itemBinding.srNo.setText(position+1)
            if(result.role=="admin")
            {
                this.itemBinding.removeAdminUser.setText("Remove Admin")
                this.itemBinding.makeAdmin.setText("Already Admin")
            }
            else
            {

                this.itemBinding.removeAdminUser.setText("Remove User")
                this.itemBinding.makeAdmin.setText(("Make Admin"))
            }

            this.itemBinding.makeAdmin.setOnClickListener {
                     if (result.role=="user"){
                         changeRoleInterface.onChangeRole("admin",result.email)
                     }
            }
            this.itemBinding.removeAdminUser.setOnClickListener {
                if (result.role=="user"){
                    changeRoleInterface.onRemoveUser(result.Id)
                }else{
                    changeRoleInterface.onChangeRole("user",result.email)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
