package com.thedancercodes.darkskyclient.events;

/**
 * Created by @thedancercodes on 04/11/2017.
 */

public class ErrorEvent {
    private final String errorMessage;

    public ErrorEvent(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
