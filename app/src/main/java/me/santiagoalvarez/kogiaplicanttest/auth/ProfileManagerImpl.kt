package me.santiagoalvarez.kogiaplicanttest.auth

import com.twitter.sdk.android.core.SessionManager
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import me.santiagoalvarez.kogiaplicanttest.util.PreferenceHelper
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
class ProfileManagerImpl @Inject constructor(val preferenceHelper: PreferenceHelper) : ProfileManager {

    override fun isAuthenticated(accountType: AccountType): Boolean {
        return when (accountType) {
            AccountType.TWITTER -> fetchTwitterSessionManager().activeSession != null
            AccountType.INSTAGRAM -> !getInstagramAccessToken().isNullOrBlank()
        }
    }

    override fun closeSession(accountType: AccountType) {
        when (accountType) {
            AccountType.TWITTER -> fetchTwitterSessionManager().clearActiveSession()
            AccountType.INSTAGRAM -> saveInstagramAccessToken("")
        }
    }

    override fun getTwitterSession(): TwitterSession = fetchTwitterSessionManager().activeSession

    override fun getTwitterUsername(): String? = fetchTwitterSessionManager().activeSession.userName

    private fun fetchTwitterSessionManager(): SessionManager<TwitterSession> =
            TwitterCore.getInstance().sessionManager

    override fun getInstagramAccessToken(): String? =
            preferenceHelper.getString(preferenceHelper.KEY_INSTAGRAM_TOKEN)

    override fun saveInstagramAccessToken(token: String) =
            preferenceHelper.putString(preferenceHelper.KEY_INSTAGRAM_TOKEN, token)
}