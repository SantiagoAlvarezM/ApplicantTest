package me.santiagoalvarez.kogiaplicanttest.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.santiagoalvarez.kogiaplicanttest.KogiApplication
import javax.inject.Singleton

/**
 * @author santiagoalvarez
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class,
    ActivityBuilder::class])
interface ApplicationComponent : AndroidInjector<KogiApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}