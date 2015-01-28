package com.epam.bp.autobase.cdi;

import com.epam.bp.autobase.model.entity.Color;
import com.epam.bp.autobase.model.entity.User;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@Stateful
@SessionScoped
public class SessionState implements Serializable {
    @Inject
    Event<Color> event;
    private User sessionUser;
    private Locale locale;

    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setLocaleFromLangCode(String lang_code) {
        this.locale = new Locale(lang_code);
        //fires entities which lists depends of locale:
        event.fire(new Color());
    }
}
