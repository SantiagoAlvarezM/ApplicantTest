package me.santiagoalvarez.kogiaplicanttest.twitter.login

import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterSession
import me.santiagoalvarez.kogiaplicanttest.common.base.BasePresenter
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseView

/**
 * @author santiagoalvarez
 */
interface TwitterLoginContract {

    interface View : BaseView<Presenter> {

        fun onLoginFail()

        fun onLoginSuccess()

        fun setCallback(callback: Callback<TwitterSession>)

    }

    interface Presenter : BasePresenter<View>
}