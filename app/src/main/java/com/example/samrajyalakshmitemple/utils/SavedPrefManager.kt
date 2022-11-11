package com.example.samrajyalakshmitemple.utils



import android.content.Context
import android.content.SharedPreferences
import com.example.samrajyalakshmitemple.utils.Constants.ISLOGIN


class
SavedPrefManager(context: Context?) {
    private val SHARED_PREF_NAME = "Samrajya Laxmi Temple App"
    private var sharedPreferences: SharedPreferences? =
        context!!.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private var editor: SharedPreferences.Editor? = sharedPreferences?.edit()



    fun isLogin(): Boolean {
        return sharedPreferences!!.getBoolean(ISLOGIN, false)
    }
    fun putLogin(isLogin : Boolean) {
        editor?.putBoolean(ISLOGIN, isLogin)
        editor?.apply()
    }
}