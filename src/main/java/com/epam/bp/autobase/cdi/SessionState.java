package com.epam.bp.autobase.cdi;

import com.epam.bp.autobase.model.entity.Color;
import com.epam.bp.autobase.model.entity.User;
import org.jboss.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Named
@Stateful
@SessionScoped
public class SessionState implements Serializable {
    private static Map<String, String> langs;
    @Inject
    Event<Color> event;
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

    public Map<String, String> getLangs() {
        if (langs == null) {
            langs = new LinkedHashMap<>();
            langs.put("English", "en");
            langs.put("Русский", "ru");
        }
        return langs;
    }

    public void setLangs(Map<String, String> langs) {
        SessionState.langs = langs;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLanguage() {
        return getLocale().getLanguage();
    }

    public void setLanguage(String lang) {
        changeLocale(lang);
    }

    public void changeLocale(String lang_code) {
        this.locale = new Locale(lang_code);
        logger.info("Locale was changed to: " + lang_code);
        //fires entities which list depends of locale:
        event.fire(new Color());
    }
}
