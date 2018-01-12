package me.santiagoalvarez.kogiaplicanttest.auth

import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import javax.inject.Inject

/**
 * Manager in charge of interaction with Instagram & Twitter services
 * @author santiagoalvarez
 */
class ProfileManager @Inject constructor() {

    /**
     * Get the [TwitterSession]
     */
    fun fetchTwitterSession(): TwitterSession
            = TwitterCore.getInstance().sessionManager.activeSession
}