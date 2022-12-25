package com.example.samrajyalakshmitemple.models

import com.google.gson.annotations.SerializedName

data class UploadImageResponse (
    @SerializedName("data"    ) var data    : Data6?    = Data6(),
    @SerializedName("success" ) var success : Boolean? = null,
    @SerializedName("status"  ) var status  : Int?     = null
        )

data class Data6(
    @SerializedName("url"         ) var url        : String? = null
)