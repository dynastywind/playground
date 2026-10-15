package org.lyndon.design.calculator;

import lombok.Value;

@Value
public class Number implements Expression{

    int num;

    @Override
    public int eval() {
        return num;
    }

}
