package com.task.parser.validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import static org.junit.jupiter.api.Assertions.*;

class UrlFacesValidatorTest {

    @Mock
    static FacesContext context;
    @Mock
    static UIComponent component;
    static UrlFacesValidator validator;

    @BeforeAll
    static void init() {
        validator = new UrlFacesValidator();
        context = Mockito.mock(FacesContext.class);
        component = Mockito.mock(UIComponent.class);
    }


    @Test
    void validate() {
        String first = "hello";
        assertThrows(ValidatorException.class, () -> validator.validate(context, component, first));
        String second = "http://hello";
        assertThrows(ValidatorException.class, () -> validator.validate(context, component, second));
        String third = "https://nnnn.nnnn";
        assertThrows(ValidatorException.class, () -> validator.validate(context, component, third));
        String fourth = "https://vk.com";
        assertDoesNotThrow(() -> validator.validate(context, component, fourth));
    }
}