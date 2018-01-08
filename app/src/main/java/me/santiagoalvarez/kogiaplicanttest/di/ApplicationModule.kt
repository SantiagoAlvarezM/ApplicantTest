package me.santiagoalvarez.kogiaplicanttest.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule
import me.santiagoalvarez.kogiaplicanttest.KogiApplication
import me.santiagoalvarez.kogiaplicanttest.home.MainActivity
import dagger.android.ContributesAndroidInjector



/**
 * @author santiagoalvarez
 */
@Module(includes = [AndroidSupportInjectionModule::class])
abstract class ApplicationModule {

    @Binds
    abstract fun provideContext(application: KogiApplication): Context

    @ContributesAndroidInjector
    internal abstract fun contributeActivityInjector(): MainActivity
}