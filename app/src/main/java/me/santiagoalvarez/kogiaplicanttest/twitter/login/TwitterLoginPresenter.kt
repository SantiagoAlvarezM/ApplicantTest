package me.santiagoalvarez.kogiaplicanttest.twitter.login

import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
@ActivityScoped
class TwitterLoginPresenter @Inject constructor() : TwitterLoginContract.Presenter {

    var view: TwitterLoginContract.View? = null

    override fun takeView(view: TwitterLoginContract.View) {
        this.view = view
        view.setCallback(object : Callback<TwitterSession>() {

            override fun success(result: Result<TwitterSession>) {
                view.onLoginSuccess()
            }

            override fun failure(exception: TwitterException) {
                view.onLoginFail()
            }
        })
    }

    override fun dropView() {
        view = null
    }
}