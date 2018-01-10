package me.santiagoalvarez.kogiaplicanttest.common.base

/**
 * @author santiagoalvarez
 */
interface BasePresenter<T> {

    fun bindView(view: T)

    fun dropView()
}