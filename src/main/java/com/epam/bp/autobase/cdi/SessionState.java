package com.epam.bp.autobase.cdi;

import com.epam.bp.autobase.model.entity.Color;
import com.epam.bp.autobase.model.entity.User;
import org.jboss.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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
    @Inject
    Event<Color> event;
    private User sessionUser;
    private Locale locale;
    @Inject
    Logger logger;
    private static Map<String, String> langs;

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
        if (locale == null) {
            try {
                locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            } catch (NullPointerException e) {
                logger.error("FacesContext is null. It's ok when we are using jsp :)");
            }
        }
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
        try {
            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        } catch (NullPointerException e) {
            logger.error("FacesContext is null. It's ok when we are using jsp :)");
        }
        logger.info("Locale was changed to: " + lang_code);
        //fires entities which list depends of locale:
        event.fire(new Color());
    }

    //value change event listener
    public void localeChanged(ValueChangeEvent e) {
        changeLocale(e.getNewValue().toString());
    }
}
