package com.kdi.thoughtworks.galaxymerchant;

public enum RomanNumber {
    I("I",1),
    V("V",5),
    X("X",10),
    L("L",50),
    C("C",100),
    D("D",500),
    M("M",1000)
    ;
    RomanNumber(String code,int value) {
        this.code = code;
        this.value = value;
    }
    private String code;
    private int value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    public static RomanNumber fromCode(String code) throws Exception{
        switch(code) {
            case "I":
                return RomanNumber.I;
            case "V":
                return RomanNumber.V;
            case "X":
                return RomanNumber.X;
            case "L":
                return RomanNumber.L;
            case "C":
                return RomanNumber.C;
            case "D":
                return RomanNumber.D;
            case "M":
                return RomanNumber.M;
            default:
                throw new Exception();
        }
    }
}
