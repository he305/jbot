package com.github.he305.jbot.telegram.application.exceptions;

import com.github.he305.jbot.common.exceptions.JBotException;

public class UnexpectedStateException extends JBotException {
    public UnexpectedStateException(String message) {
        super(message);
    }
}
