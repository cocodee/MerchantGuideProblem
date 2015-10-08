package com.kdi.thoughtworks.galaxymerchant.Exceptions;

public class RuleViolationException extends Exception {
    public RuleViolationException(String ruleName) {
        super("Rule "+ruleName+" is violated.");
    }
}
