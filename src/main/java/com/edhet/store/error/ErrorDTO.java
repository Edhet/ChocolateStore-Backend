package com.edhet.store.error;

import java.time.LocalDateTime;

public record ErrorDTO(
        LocalDateTime timestamp,
        Integer status,
        String msg
) {
}
