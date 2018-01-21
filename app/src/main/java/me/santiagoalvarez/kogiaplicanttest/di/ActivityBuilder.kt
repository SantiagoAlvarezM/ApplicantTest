package me.santiagoalvarez.kogiaplicanttest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.santiagoalvarez.kogiaplicanttest.home.MainActivity
import me.santiagoalvarez.kogiaplicanttest.init.splash.SplashActivity
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramLoginActivity
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramLoginModule
import me.santiagoalvarez.kogiaplicanttest.settings.SettingsActivity
import me.santiagoalvarez.kogiaplicanttest.settings.SettingsActivityModule
import me.santiagoalvarez.kogiaplicanttest.twitter.feed.TwitterLandingActivity
import me.santiagoalvarez.kogiaplicanttest.twitter.feed.TwitterLandingModule
import me.santiagoalvarez.kogiaplicanttest.twitter.login.TwitterLoginActivity
import me.santiagoalvarez.kogiaplicanttest.twitter.login.TwitterLoginModule


/**
 * @author santiagoalvarez
 */
@Module
abstract class ActivityBuilder {

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun bindSplashActivity(): SplashActivity

    @ActivityScoped
    @ContributesAndroidInjector
    internal abstract fun bindMainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [SettingsActivityModule::class])
    internal abstract fun bindSettingsActivity(): SettingsActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TwitterLoginModule::class])
    internal abstract fun bindTwitterLoginActivity(): TwitterLoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TwitterLandingModule::class])
    internal abstract fun bindTwitterLandingActivity(): TwitterLandingActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [InstagramLoginModule::class])
    internal abstract fun bindInstagramLoginActivity(): InstagramLoginActivity
}