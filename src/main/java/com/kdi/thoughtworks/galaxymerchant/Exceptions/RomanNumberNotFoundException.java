package com.kdi.thoughtworks.galaxymerchant.Exceptions;

public class RomanNumberNotFoundException extends Exception{
    public RomanNumberNotFoundException(String name) {
        super("Roman number with name "+name +" not found.");
    }
}
