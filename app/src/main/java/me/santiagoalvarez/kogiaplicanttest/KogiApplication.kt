package me.santiagoalvarez.kogiaplicanttest

import android.app.Activity
import android.app.Application
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterConfig
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.santiagoalvarez.kogiaplicanttest.di.DaggerApplicationComponent
import javax.inject.Inject


/**
 * @author santiagoalvarez
 */

class KogiApplication : Application(), HasActivityInjector {

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var twitterConfig: TwitterConfig

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
                .application(this)
                .build()
                .inject(this)
        Twitter.initialize(twitterConfig)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

}
