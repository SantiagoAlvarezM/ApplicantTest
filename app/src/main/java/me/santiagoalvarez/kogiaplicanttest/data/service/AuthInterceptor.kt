package me.santiagoalvarez.kogiaplicanttest.data.service

import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramConfig.ACCESS_TOKEN_FRAGMENT
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author santiagoalvarez.
 */
class AuthInterceptor(private var authToken: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        if (chain != null) {
            val originalRequest = chain.request()
            val httpUrl = originalRequest.url().newBuilder()
                    .addQueryParameter(ACCESS_TOKEN_FRAGMENT, authToken)
                    .build()
            return chain.proceed(originalRequest.newBuilder()
                    .url(httpUrl)
                    .build())
        }
        return null
    }
}