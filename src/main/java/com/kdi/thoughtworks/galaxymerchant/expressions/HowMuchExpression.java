package com.kdi.thoughtworks.galaxymerchant.expressions;

import com.kdi.thoughtworks.galaxymerchant.SentenceType;
import com.kdi.thoughtworks.galaxymerchant.expressions.Expression;

public class HowMuchExpression extends Expression {
    private String numberNames;

    public HowMuchExpression(String numberNames,SentenceType type) {
        super(type);
        this.numberNames = numberNames;
    }

    public String getNumberNames() {
        return numberNames;
    }

    public void setNumberNames(String numberNames) {
        this.numberNames = numberNames;
    }
}
