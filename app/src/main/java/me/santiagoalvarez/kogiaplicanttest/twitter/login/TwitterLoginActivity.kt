package me.santiagoalvarez.kogiaplicanttest.twitter.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.Lazy
import dagger.android.support.DaggerAppCompatActivity
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.navigation.FragmentNavigationEntry
import me.santiagoalvarez.kogiaplicanttest.navigation.Navigator
import javax.inject.Inject


class TwitterLoginActivity : DaggerAppCompatActivity(), TwitterLoginFragment.OnTwitterLoginListener {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, TwitterLoginActivity::class.java)
        }
    }

    @Inject lateinit var twitterLoginFragmentProvider: Lazy<TwitterLoginFragment>
    lateinit var navigator: Navigator
    private lateinit var twitterLoginFrg: TwitterLoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twitter_login)
        navigator = Navigator(this, savedInstanceState)

        twitterLoginFrg = supportFragmentManager.findFragmentById(R.id.content) as TwitterLoginFragment? ?:
                twitterLoginFragmentProvider.get()

        FragmentNavigationEntry.Builder(navigator, twitterLoginFrg)
                .withContainerId(R.id.content)
                .noPush()
                .navigate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        twitterLoginFrg.onActivityResult(requestCode, resultCode, data)
    }

    override fun onLoginSuccess() {
        //TODO
    }
}
