package me.santiagoalvarez.kogiaplicanttest.home

import dagger.Module
import dagger.Provides
import me.santiagoalvarez.kogiaplicanttest.navigation.Navigator

/**
 * @author santiagoalvarez
 */
@Module
class MainActivityModule {

    @Provides
    fun providesNavigator(activity: MainActivity): Navigator = Navigator(activity, null)
}
