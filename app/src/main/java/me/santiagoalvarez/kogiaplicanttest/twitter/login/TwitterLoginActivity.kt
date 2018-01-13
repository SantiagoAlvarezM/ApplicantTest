package me.santiagoalvarez.kogiaplicanttest.twitter.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.Lazy
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseActivity
import me.santiagoalvarez.kogiaplicanttest.navigation.FragmentNavigationEntry
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
class TwitterLoginActivity : BaseActivity(), TwitterLoginFragment.OnLoginListener {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, TwitterLoginActivity::class.java)
        }
    }

    @Inject lateinit var twitterLoginFragmentProvider: Lazy<TwitterLoginFragment>
    private lateinit var twitterLoginFrg: TwitterLoginFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_twitter_login)

        twitterLoginFrg =
                supportFragmentManager.findFragmentById(R.id.content) as TwitterLoginFragment? ?:
                        twitterLoginFragmentProvider.get()

        FragmentNavigationEntry.Builder(navigator, twitterLoginFrg)
                .noPush()
                .navigate()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        twitterLoginFrg.onActivityResult(requestCode, resultCode, data)
    }

    override fun finishActivity(withResultOk: Boolean) {
        if (withResultOk) {
            setResult(Activity.RESULT_OK)
        }
        finish()
    }
}
