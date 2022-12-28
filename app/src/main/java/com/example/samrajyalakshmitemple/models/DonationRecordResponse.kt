package com.example.samrajyalakshmitemple.models

import com.google.gson.annotations.SerializedName

data class DonationRecordResponse (
    @SerializedName("status"  ) var status  : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data5> = arrayListOf()
)

data class Data5 (

    @SerializedName("_id"           ) var Id            : String? = null,
    @SerializedName("name"          ) var name          : String? = null,
    @SerializedName("email"         ) var email         : String? = null,
    @SerializedName("country"       ) var country       : String? = null,
    @SerializedName("amount"        ) var amount        : String? = null,
    @SerializedName("transactionId" ) var transactionId : String? = null,
    @SerializedName("description"   ) var description   : String? = null,
    @SerializedName("phone"         ) var phone         : String? = null,
    @SerializedName("currencyType"  ) var currencyType  : String? = null,
    @SerializedName("date"          ) var date          : String? = null

)