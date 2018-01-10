package me.santiagoalvarez.kogiaplicanttest.twitter.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import me.santiagoalvarez.kogiaplicanttest.R
import javax.inject.Inject

class TwitterLoginActivity : DaggerAppCompatActivity() {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, TwitterLoginActivity::class.java)
        }
    }

    @Inject lateinit var twitterLoginPresenter: TwitterLoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.twitter_login)
        supportActionBar?.title = ""
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        supportFragmentManager.findFragmentById(R.id.twitterLoginFragment)?.onActivityResult(requestCode, resultCode, data)
    }

}
