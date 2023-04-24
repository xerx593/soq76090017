package com.stackoverflow.soq76090017;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({ "stackTrace", "cause", "suppressed", "localizedMessage" })
public class ApplicationException extends RuntimeException {

    private final int resultCode;
    private final String message;
    private final int httpStatusCode;
    private final ZonedDateTime thrownAt;

    private static final String ZONE_ID = "Europe/Istanbul";

    public ApplicationException(int resultCode, String message, HttpStatus httpStatus) {
        super();
        this.resultCode = resultCode;
        this.message = message;
        this.httpStatusCode = httpStatus.value();
        this.thrownAt = ZonedDateTime.now(ZoneId.of(ZONE_ID));
    }

}