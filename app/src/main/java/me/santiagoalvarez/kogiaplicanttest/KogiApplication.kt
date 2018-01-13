package me.santiagoalvarez.kogiaplicanttest

import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterConfig
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import me.santiagoalvarez.kogiaplicanttest.di.DaggerApplicationComponent
import javax.inject.Inject


/**
 * @author santiagoalvarez
 */

class KogiApplication : DaggerApplication() {

    @Inject lateinit var twitterConfig: TwitterConfig

    override fun onCreate() {
        super.onCreate()
        Twitter.initialize(twitterConfig)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().application(this).build()
    }


}
