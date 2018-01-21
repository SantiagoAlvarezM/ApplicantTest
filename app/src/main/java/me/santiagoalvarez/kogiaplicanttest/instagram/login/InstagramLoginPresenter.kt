package me.santiagoalvarez.kogiaplicanttest.instagram.login

import android.net.Uri
import me.santiagoalvarez.kogiaplicanttest.auth.AccountType
import me.santiagoalvarez.kogiaplicanttest.auth.ProfileManager
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramConfig.ACCESS_TOKEN_FRAGMENT
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramConfig.EROR_USER_DENIED
import me.santiagoalvarez.kogiaplicanttest.instagram.login.InstagramConfig.ERROR_REASON_QUERY
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
@ActivityScoped
class InstagramLoginPresenter @Inject constructor() : InstagramLoginContract.Presenter {

    @Inject
    lateinit var profileManager: ProfileManager
    var view: InstagramLoginContract.View? = null
    private val logger = AnkoLogger(InstagramLoginPresenter::class.java.simpleName)

    override fun takeView(view: InstagramLoginContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun handleUri(data: Uri?): Boolean {
        if (data != null) {
            val frgParts: List<String>? = data.fragment?.split("=")
            if (ACCESS_TOKEN_FRAGMENT.equals(frgParts?.get(0)) && frgParts?.size == 2) {
                profileManager.saveInstagramAccessToken(frgParts[1])
                logger.debug("login succeeded")
                view?.finishLogin(true)
            } else {
                val map = data.query.split("&")
                        .associate {
                            val list = it.split("=")
                            Pair(list[0], list[1])
                        }
                when (map[ERROR_REASON_QUERY]) {
                    EROR_USER_DENIED -> view?.showUserDeniedMessage()
                    else -> view?.showLoginErrorMessage()
                }
                profileManager.closeSession(AccountType.INSTAGRAM) //invalidate token if exist
                logger.debug("login failed with exception: ${data.query}")
            }
            return true // handled, stop loading
        }
        return false
    }
}