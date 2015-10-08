package com.kdi.thoughtworks.galaxymerchant;

import com.kdi.thoughtworks.galaxymerchant.Exceptions.RuleViolationException;
import org.junit.Test;
import static org.junit.Assert.*;


public class EvaluatorTest {
    @Test(expected = com.kdi.thoughtworks.galaxymerchant.Exceptions.RuleViolationException.class)
    public void testRepeatitionRuleFail_1() throws RuleViolationException{
        Evaluator.getValue(new RomanNumber[]{RomanNumber.I,RomanNumber.V,RomanNumber.I,RomanNumber.I,RomanNumber.I,RomanNumber.I});
    }
    @Test
    public void testRepeatitionRulePass() throws RuleViolationException{
        int value = Evaluator.getValue(new RomanNumber[]{RomanNumber.I,RomanNumber.V,RomanNumber.I,RomanNumber.I,RomanNumber.I});
        assertEquals(value, 7);
    }
    @Test
    public void testSubtractPass() throws RuleViolationException{
        int value = Evaluator.getValue(new RomanNumber[]{RomanNumber.C,RomanNumber.M,RomanNumber.X,RomanNumber.L,RomanNumber.I,RomanNumber.X});
        assertEquals(value,949);
    }
    @Test(expected = com.kdi.thoughtworks.galaxymerchant.Exceptions.RuleViolationException.class)
    public void testSubtractRuleFail_1() throws RuleViolationException{
        int value = Evaluator.getValue(new RomanNumber[]{RomanNumber.D,RomanNumber.M});
    }

    @Test(expected = com.kdi.thoughtworks.galaxymerchant.Exceptions.RuleViolationException.class)
    public void testSubtractRuleFail_2() throws RuleViolationException{
        int value = Evaluator.getValue(new RomanNumber[]{RomanNumber.C,RomanNumber.M, RomanNumber.M});
    }
}
