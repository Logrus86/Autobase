package com.epam.bp.autobase.dao.hibernate;

import javax.inject.Qualifier;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Qualifier
@Target({TYPE, METHOD, FIELD})
@Retention(RUNTIME)
@Inherited
public @interface Hibernate {
}
