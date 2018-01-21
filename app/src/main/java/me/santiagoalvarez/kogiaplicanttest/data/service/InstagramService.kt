package me.santiagoalvarez.kogiaplicanttest.data.service

import me.santiagoalvarez.kogiaplicanttest.data.service.model.InstagramUser
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author santiagoalvarez.
 */
interface InstagramService {

    /**
     * Get information about the owner of the access_token.
     */
    @GET("/v1/users/self/?access_token")
    fun fetchUser(): Call<InstagramUser>
}