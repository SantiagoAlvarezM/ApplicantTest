package me.santiagoalvarez.kogiaplicanttest.auth

import com.twitter.sdk.android.core.TwitterCore
import javax.inject.Inject

/**
 * @author santiagoalvarez.
 */
class AuthenticationManager @Inject constructor() {

    fun isAuthenticated(accountType: AccountType): Boolean {
        return when (accountType) {
            AccountType.TWITTER -> TwitterCore.getInstance().sessionManager.activeSession != null
            AccountType.INSTAGRAM -> false //TODO("not implemented")
        }
    }
}