package me.santiagoalvarez.kogiaplicanttest.settings

import android.annotation.TargetApi
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import android.preference.PreferenceScreen
import android.view.MenuItem
import android.view.View
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import me.santiagoalvarez.kogiaplicanttest.R
import me.santiagoalvarez.kogiaplicanttest.auth.AccountType
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import org.jetbrains.anko.alert
import org.jetbrains.anko.appcompat.v7.Appcompat
import javax.inject.Inject

/**
 * This fragment shows general preferences only. It is used when the
 * activity is showing a two-pane settings UI.
 * @author santiagoalvarez
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@ActivityScoped
class GeneralSettingsFragment @Inject constructor() : PreferenceFragment(),
        HasFragmentInjector, SettingsContract.View {

    //region Dagger
    override fun fragmentInjector(): AndroidInjector<android.app.Fragment> = childFragmentInjector

    @Inject
    lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>
    //endregion

    interface PreferenceInteractionListener {
        fun onLoginRequest(accountType: AccountType)
    }

    @Inject
    lateinit var presenter: SettingsContract.Presenter
    private lateinit var interactionListener: PreferenceInteractionListener

    private lateinit var twitterPreference: Preference
    private lateinit var instagramPreference: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.pref_session)
        setHasOptionsMenu(true)
        twitterPreference = findPreference(getString(R.string.pref_key_account_twitter))
        instagramPreference = findPreference(getString(R.string.pref_key_account_instagram))
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updatePreference(AccountType.TWITTER)
        updatePreference(AccountType.INSTAGRAM)
    }

    override fun onAttach(context: Context?) {
        AndroidInjection.inject(this)
        super.onAttach(context)
        check(context is PreferenceInteractionListener) {
            "Activity must implement PreferenceInteractionListener"
        }
        interactionListener = context as PreferenceInteractionListener
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            startActivity(Intent(activity, SettingsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPreferenceTreeClick(preferenceScreen: PreferenceScreen?, preference: Preference?): Boolean {
        presenter.handlePreferenceAction(
                if (preference?.key == twitterPreference.key) AccountType.TWITTER else AccountType.INSTAGRAM)
        return super.onPreferenceTreeClick(preferenceScreen, preference)
    }

    override fun showLogoutAlert(accountType: AccountType) {
        val formatArgs = if (accountType == AccountType.TWITTER)
            getString(R.string.navigation_drawer_twitter)
        else
            getString(R.string.navigation_drawer_instagram)

        alert(Appcompat, getString(R.string.pref_dialog_message, formatArgs),
                getString(R.string.pref_dialog_title)) {
            positiveButton(R.string.dialog_positive_opt, onClicked = {
                when (accountType) {
                    AccountType.TWITTER -> {
                        presenter.logoutAccount(AccountType.TWITTER)
                    }
                    AccountType.INSTAGRAM -> {
                        presenter.logoutAccount(AccountType.INSTAGRAM)
                    }
                }
            })
            negativeButton(R.string.dialog_negative_opt, onClicked = {})
        }.show()
    }

    override fun requestNavigateToLogin(accountType: AccountType) {
        interactionListener.onLoginRequest(accountType)
    }

    override fun updatePreference(accountType: AccountType) {
        when (accountType) {
            AccountType.TWITTER -> {
                val sessionActive = presenter.isSessionActive(AccountType.TWITTER)
                twitterPreference.title = if (sessionActive)
                    getString(R.string.pref_title_session_logout_twitter)
                else
                    getString(R.string.pref_title_session_login_twitter)

                twitterPreference.summary = if (sessionActive)
                    getString(R.string.pref_session_summary_logout_twitter)
                else
                    getString(R.string.pref_session_summary_login_twitter)
            }

            AccountType.INSTAGRAM -> {
                val sessionActive = presenter.isSessionActive(AccountType.INSTAGRAM)
                instagramPreference.title = if (sessionActive)
                    getString(R.string.pref_title_session_logout_instagram)
                else
                    getString(R.string.pref_title_session_login_instagram)

                instagramPreference.summary = if (sessionActive)
                    getString(R.string.pref_session_summary_logout_instagram)
                else
                    getString(R.string.pref_session_summary_login_instagram)
            }
        }
    }
}