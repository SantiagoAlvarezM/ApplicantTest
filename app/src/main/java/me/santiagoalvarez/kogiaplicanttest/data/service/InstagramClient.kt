package me.santiagoalvarez.kogiaplicanttest.data.service

import me.santiagoalvarez.kogiaplicanttest.data.service.model.InstagramUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author santiagoalvarez.
 */
interface InstagramClient {

    /**
     * Get information about the owner of the access_token.
     */
    @GET("/v1/users/self/?access_token")
    fun fetchUser(@Query("access_token") accessToken: String): Call<InstagramUser>
}