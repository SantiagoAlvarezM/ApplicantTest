package me.santiagoalvarez.kogiaplicanttest.twitter.login

import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
@ActivityScoped
class TwitterLoginPresenter @Inject constructor() :
        TwitterLoginContract.Presenter {

    var view: TwitterLoginContract.View? = null

    override fun bindView(view: TwitterLoginContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }


}