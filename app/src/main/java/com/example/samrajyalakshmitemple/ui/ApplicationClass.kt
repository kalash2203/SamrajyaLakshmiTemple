package com.example.samrajyalakshmitemple.ui

import android.app.Application
import com.example.samrajyalakshmitemple.utils.SavedPrefManager

class ApplicationClass : Application() {
    var TOKEN = ""

   fun setToken(token : String){
       TOKEN = token
   }

    fun getToken() : String{
        return TOKEN
    }
}