package com.kdi.thoughtworks.galaxymerchant.expressions;

import com.kdi.thoughtworks.galaxymerchant.SentenceType;

public class Expression {
    String expression;
    SentenceType type;

    public Expression(SentenceType type) {
        this.type = type;
    }

    public Expression(String expression, SentenceType type) {
        this.expression = expression;
        this.type = type;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public SentenceType getType() {
        return type;
    }

    public void setType(SentenceType type) {
        this.type = type;
    }
}
