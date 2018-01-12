package me.santiagoalvarez.kogiaplicanttest.init.splash

import android.os.Bundle
import android.os.Handler
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseActivity
import me.santiagoalvarez.kogiaplicanttest.home.MainActivity
import me.santiagoalvarez.kogiaplicanttest.navigation.IntentNavigationEntry

/**
 * @author santiagoalvarez
 */
class SplashActivity : BaseActivity() {

    private val handler = Handler()
    private val runnable = Runnable {
        IntentNavigationEntry.Builder(navigator, MainActivity.createIntent(this))
                .clearStack()
                .navigate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        handler.postDelayed(runnable, NAVIGATION_TRANSACTION_DELAY.toLong())
    }

    companion object {

        private val NAVIGATION_TRANSACTION_DELAY = 500
    }
}
