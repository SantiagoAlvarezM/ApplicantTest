package me.santiagoalvarez.kogiaplicanttest.auth

import com.twitter.sdk.android.core.SessionManager
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
class ProfileManagerImpl @Inject constructor() : ProfileManager {

    override fun isAuthenticated(accountType: AccountType): Boolean {
        return when (accountType) {
            AccountType.TWITTER -> fetchSessionManager().activeSession != null
            AccountType.INSTAGRAM -> false //TODO not implemented
        }
    }

    override fun closeSession(accountType: AccountType) {
        when (accountType) {
            AccountType.TWITTER -> fetchSessionManager().clearActiveSession()
            AccountType.INSTAGRAM -> { //TODO not implemented
            }
        }
    }

    override fun getTwitterSession(): TwitterSession = fetchSessionManager().activeSession

    override fun getTwitterUsername(): String? = fetchSessionManager().activeSession.userName

    private fun fetchSessionManager(): SessionManager<TwitterSession> =
            TwitterCore.getInstance().sessionManager
}