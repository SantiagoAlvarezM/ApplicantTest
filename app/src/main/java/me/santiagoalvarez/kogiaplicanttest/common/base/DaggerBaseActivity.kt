package me.santiagoalvarez.kogiaplicanttest.common.base

import android.os.Bundle
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * @author santiagoalvarez.
 */
abstract class DaggerBaseActivity : BaseActivity(), HasFragmentInjector, HasSupportFragmentInjector {

    @Inject internal var supportFragmentInjector: DispatchingAndroidInjector<Fragment>? = null
    @Inject internal var frameworkFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return supportFragmentInjector
    }

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment>? {
        return frameworkFragmentInjector
    }
}