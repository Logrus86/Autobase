package com.epam.bp.autobase.util;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

import javax.validation.MessageInterpolator;
import java.util.Locale;

public class MyMessageInterpolator implements MessageInterpolator {
    private Locale locale;

    public MyMessageInterpolator() {
    }

    public Locale getLocale() {
        return locale;
    }

    public MyMessageInterpolator setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    @Override
    public String interpolate(String messageTemplate, Context context) {
        ResourceBundleMessageInterpolator rbmi = new ResourceBundleMessageInterpolator();
        return rbmi.interpolate(messageTemplate, context, locale);
    }

    @Override
    public String interpolate(String messageTemplate, Context context, Locale locale0) {
        return interpolate(messageTemplate, context);
    }
}
