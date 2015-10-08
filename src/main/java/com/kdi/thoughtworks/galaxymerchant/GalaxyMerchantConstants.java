package com.kdi.thoughtworks.galaxymerchant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GalaxyMerchantConstants {
    public static final int MAX_REPETITION = 3;
    public static final Set<RomanNumber> ROMAN_NUMBER_NO_REPETITION_SET = new HashSet<RomanNumber>(Arrays.asList(new RomanNumber[]{RomanNumber.D, RomanNumber.L, RomanNumber.V}));
}
