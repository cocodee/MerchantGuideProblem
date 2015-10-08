package com.kdi.thoughtworks.galaxymerchant;

import com.kdi.thoughtworks.galaxymerchant.Exceptions.ParseException;
import com.kdi.thoughtworks.galaxymerchant.expressions.Expression;
import com.kdi.thoughtworks.galaxymerchant.expressions.HowManyExpression;
import com.kdi.thoughtworks.galaxymerchant.expressions.HowMuchExpression;
import com.kdi.thoughtworks.galaxymerchant.expressions.UnknownExpression;

import java.math.BigDecimal;

public class Processer {
    private Parser parser;
    private Evaluator evaluator;
    public Processer() {
        parser = new Parser();
        evaluator = new Evaluator(parser);
    }
    public void processInput(String line) throws ParseException{
        try {
            if (line.trim().equals("")) {
                return;
            }
            parser.parseLine(line);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    public void generateOutput() {
        for (Expression exp:parser.getExpressions()) {
            switch (exp.getType()) {
                case HOW_MANY:
                    generateHowManyOutput((HowManyExpression) exp);
                    break;
                case HOW_MUCH:
                    generateHowMuchOutput((HowMuchExpression) exp);
                    break;
                case NONE:
                    generateUnknownOutput(exp);
            }
        }
    }

    private void generateHowManyOutput(HowManyExpression exp) {
        try {
            String[] numberNames = exp.getNumberNames().split(" ");
            int value = Evaluator.getValue(Evaluator.getRomanByNames(numberNames, parser));
            Currency currency = parser.getCurrencies().get(exp.getCurrencyName());
            BigDecimal result = currency.getValue().multiply(new BigDecimal(value));
            StringBuilder sb = new StringBuilder();
            sb.append(exp.getNumberNames()).append(" is ").append(result.stripTrailingZeros().toPlainString()).append(" Credits");
            System.out.println(sb.toString());
        } catch (Exception e) {
            generateUnknownOutput(new UnknownExpression());
        }
    }

    private void generateHowMuchOutput(HowMuchExpression exp) {
        try {
            String[] numberNames = exp.getNumberNames().split(" ");
            int value = Evaluator.getValue(Evaluator.getRomanByNames(numberNames, parser));
            StringBuilder sb = new StringBuilder();
            sb.append(exp.getNumberNames()).append(" is ").append(value);
            System.out.println(sb.toString());
        } catch (Exception e) {
            generateUnknownOutput(new UnknownExpression());
        }
    }
    private void generateUnknownOutput(Expression exp) {
        System.out.println("I have no idea what you are talking about");
    }
}
