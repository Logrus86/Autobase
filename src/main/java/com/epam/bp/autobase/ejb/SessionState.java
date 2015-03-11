package com.epam.bp.autobase.ejb;

import com.epam.bp.autobase.model.entity.User;
import org.jboss.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@Stateful
@SessionScoped
public class SessionState implements Serializable {
    @Inject
    Logger logger;
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
        if (!locale.equals(this.locale)) {
            this.locale = locale;
            logger.info("Locale was changed to: " + locale.getLanguage());
        }
    }

    public void setLocale(String lang_code) {
        if (!this.locale.getLanguage().equals(lang_code)) {
            this.locale = new Locale(lang_code);
            logger.info("Locale was changed to: " + lang_code);
        }
    }

}
