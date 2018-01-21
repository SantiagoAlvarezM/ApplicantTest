package me.santiagoalvarez.kogiaplicanttest.twitter.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.fragment_twitter_login.*
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseLoginFragment
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import javax.inject.Inject


/**
 * @author santiagoalvarez
 */
@ActivityScoped
class TwitterLoginFragment @Inject constructor() : BaseLoginFragment(), TwitterLoginContract.View {

    @Inject lateinit var presenter: TwitterLoginContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_twitter_login, container, false)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        login_button.onActivityResult(requestCode, resultCode, data)
    }

    override fun setCallback(callback: Callback<TwitterSession>) {
        login_button.callback = callback
    }

    override fun onLoginFail() {
        loginListener.onLoginFinish(false)
    }

    override fun onLoginSuccess() {
        loginListener.onLoginFinish(true)
    }

}
