package me.santiagoalvarez.kogiaplicanttest.instagram.login

import android.net.Uri
import me.santiagoalvarez.kogiaplicanttest.common.base.BasePresenter
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseView

/**
 * @author santiagoalvarez
 */
interface InstagramLoginContract {

    interface View : BaseView<Presenter> {

        fun finishLogin(withResultOk: Boolean)

        fun showLoginErrorMessage()

        fun showUserDeniedMessage()

    }

    interface Presenter : BasePresenter<View> {

        fun handleUri(data: Uri?): Boolean

    }
}