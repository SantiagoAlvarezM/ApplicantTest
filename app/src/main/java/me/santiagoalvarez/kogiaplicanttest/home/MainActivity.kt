package me.santiagoalvarez.kogiaplicanttest.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.auth.AccountType
import me.santiagoalvarez.kogiaplicanttest.navigation.IntentNavigationEntry
import me.santiagoalvarez.kogiaplicanttest.navigation.Navigator
import me.santiagoalvarez.kogiaplicanttest.preferences.SettingsActivity
import me.santiagoalvarez.kogiaplicanttest.twitter.feed.TwitterLandingActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    @Inject lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_instagram -> {
                TODO("not implemented")
            }
            R.id.nav_twitter -> {
                IntentNavigationEntry.Builder(navigator, TwitterLandingActivity.createIntent(this))
                        .withLoginCheck(AccountType.TWITTER)
                        .navigate()
            }
            R.id.nav_settings -> {
                IntentNavigationEntry.Builder(navigator, SettingsActivity.createIntent(this))
                        .navigate()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
