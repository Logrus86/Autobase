package com.epam.bp.autobase.util;

public class AttributeSetterException extends RuntimeException {

    public AttributeSetterException() {
    }

    public AttributeSetterException(String message) {
        super(message);
    }

    public AttributeSetterException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttributeSetterException(Throwable cause) {
        super(cause);
    }
}
