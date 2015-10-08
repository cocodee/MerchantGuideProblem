package com.kdi.thoughtworks.galaxymerchant;

import com.kdi.thoughtworks.galaxymerchant.Exceptions.RomanNumberNotFoundException;
import com.kdi.thoughtworks.galaxymerchant.Exceptions.RuleViolationException;
import com.kdi.thoughtworks.galaxymerchant.rules.RepeatitionRule;
import com.kdi.thoughtworks.galaxymerchant.rules.SubtractRule;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {
    private Parser parser;
    public Evaluator(Parser parser) {
        this.parser = parser;
    }

    public static int getValue(RomanNumber[] romanNums) throws RuleViolationException{
        int value = 0;
        List<Integer> nums = getNumbers(romanNums);
        for(int i:nums) {
            value += i;
        }
        return value;
    }
    public static List<Integer> getNumbers(RomanNumber[] romanNums) throws RuleViolationException{
        int current;
        RepeatitionRule repeatitionRule = new RepeatitionRule();
        SubtractRule subtractRule = new SubtractRule();
        List<Integer> intList = new ArrayList<Integer>();
        for (int i = 0;i<romanNums.length-1;i++) {
            if (!repeatitionRule.accept(romanNums[i])) {
                throw new RuleViolationException("Repetition Rule");
            }
            if (!subtractRule.accept(romanNums[i])){
                throw new RuleViolationException("Subtract Rule");
            }
            current = romanNums[i].getValue();
            if (current<romanNums[i+1].getValue()) {
                current = -current;
            }
            intList.add(current);
        }
        if (!repeatitionRule.accept(romanNums[romanNums.length-1])) {
            throw new RuleViolationException("Repetition Rule");
        }
        if (!subtractRule.accept(romanNums[romanNums.length-1])){
            throw new RuleViolationException("Subtract Rule");
        }
        intList.add(romanNums[romanNums.length-1].getValue());
        return intList;
    }

    public static RomanNumber[] getRomanByNames(String[] numberNames,Parser parser) throws RomanNumberNotFoundException{
        RomanNumber[] romanNums = new RomanNumber[numberNames.length];
        for (int i=0;i<numberNames.length;i++) {
            romanNums[i] = parser.getRomanNumbers().get(numberNames[i]);
            if (romanNums[i]==null) {
                throw new RomanNumberNotFoundException(numberNames[i]);
            }
        }
        return romanNums;
    }
}
