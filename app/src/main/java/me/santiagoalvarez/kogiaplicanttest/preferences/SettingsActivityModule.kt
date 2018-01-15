package me.santiagoalvarez.kogiaplicanttest.preferences

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseActivity
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import me.santiagoalvarez.kogiaplicanttest.di.FragmentScoped
import me.santiagoalvarez.kogiaplicanttest.navigation.IntentNavigationEntry
import me.santiagoalvarez.kogiaplicanttest.navigation.Navigator
import me.santiagoalvarez.kogiaplicanttest.twitter.login.TwitterLoginActivity

/**
 * @author santiagoalvarez
 */
@Module
abstract class SettingsActivityModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun generalPreferenceFragment(): GeneralPreferenceFragment

    @ActivityScoped
    @Binds
    internal abstract fun settingsPresenter(presenter: SettingsPresenter): SettingsContract.Presenter

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun providesTwitterLoginEntry(context: Context, navigator: Navigator): IntentNavigationEntry
                = IntentNavigationEntry.Builder(navigator, TwitterLoginActivity.createIntent(context))
                .withRequestCode(BaseActivity.REQUEST_SIGNIN)
                .build()
    }
}