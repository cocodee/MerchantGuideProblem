package com.kdi.thoughtworks.galaxymerchant.rules;

import com.kdi.thoughtworks.galaxymerchant.RomanNumber;

import java.util.*;

public class SubtractRule implements RomanRule {
    private RomanNumber previousNumber = null;
    private boolean subtracted = false;
    public static final Map<RomanNumber,Set<RomanNumber>> SUBTRACTABLE =  Collections
            .unmodifiableMap(new HashMap<RomanNumber, Set<RomanNumber>>() {
                {
                    this.put(RomanNumber.I, new HashSet<RomanNumber>(Arrays.asList(new RomanNumber[]{RomanNumber.V,RomanNumber.X})));
                    this.put(RomanNumber.V, Collections.emptySet());
                    this.put(RomanNumber.X, new HashSet<RomanNumber>(Arrays.asList(new RomanNumber[]{RomanNumber.L,RomanNumber.C})));
                    this.put(RomanNumber.L, Collections.emptySet());
                    this.put(RomanNumber.C, new HashSet<RomanNumber>(Arrays.asList(new RomanNumber[]{RomanNumber.D,RomanNumber.M})));
                    this.put(RomanNumber.D, Collections.emptySet());
                    this.put(RomanNumber.M, Collections.emptySet());
                }
            });
    ;

    @Override
    public boolean accept(RomanNumber input) {
        if (previousNumber == null) {
            previousNumber = input;
            subtracted = false;
            return true;
        }
        if (!subtracted && previousNumber.getValue()>=input.getValue()){
            previousNumber = input;
            subtracted = false;
            return true;
        }
        if (!subtracted && previousNumber.getValue()<input.getValue()){
            if (SUBTRACTABLE.get(previousNumber).contains(input)) {
                previousNumber = input;
                subtracted = true;
                return true;
            } else {
                previousNumber = input;
                subtracted = false;
                return false;
            }
        }
        if (subtracted && previousNumber.getValue()>input.getValue()){
            previousNumber = input;
            subtracted = false;
            return true;
        }
        if (subtracted && previousNumber.getValue() <= input.getValue()){
            previousNumber = input;
            subtracted = true;
            return false;
        }
        return false;
    }
}
