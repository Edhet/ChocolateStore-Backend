package com.edhet.store.exception;

import java.time.LocalDateTime;

public record ErrorDTO(
        LocalDateTime timestamp,
        Integer status,
        String msg
) {
}
