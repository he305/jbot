package com.github.he305.jbot.common.exceptions;

public class StringValidationException extends JBotException{
    public StringValidationException() {
        super("Provided string empty or null");
    }
}
