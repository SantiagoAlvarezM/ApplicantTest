package me.santiagoalvarez.kogiaplicanttest.di

import android.app.Application
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * @author santiagoalvarez
 */
@Module
class ApplicationModule {

    @Provides
    fun providesTwitterConfig(application: Application): TwitterConfig
            = TwitterConfig.Builder(application)
            .logger(DefaultLogger(Log.DEBUG))
            .debug(true)
            .build()
}