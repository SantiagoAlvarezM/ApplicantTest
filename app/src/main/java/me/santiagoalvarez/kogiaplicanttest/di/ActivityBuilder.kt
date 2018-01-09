package me.santiagoalvarez.kogiaplicanttest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.santiagoalvarez.kogiaplicanttest.home.MainActivity
import me.santiagoalvarez.kogiaplicanttest.home.MainActivityModule
import me.santiagoalvarez.kogiaplicanttest.preferences.SettingsActivity
import me.santiagoalvarez.kogiaplicanttest.preferences.SettingsActivityModule


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
}