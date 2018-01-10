package me.santiagoalvarez.kogiaplicanttest.twitter.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_twitter_login.*
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


/**
 * A placeholder fragment containing a simple view.
 */
@ActivityScoped
class TwitterLoginFragment @Inject constructor() : DaggerFragment(), TwitterLoginContract.View {

    @Inject lateinit var presenter: TwitterLoginContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_twitter_login, container, false)

        login_button.callback = object : Callback<TwitterSession>() {

            override fun success(result: Result<TwitterSession>) {
                toast("success")
            }

            override fun failure(exception: TwitterException) {
                toast("failure")
            }
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.bindView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun showLoadingIndicator(active: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoginFailMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigate() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
