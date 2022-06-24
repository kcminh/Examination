package com.t3h.elibrary.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElibraryException extends Throwable {
    public ElibraryException(String message) {
        super(message);
    }
}
