package com.kdi.thoughtworks.galaxymerchant.rules;

import com.kdi.thoughtworks.galaxymerchant.GalaxyMerchantConstants;
import com.kdi.thoughtworks.galaxymerchant.RomanNumber;

public class RepeatitionRule implements RomanRule {
    private RomanNumber previousNumber = null;
    private int previousRepetition = 0;
    @Override
    public boolean accept(RomanNumber input) {
        if (previousNumber!=null && previousNumber.equals(input)) {
            previousRepetition++;
            if (previousRepetition>GalaxyMerchantConstants.MAX_REPETITION|| GalaxyMerchantConstants.ROMAN_NUMBER_NO_REPETITION_SET.contains(input)){
                return false;
            }
        } else {
            previousNumber = input;
            previousRepetition = 1;
        }
        return true;
    }
}
