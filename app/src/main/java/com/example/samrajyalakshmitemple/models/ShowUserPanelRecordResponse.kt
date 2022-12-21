package com.example.samrajyalakshmitemple.models

import com.google.gson.annotations.SerializedName

data class ShowUserPanelRecordResponse (
    @SerializedName("status"  ) var status  : Boolean?        = null,
    @SerializedName("message" ) var message : String?         = null,
    @SerializedName("data"    ) var data    : ArrayList<Data4> = arrayListOf()

)

data class Data4 (

    @SerializedName("_id"       ) var Id        : String? = null,
    @SerializedName("email"     ) var email     : String? = null,
    @SerializedName("__v"       ) var _v        : Int?    = null,
    @SerializedName("createdAt" ) var createdAt : String? = null,
    @SerializedName("name"      ) var name      : String? = null,
    @SerializedName("role"      ) var role      : String? = null,
    @SerializedName("updatedAt" ) var updatedAt : String? = null

)

