package com.kdi.thoughtworks.galaxymerchant.Exceptions;

public class ParseException extends Exception{
    public ParseException(String line,String errorMessage) {
        super("Parse error:[["+line+"]]"+errorMessage);
    }
}
