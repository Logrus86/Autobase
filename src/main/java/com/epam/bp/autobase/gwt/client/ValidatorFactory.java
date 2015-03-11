package com.epam.bp.autobase.gwt.client;

import com.epam.bp.autobase.model.entity.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import org.gwtbootstrap3.client.ui.form.validator.Validator;

import javax.validation.ParameterNameProvider;

public class ValidatorFactory extends AbstractGwtValidatorFactory {

    @GwtValidation(User.class)
    public interface UserGwtValidator extends Validator {

    }

    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(UserGwtValidator.class);
    }

    @Override
    public ParameterNameProvider getParameterNameProvider() {
        return null;
    }

    @Override
    public void close() {

    }
}
