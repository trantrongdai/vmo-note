package com.vmo.note.util;

import java.time.LocalDateTime;

/**
 * Datetime utils
 */
public final class DateTimeUtils {
    private DateTimeUtils() {
    }

    /**
     * Get instant current time UTC
     *
     * @return
     */
    public static LocalDateTime getCurrentTimeUTC() {
        return LocalDateTime.now();
    }
}
