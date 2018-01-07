package me.santiagoalvarez.kogiaplicanttest.navigation;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkNotNull;

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
    private final Boolean loginRequired;

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
    }

    protected NavigationEntry(Parcel parcelIn, T target) {
        home = (boolean) parcelIn.readValue(null);
        title = (String) parcelIn.readValue(null);
        animations = (CustomAnimations) parcelIn.readValue(null);
        loginRequired = (Boolean) parcelIn.readValue(null);
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
    public boolean isLoginRequired() {
        Boolean loginRequiredLocal = this.loginRequired;
        if (loginRequiredLocal == null) {
            try {
                Class<?> internalTargetClass = getInternalTargetClass();
                if (internalTargetClass != null) {
                    loginRequiredLocal = internalTargetClass.getAnnotation(AuthenticationRequired.class) != null;
                } else {
                    loginRequiredLocal = false;
                }
            } catch (ClassNotFoundException e) {
                loginRequiredLocal = false;
            }
        }
        return loginRequiredLocal;
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
    }

    /**
     * @return the internal class of the target.
     * @throws ClassNotFoundException
     */
    protected abstract Class<?> getInternalTargetClass() throws ClassNotFoundException;

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
        private Boolean loginRequired;

        Builder(Navigator navigator, P target) {
            this.target = checkNotNull(target);
            this.navigator = navigator;
        }

        protected abstract T self();

        /**
         * Builds and executes the navigation entry.
         */
        public void navigate() {
            checkNotNull(navigator,
                    "The navigator instance is null when trying to execute a navigation entry.");
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
        public T withLoginCheck() {
            this.loginRequired = true;
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
         * Builds the {@link NavigationEntry}
         *
         * @return the navigation event.
         */
        protected abstract NavigationEntry<P> build();

    }
}
