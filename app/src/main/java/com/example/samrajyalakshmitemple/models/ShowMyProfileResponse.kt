package com.example.samrajyalakshmitemple.models

import com.google.gson.annotations.SerializedName

data class ShowMyProfileResponse(
@SerializedName("token" ) var token : String?  = null,
@SerializedName("data"    ) var data    : User?    = User(),
@SerializedName("message") var message : String?=null

)

data class User (

    @SerializedName("_id"       ) var Id        : String? = null,
    @SerializedName("email"     ) var email     : String? = null,
    @SerializedName("__v"       ) var _v        : Int?    = null,
    @SerializedName("createdAt" ) var createdAt : String? = null,
    @SerializedName("role"      ) var role      : String? = null,
    @SerializedName("updatedAt" ) var updatedAt : String? = null,
    @SerializedName("city"      ) var city      : String? = null,
    @SerializedName("country"   ) var country   : String? = null,
    @SerializedName("img"       ) var img       : String? = null,
    @SerializedName("name"      ) var name      : String? = null,
    @SerializedName("phone"     ) var phone     : String? = null,
    @SerializedName("state"     ) var state     : String? = null

)