package me.santiagoalvarez.kogiaplicanttest.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.santiagoalvarez.kogiaplicanttest.home.MainActivity
import me.santiagoalvarez.kogiaplicanttest.home.MainActivityModule


/**
 * @author santiagoalvarez
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun bindMainActivity(): MainActivity
}