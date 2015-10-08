package com.kdi.thoughtworks.galaxymerchant;

import com.kdi.thoughtworks.galaxymerchant.Exceptions.ParseException;
import com.kdi.thoughtworks.galaxymerchant.Exceptions.RomanNumberNotFoundException;
import com.kdi.thoughtworks.galaxymerchant.Exceptions.RuleViolationException;
import com.kdi.thoughtworks.galaxymerchant.expressions.Expression;
import com.kdi.thoughtworks.galaxymerchant.expressions.HowManyExpression;
import com.kdi.thoughtworks.galaxymerchant.expressions.HowMuchExpression;
import com.kdi.thoughtworks.galaxymerchant.expressions.UnknownExpression;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static String regRomanNumber = "^(\\w+) is ([I|V|X|L|C|D|M])$";
    private static String regCurrency = "((?:\\w+ +)+)([A-Z]\\w+) is (\\d+) Credits$";
    private static String regHowMany =  "^how many Credits is ((?:\\w+ +)+)([A-Z]\\w+) *\\? *$";
    private static String regHowMuch = "^how much is ((?:\\w+ +)+)\\? *$";
    private static Pattern ptnRomanNumber = Pattern.compile(regRomanNumber);
    private static Pattern ptnCurrency =  Pattern.compile(regCurrency);
    private static Pattern ptnHowMany = Pattern.compile(regHowMany);
    private static Pattern ptnHowMuch = Pattern.compile(regHowMuch);

    private Map<String,Currency> currencies= new HashMap<String,Currency>();
    private Map<String,RomanNumber> romanNumbers = new HashMap<String,RomanNumber>();
    private ArrayList<String> questions = new ArrayList<String>();
    private ArrayList<Expression> expressions = new ArrayList<Expression>();

    public Map<String, RomanNumber> getRomanNumbers() {
        return romanNumbers;
    }

    public void setRomanNumbers(Map<String, RomanNumber> romanNumbers) {
        this.romanNumbers = romanNumbers;
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(Map<String, Currency> currencies) {
        this.currencies = currencies;
    }

    public void parse(String input) throws Exception{

    }

    public void parseLine(String line) throws ParseException{
        SentenceType type = checkType(line);
        switch(type) {
            case NUMBER:
                parseNumber(line);
                break;
            case CURRENCY:
                parseCurrency(line);
                break;
            case HOW_MANY:
                parseHowMany(line);
                break;
            case HOW_MUCH:
                parseHowMuch(line);
                break;
            default:
                parseNone(line);
                break;
        }
    }
    private void parseNumber(String line) {
        Matcher matcher = ptnRomanNumber.matcher(line);
        matcher.matches();
        String name = matcher.group(1).trim();
        String code = matcher.group(2).trim();
        if (!romanNumbers.containsKey(name)) {
            romanNumbers.put(name, RomanNumber.valueOf(code));
        }
    }
    private void parseCurrency(String line) throws ParseException{
        Matcher matcher  = ptnCurrency.matcher(line);
        matcher.matches();
        String[] numberNames = matcher.group(1).split(" ");
        String currencyName = matcher.group(2);
        int credits = Integer.parseInt(matcher.group(3).trim());
        try {
            RomanNumber[] romanNums = Evaluator.getRomanByNames(numberNames, this);
            int value = Evaluator.getValue(romanNums);
            BigDecimal currencyCredits = new BigDecimal(credits).divide(new BigDecimal(value));
            currencies.put(currencyName, new Currency(currencyName, currencyCredits));
        } catch (RomanNumberNotFoundException rnne) {
            throw new ParseException(line,rnne.getMessage());
        } catch (RuleViolationException rve) {
            throw new ParseException(line,rve.getMessage());
        }
    }
    private void parseHowMany(String line) {
        Matcher matcher = ptnHowMany.matcher(line);
        matcher.matches();
        String numbers = matcher.group(1);
        String currencyName = matcher.group(2);
        expressions.add(new HowManyExpression(numbers, currencyName, SentenceType.HOW_MANY));
    }
    private void parseHowMuch(String line) {
        Matcher matcher = ptnHowMuch.matcher(line);
        matcher.matches();
        String numbers = matcher.group(1);
        expressions.add(new HowMuchExpression(numbers,SentenceType.HOW_MUCH));
    }

    private void parseNone(String line) {
        expressions.add(new UnknownExpression());
    }

    public SentenceType checkType(String line) {
        SentenceType type = SentenceType.NONE;
        Pattern[] patterns = new Pattern[]{
                ptnRomanNumber,ptnCurrency,ptnHowMany,ptnHowMuch
        };
        for (int i = 0; i<patterns.length; i++ ){
            Pattern ptn = patterns[i];
            Matcher matcher = ptn.matcher(line);
            if (matcher.matches()) {
                switch (i){
                    case 0:
                        type = SentenceType.NUMBER;
                        break;
                    case 1:
                        type = SentenceType.CURRENCY;
                        break;
                    case 2:
                        type = SentenceType.HOW_MANY;
                        break;
                    case 3:
                        type = SentenceType.HOW_MUCH;
                        break;
                    default:
                        break;
                }
                break;
            }
        }
        return type;
    }
 }
