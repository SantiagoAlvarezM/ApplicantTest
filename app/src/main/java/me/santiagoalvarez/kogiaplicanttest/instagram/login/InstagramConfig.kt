package me.santiagoalvarez.kogiaplicanttest.instagram.login

/**
 * TODO replace this, create an environment class
 * @author santiagoalvarez.
 */
object InstagramConfig {

    val INSTAGRAM_CLIENT_ID = "77ff5432f6a042c8aa8966ab843d54d3"

    val INSTAGRAM_API_BASE_URL = "https://api.instagram.com"
    val INSTAGRAM_URL = "https://www.instagram.com"
    val INSTAGRAM_REDIRECT_URI = "http://app.me.santiagoalvarez.kogiaplicanttest/redirect"

    val ACCESS_TOKEN_FRAGMENT = "access_token"
    val ERROR_REASON_QUERY = "error_reason"

    val EROR_USER_DENIED = "user_denied"

    val INSTAGRAM_AUTH_URL = "${INSTAGRAM_API_BASE_URL}/oauth/authorize/?client_id=${INSTAGRAM_CLIENT_ID}&redirect_uri=${INSTAGRAM_REDIRECT_URI}&response_type=token"
}