package me.santiagoalvarez.kogiaplicanttest.common.base

/**
 * @author santiagoalvarez
 */
interface BasePresenter<T> {

    fun takeView(view: T)

    fun dropView()
}