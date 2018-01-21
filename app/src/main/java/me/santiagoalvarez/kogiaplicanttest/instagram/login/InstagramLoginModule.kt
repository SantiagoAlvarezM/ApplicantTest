package me.santiagoalvarez.kogiaplicanttest.instagram.login

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import me.santiagoalvarez.kogiaplicanttest.di.FragmentScoped

/**
 * @author santiagoalvarez.
 */
@Module
abstract class InstagramLoginModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun instagramloginFragment(): InstagramLoginFragment

    @ActivityScoped
    @Binds
    internal abstract fun instagramLoginPresenter(presenter: InstagramLoginPresenter): InstagramLoginContract.Presenter
}