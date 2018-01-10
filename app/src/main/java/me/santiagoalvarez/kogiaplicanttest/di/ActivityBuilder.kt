package me.santiagoalvarez.kogiaplicanttest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.santiagoalvarez.kogiaplicanttest.home.MainActivity
import me.santiagoalvarez.kogiaplicanttest.home.MainActivityModule
import me.santiagoalvarez.kogiaplicanttest.preferences.SettingsActivity
import me.santiagoalvarez.kogiaplicanttest.preferences.SettingsActivityModule
import me.santiagoalvarez.kogiaplicanttest.twitter.login.TwitterLoginActivity
import me.santiagoalvarez.kogiaplicanttest.twitter.login.TwitterLoginModule


/**
 * @author santiagoalvarez
 */
@Module
abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SettingsActivityModule::class])
    internal abstract fun bindSettingsActivity(): SettingsActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TwitterLoginModule::class])
    internal abstract fun bindTwitterLoginActivity(): TwitterLoginActivity
}