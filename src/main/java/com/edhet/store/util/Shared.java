package com.edhet.store.util;

import com.edhet.store.error.exceptions.BadRequestException;

public final class Shared {
    private Shared() {
    }

    public static Long stringToLongParsing(String id) throws BadRequestException {
        long expectedId;
        try {
            expectedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Error parsing string parameter to number");
        }
        return expectedId;
    }
}
