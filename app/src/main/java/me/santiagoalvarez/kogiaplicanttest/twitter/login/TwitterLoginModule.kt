package me.santiagoalvarez.kogiaplicanttest.twitter.login

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import me.santiagoalvarez.kogiaplicanttest.di.FragmentScoped


/**
 * @author santiagoalvarez
 */
@Module
abstract class TwitterLoginModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun twitterloginFragment(): TwitterLoginFragment

    @ActivityScoped
    @Binds
    internal abstract fun twitterLoginPresenter(presenter: TwitterLoginPresenter): TwitterLoginContract.Presenter
}