package com.deliveroo.cronparser;

import java.util.Set;

public class CronField {
    private final CronFieldType fieldType;
    private final String expression;
    private final Set<Integer> parsedValues;

    public CronField(CronFieldType fieldType, String expression) {
        this.parsedValues = CronFieldHandler.handleField(expression, fieldType.getMin(), fieldType.getMax());
        this.fieldType = fieldType;
        this.expression = expression;
    }

    public Set<Integer> getParsedValues() {
        return parsedValues;
    }

    public CronFieldType getFieldType() {
        return fieldType;
    }

    public String getExpression() {
        return expression;
    }
}
