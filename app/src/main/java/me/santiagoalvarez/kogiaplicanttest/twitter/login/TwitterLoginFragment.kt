package me.santiagoalvarez.kogiaplicanttest.twitter.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.common.base.Preconditions
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_twitter_login.*
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject


/**
 * A placeholder fragment containing a simple view.
 */
@ActivityScoped
class TwitterLoginFragment @Inject constructor() : DaggerFragment(), TwitterLoginContract.View {

    interface OnTwitterLoginListener {
        fun onLoginSuccess()
    }

    @Inject lateinit var presenter: TwitterLoginContract.Presenter
    private var loginListener: OnTwitterLoginListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_twitter_login, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Preconditions.checkArgument(context is OnTwitterLoginListener, "activity must implement OnTwitterLoginListener")
        loginListener = context as OnTwitterLoginListener
    }

    override fun onDetach() {
        super.onDetach()
        loginListener = null
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
        snackbar(iVTwitterLoginLogo.rootView, R.string.pref_dialog_message).show()
    }

    override fun onLoginSuccess() {
        loginListener?.onLoginSuccess()
    }
}
