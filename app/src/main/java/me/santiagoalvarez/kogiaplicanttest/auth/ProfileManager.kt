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
     * Close session on given [AccountType]
     * @param accountType
     */
    fun closeSession(accountType: AccountType)

    /**
     * Get the active [TwitterSession]
     * @return [TwitterSession]
     */
    fun getTwitterSession(): TwitterSession

    /**
     * Get the Twitter username from [TwitterSession.userName]
     * @return [String] username
     */
    fun getTwitterUsername(): String?

    /**
     * Get the AccessToken used for Instagram request
     */
    fun getInstagramAccessToken(): String?

    /**
     * Save the Instagram AccessToken
     */
    fun saveInstagramAccessToken(token: String)
}