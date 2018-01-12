package me.santiagoalvarez.kogiaplicanttest.common.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import me.santiagoalvarez.kogiaplicanttest.navigation.Navigator
import javax.inject.Inject

/**
 * @author santiagoalvarez.
 */
abstract class BaseActivity : AppCompatActivity() {

    companion object {
        public val REQUEST_SIGNIN = 999
    }

    protected lateinit var navigator: Navigator
    @Inject protected lateinit var navigationListener: Navigator.NavigationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = Navigator(this, savedInstanceState, android.R.id.content, navigationListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_SIGNIN) {
            if (resultCode == Activity.RESULT_OK) {
                navigator.executePendingNavigation(REQUEST_SIGNIN.toString())
            } else {
                navigator.removePendingNavigation(REQUEST_SIGNIN.toString())
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}