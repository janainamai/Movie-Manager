package br.com.movie.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Customized exception for field validation.
 */
@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private final String fields;
    private final String fieldsMessage;
}