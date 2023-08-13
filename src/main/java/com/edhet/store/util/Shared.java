package com.edhet.store.util;

import com.edhet.store.error.exceptions.BadRequestException;

public final class Shared {
    private Shared() {
    }

    public static long stringToLongParsing(String id) throws BadRequestException {
        long expectedId;
        try {
            expectedId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Erro convertendo string em número");
        }
        return expectedId;
    }
}
