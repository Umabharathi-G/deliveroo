package com.deliveroo.cronparser;

public class CronParser {

    private static final int EXPRESSION_SIZE = 6;

    public static void main(String[] args) {
        try {
            handleCronExpression(args);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void handleCronExpression(String[] args) {
        String[] expressionParts = validateAndPreprocessExpression(args);

        String minuteExp = expressionParts[0];
        String hourExp = expressionParts[1];
        String dayOfMonthExp = expressionParts[2];
        String monthExp = expressionParts[3];
        String dayOfWeekExp = expressionParts[4];
        String command = expressionParts[5];

        try {
            CronExpression cronExpression = new CronExpression(minuteExp, hourExp, dayOfMonthExp, monthExp, dayOfWeekExp, command);
            cronExpression.printCronExpression();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error processing cron expression: " + e.getMessage(), e);
        }
    }

    private static String[] validateAndPreprocessExpression(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Usage: java CronParser \"<cron_expression>\"");
        }

        String expression = args[0];
        String[] expressionParts = expression.split(" ", EXPRESSION_SIZE);

        if (expressionParts.length != EXPRESSION_SIZE) {
            throw new IllegalArgumentException("Invalid cron expression. Expected 5 fields and a command.");
        }
        return expressionParts;
    }
}
