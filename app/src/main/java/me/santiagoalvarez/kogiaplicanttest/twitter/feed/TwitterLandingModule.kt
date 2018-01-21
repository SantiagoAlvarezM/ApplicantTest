package me.santiagoalvarez.kogiaplicanttest.twitter.feed

import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.Timeline
import com.twitter.sdk.android.tweetui.UserTimeline
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import me.santiagoalvarez.kogiaplicanttest.auth.ProfileManager
import me.santiagoalvarez.kogiaplicanttest.di.FragmentScoped

/**
 * @author santiagoalvarez
 */
@Module
abstract class TwitterLandingModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun twitterLandingFragment(): TwitterLandingFragment

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesTimeline(profileManager: ProfileManager): Timeline<Tweet> =
                UserTimeline.Builder()
                        .screenName(profileManager.getTwitterUsername() ?: "")
                        .build()
    }
}