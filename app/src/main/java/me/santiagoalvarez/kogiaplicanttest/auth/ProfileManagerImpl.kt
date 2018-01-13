package me.santiagoalvarez.kogiaplicanttest.auth

import com.twitter.sdk.android.core.TwitterSession
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
class ProfileManagerImpl @Inject constructor() : ProfileManager {

    @Inject lateinit var twitterSession: TwitterSession

    override fun isAuthenticated(accountType: AccountType): Boolean {
        return when (accountType) {
            AccountType.TWITTER -> twitterSession.authToken != null
            AccountType.INSTAGRAM -> false //TODO("not implemented")
        }
    }

    override fun fetchTwitterSession(): TwitterSession = twitterSession

    override fun getTwitterUsername(): String = twitterSession.userName
}