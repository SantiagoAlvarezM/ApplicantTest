package me.santiagoalvarez.kogiaplicanttest.di

import android.app.Application
import android.content.Context
import android.util.Log
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.TwitterConfig
import dagger.Module
import dagger.Provides
import me.santiagoalvarez.kogiaplicanttest.auth.AccountType
import me.santiagoalvarez.kogiaplicanttest.auth.AuthenticationManager
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseActivity
import me.santiagoalvarez.kogiaplicanttest.navigation.Navigator
import me.santiagoalvarez.kogiaplicanttest.twitter.login.TwitterLoginActivity


/**
 * @author santiagoalvarez
 */
@Module
class ApplicationModule {

    @Provides
    fun providesContext(application: Application): Context = application

    @Provides
    fun providesTwitterConfig(application: Application): TwitterConfig
            = TwitterConfig.Builder(application)
            .logger(DefaultLogger(Log.DEBUG))
            .debug(true)
            .build()

    @Provides
    fun providesNavigationListener(context: Context, authenticationManager: AuthenticationManager): Navigator.NavigationListener
            = Navigator.NavigationListener { navigator, entry ->
        if (entry.isLoginRequired(AccountType.TWITTER) && !authenticationManager.isAuthenticated(AccountType.TWITTER)) {
            navigator.addPendingNavigation(BaseActivity.REQUEST_SIGNIN.toString(), entry)
            navigator.to(TwitterLoginActivity.createIntent(context)).withRequestCode(BaseActivity.REQUEST_SIGNIN).navigate()
            return@NavigationListener true
        } else if (entry.isLoginRequired(AccountType.INSTAGRAM) && !authenticationManager.isAuthenticated(AccountType.INSTAGRAM)) {
            //TODO("not implemented")
            return@NavigationListener false
        }
        return@NavigationListener false
    }
}