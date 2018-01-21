package me.santiagoalvarez.kogiaplicanttest.common.base

import android.content.Context
import com.google.common.base.Preconditions
import dagger.android.support.DaggerFragment
import me.santiagoalvarez.kogiaplicanttest.auth.LoginListener

/**
 * @author santiagoalvarez.
 */
abstract class BaseLoginFragment : DaggerFragment() {

    lateinit var loginListener: LoginListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Preconditions.checkArgument(context is LoginListener, "Activity must implement LoginListener")
        loginListener = context as LoginListener
    }
}