package me.santiagoalvarez.kogiaplicanttest.common.base

import android.app.Activity
import me.santiagoalvarez.kogiaplicanttest.auth.LoginListener

/**
 * @author santiagoalvarez.
 */
abstract class BaseLoginActivity : BaseActivity(), LoginListener {

    override fun onLoginFinish(resultOk: Boolean) {
        if (resultOk) setResult(Activity.RESULT_OK)
        finish()
    }
}