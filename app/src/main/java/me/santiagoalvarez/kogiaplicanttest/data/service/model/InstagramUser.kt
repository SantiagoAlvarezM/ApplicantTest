package me.santiagoalvarez.kogiaplicanttest.data.service.model

import com.google.gson.annotations.SerializedName

/**
 * @author santiagoalvarez.
 */
data class InstagramUser(
        @SerializedName("data") val data: Data,
        @SerializedName("meta") val meta: Meta
)

data class Data(
        @SerializedName("id") val id: String,
        @SerializedName("username") val username: String,
        @SerializedName("profile_picture") val profilePicture: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("bio") val bio: String,
        @SerializedName("website") val website: String,
        @SerializedName("is_business") val isBusiness: Boolean,
        @SerializedName("counts") val counts: Counts
)

data class Counts(
        @SerializedName("media") val media: Int,
        @SerializedName("follows") val follows: Int,
        @SerializedName("followed_by") val followedBy: Int
)

data class Meta(
        @SerializedName("code") val code: Int
)