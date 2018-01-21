package me.santiagoalvarez.kogiaplicanttest.instagram.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import dagger.Lazy
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseLoginActivity
import me.santiagoalvarez.kogiaplicanttest.navigation.FragmentNavigationEntry
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
class InstagramLoginActivity : BaseLoginActivity() {

    @Inject
    lateinit var instagramLoginFragmentProvider: Lazy<InstagramLoginFragment>

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, InstagramLoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_activity_login)
        val frag = supportFragmentManager.findFragmentById(R.id.content)
                ?: instagramLoginFragmentProvider.get()
        FragmentNavigationEntry.Builder(navigator, frag)
                .clearHistory()
                .navigate()
    }
}
