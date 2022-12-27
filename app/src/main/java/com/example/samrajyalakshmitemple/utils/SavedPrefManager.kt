package com.example.samrajyalakshmitemple.utils



import android.content.Context
import android.content.SharedPreferences
import android.media.Image
import com.example.samrajyalakshmitemple.utils.Constants.EMAIL
import com.example.samrajyalakshmitemple.utils.Constants.ISLOGIN
import com.example.samrajyalakshmitemple.utils.Constants.NAME
import com.example.samrajyalakshmitemple.utils.Constants.TOKEN


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
    fun putUserDetails(name:String?,email:String?,phone:String?,city:String?,country:String?,role:String?,image:String?){
        editor?.putString(NAME, name)
        editor?.putString(EMAIL, email)
        editor?.putString(Constants.NUMBER, phone)
        editor?.putString(Constants.CITY, city)
        editor?.putString(Constants.COUNTRY, country)
        editor?.putString(Constants.ROLE,role)
        editor?.putString(Constants.IMAGE,image)
        editor?.apply()
    }
    fun putToken(token:String?){
        editor?.putString(TOKEN,token)
        editor?.apply()
    }
    fun getToken() : String? {
        return sharedPreferences!!.getString(TOKEN,"")
    }
    fun putEmail(email:String?){
        editor?.putString(Constants.EMAIL,email)
        editor?.apply()
    }
    fun getEmail() : String? {
        return sharedPreferences!!.getString(EMAIL,"")
    }

}