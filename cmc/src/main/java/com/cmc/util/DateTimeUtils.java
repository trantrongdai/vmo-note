package com.cmc.util;

import java.time.LocalDateTime;

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
