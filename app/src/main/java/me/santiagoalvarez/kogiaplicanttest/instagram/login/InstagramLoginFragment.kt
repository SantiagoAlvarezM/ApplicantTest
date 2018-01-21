package me.santiagoalvarez.kogiaplicanttest.instagram.login

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.Snackbar
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_instagram_login.*
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseLoginFragment
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramConfig.INSTAGRAM_AUTH_URL
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramConfig.INSTAGRAM_REDIRECT_URI
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramConfig.INSTAGRAM_URL
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import org.jetbrains.anko.support.v4.browse
import javax.inject.Inject


/**
 * @author santiagoalvarez
 */
@ActivityScoped
class InstagramLoginFragment @Inject constructor() : BaseLoginFragment(), InstagramLoginContract.View {

    @Inject
    lateinit var presenter: InstagramLoginContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_instagram_login, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wVInstagramLogin.settings.javaScriptEnabled = true
        wVInstagramLogin.webViewClient = InstagramLoginWebViewClient()
        wVInstagramLogin.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && wVInstagramLogin.canGoBack()) {
                wVInstagramLogin.goBack()
                true
            } else
                false
        }
        wVInstagramLogin.loadUrl(INSTAGRAM_AUTH_URL)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun finishLogin(withResultOk: Boolean) {
        Snackbar.make(wVInstagramLogin, R.string.general_login_success,
                Snackbar.LENGTH_LONG)
                .addCallback(object : Snackbar.Callback() {
                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                        super.onDismissed(transientBottomBar, event)
                        loginListener.onLoginFinish(withResultOk)
                    }
                })
                .show()
    }

    override fun showLoginErrorMessage() {
        Snackbar.make(wVInstagramLogin, R.string.general_login_fails, Snackbar.LENGTH_LONG)
                .show()
    }

    override fun showUserDeniedMessage() {
        Snackbar.make(wVInstagramLogin, R.string.general_login_denied, Snackbar.LENGTH_LONG)
                .show()
    }

    private inner class InstagramLoginWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            return if (url == null) false
            else evaluateUrl(Uri.parse(url))
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return if (request == null || request.url == null) super.shouldOverrideUrlLoading(view, request)
            else evaluateUrl(request.url)
        }

        fun evaluateUrl(url: Uri): Boolean {
            return when {
                INSTAGRAM_URL.contains(url.host) -> false
                INSTAGRAM_REDIRECT_URI.contains(url.host) -> presenter.handleUri(url)
                else -> {
                    browse(url.toString())
                    true
                }
            }
        }
    }
}
