package me.santiagoalvarez.kogiaplicanttest.settings

import me.santiagoalvarez.kogiaplicanttest.auth.AccountType
import me.santiagoalvarez.kogiaplicanttest.auth.ProfileManager
import me.santiagoalvarez.kogiaplicanttest.di.ActivityScoped
import javax.inject.Inject

/**
 * @author santiagoalvarez
 */
@ActivityScoped
class SettingsPresenter @Inject constructor() : SettingsContract.Presenter {

    @Inject lateinit var profileManager: ProfileManager
    var view: SettingsContract.View? = null

    override fun takeView(view: SettingsContract.View) {
        this.view = view
    }

    override fun dropView() {
        view = null
    }

    override fun logoutAccount(accountType: AccountType) {
        profileManager.closeSession(accountType)
        view?.updatePreference(accountType)
    }

    override fun handlePreferenceAction(accountType: AccountType) {
        if (profileManager.isAuthenticated(accountType)) {
            view?.showLogoutAlert(accountType)
        } else {
            view?.requestNavigateToLogin(accountType)
        }
    }

    override fun isSessionActive(accountType: AccountType): Boolean =
            profileManager.isAuthenticated(accountType)

}