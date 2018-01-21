package me.santiagoalvarez.kogiaplicanttest.navigation;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.io.Serializable;

import me.santiagoalvarez.kogiaplicanttest.auth.AccountType;

/**
 * Defines a navigation transaction
 *
 * @author santiagoalvarez
 */

public abstract class NavigationEntry<T> implements Parcelable {

    /**
     * The target object can be either a Fragment or an Intent.
     */
    private final T target;
    private final @Nullable
    CustomAnimations animations;
    private final boolean home;
    private final String title;
    private final CustomLogin loginRequired;
    private final boolean sticky;

    /**
     * private full options constructor.  Exposes all of the available information within the event.
     *
     * @param builder the target navigation class, can be a fragment or an intent.
     */
    protected NavigationEntry(Builder<?, T> builder) {
        this.target = builder.target;
        this.animations = builder.animations;
        this.title = builder.title;
        this.home = builder.home;
        this.loginRequired = builder.loginRequired;
        this.sticky = builder.sticky;
    }

    protected NavigationEntry(Parcel parcelIn, T target) {
        home = (boolean) parcelIn.readValue(null);
        title = (String) parcelIn.readValue(null);
        animations = (CustomAnimations) parcelIn.readValue(null);
        loginRequired = (CustomLogin) parcelIn.readValue(null);
        sticky = (boolean) parcelIn.readValue(null);
        this.target = target;
    }

    public boolean isHome() {
        return home;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Gets the target.
     *
     * @return the target
     */
    public T getTarget() {
        return target;
    }

    public CustomAnimations getAnimations() {
        return animations;
    }

    /**
     * Determines if the user must be authenticated before doing the navigator action.
     * If no value was set then it will check if the Target class contains the {@link AuthenticationRequired} annotation.
     *
     * @return if login check is enabled.
     */
    public boolean isLoginRequired(AccountType accountType) {
        CustomLogin loginRequiredLocal = this.loginRequired;
        if (loginRequiredLocal == null) {
            try {
                Class<?> internalTargetClass = getInternalTargetClass();
                AccountType[] accounts = internalTargetClass.getAnnotation(AuthenticationRequired.class).accounts();
                for (AccountType account : accounts) {
                    if (account == accountType) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
                loginRequiredLocal = new CustomLogin(accountType, false);
            }
        }
        return loginRequiredLocal.loginRequired;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(home);
        parcel.writeValue(title);
        parcel.writeValue(animations);
        parcel.writeValue(loginRequired);
        parcel.writeValue(sticky);
    }

    /**
     * @return the internal class of the target.
     * @throws ClassNotFoundException
     */
    protected abstract Class<?> getInternalTargetClass() throws ClassNotFoundException;

    public boolean isSticky() {
        return sticky;
    }

    /**
     * custom login type holder
     */
    public static class CustomLogin implements Serializable {

        private static final long serialVersionUID = -7366758632864960252L;

        final AccountType accountType;
        final boolean loginRequired;

        public CustomLogin(AccountType accountType, boolean loginRequired) {
            this.accountType = accountType;
            this.loginRequired = loginRequired;
        }
    }

    /**
     * The custom transition animation configuration class.
     */
    public static class CustomAnimations implements Serializable {

        private static final long serialVersionUID = 1L;

        public final int enter;
        public final int exit;
        public final int popEnter;
        public final int popExit;

        /**
         * The {@link CustomAnimations} constructor.
         *
         * @param enter    the enter animation.
         * @param exit     the exit animation.
         * @param popEnter the pop backstack enter animation (only used for fragment transactions).
         * @param popExit  the pop backstack exit animation (only used for fragment transactions).
         */
        public CustomAnimations(int enter, int exit, int popEnter, int popExit) {
            this.enter = enter;
            this.exit = exit;
            this.popEnter = popEnter;
            this.popExit = popExit;
        }

        /**
         * The {@link CustomAnimations} constructor.
         *
         * @param enter A resource ID of the animation resource to use for
         *              the incoming view.  Use 0 for no animation.
         * @param exit  A resource ID of the animation resource to use for
         *              the outgoing view.  Use 0 for no animation.
         */
        public CustomAnimations(int enter, int exit) {
            this(enter, exit, 0, 0);
        }
    }

    /**
     * Builder to create the Navigation entry.
     */
    public static abstract class Builder<T extends Builder<T, P>, P> {

        protected final P target;
        private CustomAnimations animations;
        private String title = null;
        private boolean home = false;
        protected Navigator navigator;
        private CustomLogin loginRequired;
        private boolean sticky = false;

        Builder(Navigator navigator, P target) {
            if (target == null) {
                throw new NullPointerException("target can't be null");
            }
            this.target = target;
            this.navigator = navigator;
        }

        protected abstract T self();

        /**
         * Builds and executes the navigation entry.
         */
        public void navigate() {
            if (navigator == null) {
                throw new NullPointerException("The navigator instance is null when trying to execute a navigation entry.");
            }
            navigator.navigateTo(build());
        }

        public T withHome(boolean isHome) {
            this.home = isHome;
            return self();
        }

        public T withTitle(String title) {
            this.title = title;
            return self();
        }

        /**
         * Sets if the user must be authenticated before doing the navigator action.
         *
         * @return if login check is enabled.
         */
        public T withLoginCheck(AccountType accountType) {
            this.loginRequired = new CustomLogin(accountType, true);
            return self();
        }

        /**
         * Sets the custom transition animation configuration class.
         * By default is slide left.
         */
        public T withAnimations(CustomAnimations animations) {
            this.animations = animations;
            return self();
        }

        /**
         * Sets the NavigationEntry as sticky.
         * This flag avoid the Navigator removing the NavigationEntry
         * when executed with {@link Navigator#executePendingNavigation(String)},
         * unless it is forced to be removed or kept during the execution.
         * If not set, this flag defaults to false.
         *
         * @return the builder instance
         */
        public T sticky() {
            this.sticky = true;
            return self();
        }

        /**
         * Builds the {@link NavigationEntry}
         *
         * @return the navigation event.
         */
        protected abstract NavigationEntry<P> build();

    }
}
