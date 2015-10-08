package com.kdi.thoughtworks.galaxymerchant;

import com.kdi.thoughtworks.galaxymerchant.Exceptions.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void testParseNumber() throws ParseException{
        Parser parser = new Parser();
        parser.parseLine("glob is I");
        assertNotNull(parser.getRomanNumbers());
        assertEquals(parser.getRomanNumbers().size(), 1);
        assertEquals(parser.getRomanNumbers().get("glob"),RomanNumber.I);
    }
    @Test
    public void testParseCurrency() throws ParseException{
        Parser parser = new Parser();
        parser.parseLine("glob is I");
        parser.parseLine("prok is V");
        parser.parseLine("pish is X");
        parser.parseLine("tegj is L");
        parser.parseLine("glob glob Silver is 34 Credits");
        assertEquals(parser.getCurrencies().size(), 1);
        assertEquals(parser.getCurrencies().get("Silver").getName(),"Silver");
    }
    @Test
    public void testParseHowMany() throws ParseException{
        Parser parser = new Parser();
        parser.parseLine("how many Credits is glob prok Silver ?");
        assertEquals(parser.getExpressions().size(), 1);
        assertEquals(parser.getExpressions().get(0).getType(),SentenceType.HOW_MANY);
    }
    @Test
    public void testParseHowMuch() throws ParseException{
        Parser parser = new Parser();
        parser.parseLine("how much is pish tegj glob glob ?");
        assertEquals(parser.getExpressions().size(),1);
        assertEquals(parser.getExpressions().get(0).getType(), SentenceType.HOW_MUCH);
    }
    @Test
    public void testParseUnknown() throws ParseException{
        Parser parser = new Parser();
        parser.parseLine("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        assertEquals(parser.getExpressions().size(),1);
        assertEquals(parser.getExpressions().get(0).getType(), SentenceType.NONE);
    }
}
