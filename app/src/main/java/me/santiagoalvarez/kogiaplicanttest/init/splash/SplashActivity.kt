package me.santiagoalvarez.kogiaplicanttest.init.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.home.MainActivity
import me.santiagoalvarez.kogiaplicanttest.navigation.IntentNavigationEntry
import me.santiagoalvarez.kogiaplicanttest.navigation.NavigationEntry
import me.santiagoalvarez.kogiaplicanttest.navigation.Navigator

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : AppCompatActivity() {

    lateinit var navigator: Navigator
    private val mHideHandler = Handler()
    private val mHidePart2Runnable = Runnable {
        // Delayed navigation transaction

        val entry = IntentNavigationEntry.Builder(navigator, MainActivity.createIntent(this))
                .clearStack()
                .build()
        navigator.navigateTo(entry)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        navigator = Navigator(this, savedInstanceState)

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.postDelayed(mHidePart2Runnable, NAVIGATION_TRANSACTION_DELAY.toLong())
    }

    companion object {

        private val NAVIGATION_TRANSACTION_DELAY = 500
    }
}
