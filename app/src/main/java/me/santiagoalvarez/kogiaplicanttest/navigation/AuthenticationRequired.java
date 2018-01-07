package me.santiagoalvarez.kogiaplicanttest.navigation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotated clients with this annotation will be authenticated checked by {@link Navigator}
 * before executing the action.
 *
 * @author santiagoalvarez
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticationRequired {
}
