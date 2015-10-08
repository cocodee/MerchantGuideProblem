package com.kdi.thoughtworks.galaxymerchant.expressions;

import com.kdi.thoughtworks.galaxymerchant.SentenceType;
import com.kdi.thoughtworks.galaxymerchant.expressions.Expression;

public class HowManyExpression extends Expression {
    private String numberNames;
    private String currencyName;

    public HowManyExpression(String numberNames, String currencyName, SentenceType type) {
        super(type);
        this.numberNames = numberNames;
        this.currencyName = currencyName;
    }

    public String getNumberNames() {
        return numberNames;
    }

    public void setNumberNames(String numberNames) {
        this.numberNames = numberNames;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }
}
