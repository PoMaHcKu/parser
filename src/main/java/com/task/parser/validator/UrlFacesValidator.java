package com.task.parser.validator;

import org.apache.commons.validator.routines.UrlValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * UrlFacesValidator class
 * Реализует интерфейс javax.faces.validator.Validator.
 * Валидирует строку из xhtml страницы в режиме реального времени.
 */
@FacesValidator("validator")
public class UrlFacesValidator implements Validator<String> {

    /**
     * Проверяет является ли строка ссылкой, в случаен непрохождения проверки бросает исключение
     * с сообщением о непрохождении проверки.
     * @param context   FacesContext
     * @param component UIComponent
     * @param s         String
     * @throws ValidatorException некорректная ссылка
     */
    @Override
    public void validate(FacesContext context, UIComponent component, String s) throws ValidatorException {
        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(s)) {
            String detailMessage = "Некоректная ссылка. Верный формат ссылки http(s)://address.domain";
            throw new ValidatorException(new FacesMessage("", detailMessage));
        }
    }
}