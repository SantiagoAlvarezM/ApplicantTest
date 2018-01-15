package me.santiagoalvarez.kogiaplicanttest.preferences

import me.santiagoalvarez.kogiaplicanttest.auth.AccountType
import me.santiagoalvarez.kogiaplicanttest.common.base.BasePresenter
import me.santiagoalvarez.kogiaplicanttest.common.base.BaseView

/**
 * @author santiagoalvarez
 */
interface SettingsContract {

    interface View : BaseView<Presenter> {

        fun updatePreference(accountType: AccountType)

        fun showLogoutAlert(accountType: AccountType)

        fun requestNavigateToLogin(accountType: AccountType)
    }

    interface Presenter : BasePresenter<View> {

        fun logoutAccount(accountType: AccountType)

        fun handlePreferenceAction(accountType: AccountType)

        fun isSessionActive(accountType: AccountType): Boolean
    }
}