package com.task.parser;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Позволяет приложению работать в окружении контейнера сервлентов.
 */
public class WebInitializer extends SpringBootServletInitializer {

    /**
     * Регистрирует класс <b>ParserApplication</b> как класс конфигурации приложения
     * @param builder SpringApplicationBuilder
     * @return SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ParserApplication.class);
    }
}