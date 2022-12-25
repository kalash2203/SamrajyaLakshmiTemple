package com.example.samrajyalakshmitemple.models

import com.google.gson.annotations.SerializedName

data class EditProfileResponse(
    @SerializedName("status"  ) var status  : Boolean? = null,
    @SerializedName("message" ) var message : String?  = null,
    @SerializedName("data"    ) var data    : Data7?    = Data7()

)

data class Data7 (

    @SerializedName("acknowledged"  ) var acknowledged  : Boolean? = null,
    @SerializedName("modifiedCount" ) var modifiedCount : Int?     = null,
    @SerializedName("upsertedId"    ) var upsertedId    : String?  = null,
    @SerializedName("upsertedCount" ) var upsertedCount : Int?     = null,
    @SerializedName("matchedCount"  ) var matchedCount  : Int?     = null

)
