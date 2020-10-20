package com.task.parser.validator;

import org.apache.commons.validator.routines.UrlValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validator")
public class JsfValidator implements Validator<String> {

    @Override
    public void validate(FacesContext context, UIComponent component, String s) throws ValidatorException {
        UrlValidator urlValidator = new UrlValidator();
        s = checkAndGetHttpProtocol(s.trim());
        if (!urlValidator.isValid(s)) {
            throw new ValidatorException(new FacesMessage("", "Некоректная ссылка"));
        }
    }

    public String checkAndGetHttpProtocol(String url) {
        if (url.startsWith("https://") || url.startsWith("http://")) {
            return url;
        }
        return "http://" + url;
    }
}