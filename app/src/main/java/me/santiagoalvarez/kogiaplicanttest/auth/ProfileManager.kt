package me.santiagoalvarez.kogiaplicanttest.auth

import com.twitter.sdk.android.core.TwitterSession

/**
 * Manager in charge of interaction with Instagram & Twitter services
 * @author santiagoalvarez
 */
interface ProfileManager {

    /**
     * Determines if user is authenticated for a given [AccountType]
     * @param accountType
     * @return true if is authenticated, false otherwise
     */
    fun isAuthenticated(accountType: AccountType): Boolean

    /**
     * Get the active [TwitterSession]
     * @return [TwitterSession]
     */
    fun fetchTwitterSession(): TwitterSession

    /**
     * Get the Twitter username from [TwitterSession]
     * @return [String] username
     */
    fun getTwitterUsername(): String
}