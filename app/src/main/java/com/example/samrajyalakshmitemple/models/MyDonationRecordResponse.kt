package com.example.samrajyalakshmitemple.models

import com.google.gson.annotations.SerializedName

data class MyDonationRecordResponse (
    @SerializedName("status"  ) var status  : Boolean?          = null,
    @SerializedName("message" ) var message : String?           = null,
    @SerializedName("data"    ) var data    : ArrayList<String> = arrayListOf()

)
