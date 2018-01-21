package me.santiagoalvarez.kogiaplicanttest.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig
import dagger.Module
import dagger.Provides
import me.santiagoalvarez.kogiaplicanttest.auth.AccountType
import me.santiagoalvarez.kogiaplicanttest.auth.ProfileManager
import me.santiagoalvarez.kogiaplicanttest.auth.ProfileManagerImpl
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseActivity
import me.santiagoalvarez.kogiaplicanttest.data.service.AuthInterceptor
import me.santiagoalvarez.kogiaplicanttest.data.service.InstagramClient
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramConfig.INSTAGRAM_API_BASE_URL
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramLoginActivity
import me.santiagoalvarez.kogiaplicanttest.navigation.Navigator
import me.santiagoalvarez.kogiaplicanttest.twitter.login.TwitterLoginActivity
import me.santiagoalvarez.kogiaplicanttest.util.PreferenceHelper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @author santiagoalvarez
 */
@Module
class ApplicationModule {

    @Provides
    fun providesContext(application: Application): Context = application.applicationContext

    @Provides
    fun providesPreferenceHelper(context: Context): PreferenceHelper = PreferenceHelper(context)

    @Provides
    fun providesProfileManager(preferenceHelper: PreferenceHelper): ProfileManager =
            ProfileManagerImpl(preferenceHelper)

    @Provides
    fun providesTwitterConfig(application: Application): TwitterConfig = TwitterConfig.Builder(application)
            .logger(DefaultLogger(Log.DEBUG))
            .debug(true)
            .build()

    @Provides
    fun providesRetrofitInstagramClient(profileManager: ProfileManager): InstagramClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        val authInterceptor = AuthInterceptor(profileManager.getInstagramAccessToken()!!)
        if (!okHttpClientBuilder.interceptors().contains(authInterceptor)) {
            okHttpClientBuilder.addInterceptor(authInterceptor)
        }
        return Retrofit.Builder()
                .baseUrl(INSTAGRAM_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder.build())
                .build()
                .create(InstagramClient::class.java)
    }

    @Provides
    fun providesNavigationListener(context: Context, profileManager: ProfileManager):
            Navigator.NavigationListener = Navigator.NavigationListener { navigator, entry ->
        if (AccountType.TWITTER == entry.loginType &&
                entry.isLoginRequired(AccountType.TWITTER) &&
                !profileManager.isAuthenticated(AccountType.TWITTER)) {
            navigator.addPendingNavigation(BaseActivity.REQUEST_SIGNIN.toString(), entry)
            navigator.to(TwitterLoginActivity.createIntent(context))
                    .withRequestCode(BaseActivity.REQUEST_SIGNIN)
                    .navigate()
            return@NavigationListener true
        } else if (AccountType.INSTAGRAM == entry.loginType &&
                entry.isLoginRequired(AccountType.INSTAGRAM) &&
                !profileManager.isAuthenticated(AccountType.INSTAGRAM)) {
            navigator.addPendingNavigation(BaseActivity.REQUEST_SIGNIN.toString(), entry)
            navigator.to(InstagramLoginActivity.createIntent(context))
                    .withRequestCode(BaseActivity.REQUEST_SIGNIN)
                    .navigate()
            return@NavigationListener true
        }
        return@NavigationListener false
    }
}