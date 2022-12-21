package com.example.samrajyalakshmitemple.models

import com.google.gson.annotations.SerializedName

data class RemoveUserResponse (
    @SerializedName("status"  ) var status  : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Data2?    = Data2()
        )

data class Data2 (

    @SerializedName("acknowledged" ) var acknowledged : Boolean? = null,
    @SerializedName("deletedCount" ) var deletedCount : Int?     = null

)