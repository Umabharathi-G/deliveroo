package com.deliveroo.cronparser;

public class CronParser 
{
    private static final int expressionSize = 6;

    public static void main( String[] args )
    {
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
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            System.exit(1);
        }

    }

    private static String[] validateAndPreprocessExpression(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: java CronParser \"<cron_expression>\"");
            System.exit(1);
        }

        String expression = args[0];
        String[] expressionParts = expression.split(" ", expressionSize);

        if (expressionParts.length != expressionSize) {
            System.err.println("Invalid cron expression. Expected 5 fields and a command.");
            System.exit(1);
        }
        return expressionParts;
    }
}
