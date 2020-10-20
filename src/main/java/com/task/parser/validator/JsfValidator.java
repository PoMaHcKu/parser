package com.task.parser.validator;

import org.apache.commons.validator.routines.UrlValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * JfsValidator
 * Реализует интерфейс javax.faces.validator.Validator.
 * Валидирует строку из xhtml страницы в режиме реального времени.
 */
@FacesValidator("validator")
public class JsfValidator implements Validator<String> {

    /**
     * @param context FacesContext
     * @param component UIComponent
     * @param s String
     * @throws ValidatorException некорректная ссылка
     * Проверяет является ли строка ссылкой, в случаен непрохождения проверки бросает исключение
     * с сообщением о непрохождении проверки.
     */
    @Override
    public void validate(FacesContext context, UIComponent component, String s) throws ValidatorException {
        UrlValidator urlValidator = new UrlValidator();
        s = checkAndGetHttpProtocol(s.trim());
        if (!urlValidator.isValid(s)) {
            throw new ValidatorException(new FacesMessage("", "Некоректная ссылка"));
        }
    }

    /**
     * @param url String
     * @return protocol + url String
     * Проверяет содержит ли ссылка протокол, если нет - добавляет http:// перед ссылкой
     */
    public String checkAndGetHttpProtocol(String url) {
        if (url.startsWith("https://") || url.startsWith("http://")) {
            return url;
        }
        return "http://" + url;
    }
}