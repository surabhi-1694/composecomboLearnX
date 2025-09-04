package com.example.ecomapp.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

object SharedPref {
    private var mSharedPref: SharedPreferences? = null
    const val PREF_NAME: String = "NG_CAD_FIRST_RESPONDER"
    const val ACCESS_TOKEN: String = "ACCESS_TOKEN"
    const val IS_READ_UNREAD_REQUIRED: String = "isReadUnreadRequired"
    const val Access_State_Device: String = "accessStateDevice"


    fun init(context: Context) {

        

        if (SharedPref.mSharedPref == null) SharedPref.mSharedPref =
            context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE)
    }


    fun getString(key: String?, defValue: String?): String? {
        return SharedPref.mSharedPref?.getString(key, defValue)
    }

    fun putString(key: String?, value: String?) {
        val prefsEditor: SharedPreferences.Editor = SharedPref.mSharedPref!!.edit()
        prefsEditor.putString(key, value)
        prefsEditor.apply()
    }

    fun getInteger(key: String?, defValue: Int): Int {
        return SharedPref.mSharedPref?.getInt(key, defValue) ?: 0
    }

    fun putInteger(key: String?, value: Int) {
        val prefsEditor: SharedPreferences.Editor = SharedPref.mSharedPref!!.edit()
        prefsEditor.putInt(key, value)
        prefsEditor.apply()
    }


    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return SharedPref.mSharedPref!!.getBoolean(key, defValue)
    }

    fun putBoolean(key: String?, value: Boolean) {
        val prefsEditor: SharedPreferences.Editor = SharedPref.mSharedPref!!.edit()
        prefsEditor.putBoolean(key, value)
        prefsEditor.apply()
    }

    fun getLong(key: String?, defValue: Long): Long {
        return SharedPref.mSharedPref!!.getLong(key, defValue)
    }

    fun putLong(key: String?, value: Long) {
        val prefsEditor: SharedPreferences.Editor = SharedPref.mSharedPref!!.edit()
        prefsEditor.putLong(key, value)
        prefsEditor.apply()
    }


    fun getFloat(key: String?, defValue: Float): Float {
        return SharedPref.mSharedPref!!.getFloat(key, defValue)
    }

    fun putFloat(key: String?, value: Float) {
        val prefsEditor: SharedPreferences.Editor = SharedPref.mSharedPref!!.edit()
        prefsEditor.putFloat(key, value)
        prefsEditor.apply()
    }


//    /**/ Clear Preference //// */
    fun clearPreference(context: Context?) {
        SharedPref.mSharedPref!!.edit().clear().apply()
    }

//    /**/ Remove //// */
    fun removePreference(Key: String?) {
        SharedPref.mSharedPref!!.edit().remove(Key).apply()
    }
}