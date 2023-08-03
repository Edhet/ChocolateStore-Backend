package com.edhet.store.util;

import com.edhet.store.exception.errors.BadRequestException;

public final class Shared {
    private Shared() {
    }

    public static Long getIdFromStringRequest(String id) throws BadRequestException {
        long expectedId;
        try {
            expectedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Error parsing id parameter to number");
        }
        return expectedId;
    }
}
