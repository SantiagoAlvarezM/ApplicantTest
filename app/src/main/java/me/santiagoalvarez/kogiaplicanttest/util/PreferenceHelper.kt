package me.santiagoalvarez.kogiaplicanttest.util

import android.content.Context
import android.preference.PreferenceManager
import me.santiagoalvarez.kogiaplicanttest.R
import javax.inject.Inject

/**
 * @author santiagoalvarez.
 */
class PreferenceHelper @Inject constructor(val context: Context) {

    val KEY_INSTAGRAM_TOKEN = context.getString(R.string.pref_key_instagram_token)

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun putString(key: String, value: String) = sharedPreferences.edit().putString(key, value).apply()

    fun getString(key: String): String = sharedPreferences.getString(key, "")

}