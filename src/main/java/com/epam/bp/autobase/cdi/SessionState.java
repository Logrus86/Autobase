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
    private static Map<String, Locale> locales;

    static {
        locales = new LinkedHashMap<String, Locale>();
        locales.put("en", Locale.ENGLISH);
        locales.put("ru", new Locale("ru"));
    }
    public User getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(User sessionUser) {
        this.sessionUser = sessionUser;
    }

    public Map<String, Locale> getLangs() {
        return locales;
    }

    public void setLangs(Map<String, Locale> langs) {
        SessionState.locales = langs;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String lang) {
        changeLocale(lang);
    }

    public void changeLocale(String lang_code) {
        this.locale = new Locale(lang_code);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        logger.info("Locale was changed to: " + lang_code);

        //fires entities which list depends of locale:
        event.fire(new Color());
    }

    //value change event listener
    public void localeChanged(ValueChangeEvent e) {
        changeLocale(e.getNewValue().toString());
    }
}
