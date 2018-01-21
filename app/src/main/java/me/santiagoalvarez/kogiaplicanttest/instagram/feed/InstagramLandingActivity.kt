package me.santiagoalvarez.kogiaplicanttest.instagram.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_instagram_landing.*
import kotlinx.android.synthetic.main.app_bar_main.*
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.R.id.*
import me.santiagoalvarez.kogiaplicanttest.auth.AccountType
import me.santiagoalvarez.kogiaplicanttest.navigation.AuthenticationRequired

/**
 * @author santiagoalvarez
 */
@AuthenticationRequired(accounts = [AccountType.INSTAGRAM])
class InstagramLandingActivity : DaggerAppCompatActivity(),
        BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, InstagramLandingActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instagram_landing)
        setSupportActionBar(toolbar)

        bNInstagramlanding.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            bottom_nav_favorites -> {

            }
            bottom_nav_uploads -> {

            }
            bottom_nav_profile -> {

            }
        }
        return true
    }
}
