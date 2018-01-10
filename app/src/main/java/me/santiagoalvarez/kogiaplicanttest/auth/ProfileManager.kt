package me.santiagoalvarez.kogiaplicanttest.auth

import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession

/**
 * Manager in charge of interaction with Instagram & Twitter services
 * @author santiagoalvarez
 */
interface ProfileManager {

    /**
     * Get the [TwitterSession]
     */
    fun fetchTwitterSession(): TwitterSession

    /**
     * Get the User email from [TwitterAuthClient]
     *
     * @param twitterSession
     */
    fun fetchTwitterEmail(twitterSession: TwitterSession): Callback<String>

    /**
     * Query Authentication state for given [AccountType]
     *
     * @param accountType
     * @return [Boolean] true if is authenticated, false otherwise
     */
    fun isUserAuthenticated(accountType: AccountType): Boolean
}