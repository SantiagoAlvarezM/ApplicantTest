package me.santiagoalvarez.kogiaplicanttest.common.base

/**
 * @author santiagoalvarez
 */
interface BasePresenter<in T> {

    fun takeView(view: T)

    fun dropView()
}