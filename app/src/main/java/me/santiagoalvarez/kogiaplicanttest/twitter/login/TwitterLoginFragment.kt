package me.santiagoalvarez.kogiaplicanttest.twitter.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.common.base.Preconditions.checkArgument
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_twitter_login.*
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import javax.inject.Inject


/**
 * @author santiagoalvarez
 */
@ActivityScoped
class TwitterLoginFragment @Inject constructor() : DaggerFragment(), TwitterLoginContract.View {

    @Inject lateinit var presenter: TwitterLoginContract.Presenter
    lateinit var loginListener: OnLoginListener

    interface OnLoginListener {
        fun finishActivity(withResultOk: Boolean)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_twitter_login, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        checkArgument(context is OnLoginListener, "Activity must implement OnLoginListener")
        loginListener = context as OnLoginListener
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
        loginListener.finishActivity(false)
    }

    override fun onLoginSuccess() {
        loginListener.finishActivity(true)
    }

}
