package com.cmc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Locale;

/**
 * Message translator component
 */
@Component
public class MessageTranslator {
    private static ResourceBundleMessageSource messageSource;

    @Autowired
    MessageTranslator(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String toLocale(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }

    /**
     * Format message with translator
     *
     * @param fmt  Message format
     * @param args Array of arguments
     * @return Translated message
     */
    public String format(@NotNull String fmt, Object... args) {
        return String.format(toLocale(fmt), args);
    }
}
