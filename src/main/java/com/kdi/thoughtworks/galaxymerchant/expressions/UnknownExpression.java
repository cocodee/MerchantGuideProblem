package com.kdi.thoughtworks.galaxymerchant.expressions;

import com.kdi.thoughtworks.galaxymerchant.SentenceType;
import com.kdi.thoughtworks.galaxymerchant.expressions.Expression;

public class UnknownExpression extends Expression {
    public UnknownExpression() {
        super(SentenceType.NONE);
    }
}
