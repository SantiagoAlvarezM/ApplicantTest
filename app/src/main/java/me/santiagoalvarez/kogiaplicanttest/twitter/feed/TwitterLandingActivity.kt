package me.santiagoalvarez.kogiaplicanttest.twitter.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.Lazy
import kotlinx.android.synthetic.main.activity_twitter_landing.*
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.auth.AccountType
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseActivity
import me.santiagoalvarez.kogiaplicanttest.navigation.AuthenticationRequired
import me.santiagoalvarez.kogiaplicanttest.navigation.FragmentNavigationEntry
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
@AuthenticationRequired(accounts = [AccountType.TWITTER])
class TwitterLandingActivity : BaseActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, TwitterLandingActivity::class.java)
        }
    }

    @Inject lateinit var twitterLandingFragmentProvider: Lazy<TwitterLandingFragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_landing)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val twitterLandingFragment = supportFragmentManager.findFragmentById(R.id.content) as TwitterLandingFragment? ?:
                twitterLandingFragmentProvider.get()

        FragmentNavigationEntry.Builder(navigator, twitterLandingFragment)
                .noPush()
                .navigate()
    }

}
