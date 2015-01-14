package com.epam.bp.autobase.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;

public class Resources {

    @SuppressWarnings("unused")
    @Produces
    @PersistenceContext
    private EntityManager em;

    @Produces
    public Logger log(InjectionPoint injectionPoint) {
        return getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

}
