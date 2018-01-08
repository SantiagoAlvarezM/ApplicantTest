package me.santiagoalvarez.kogiaplicanttest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import me.santiagoalvarez.kogiaplicanttest.KogiApplication
import javax.inject.Singleton

/**
 * @author santiagoalvarez
 */
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent : AndroidInjector<KogiApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<KogiApplication>()
}