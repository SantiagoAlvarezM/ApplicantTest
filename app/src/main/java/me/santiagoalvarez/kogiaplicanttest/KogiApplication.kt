package me.santiagoalvarez.kogiaplicanttest

import dagger.android.*
import me.santiagoalvarez.kogiaplicanttest.di.DaggerApplicationComponent


/**
 * @author santiagoalvarez
 */

class KogiApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val applicationComponent = DaggerApplicationComponent.builder()
                .create(this)
        applicationComponent.inject(this)
        return applicationComponent
    }

}
