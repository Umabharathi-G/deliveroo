package com.deliveroo.cronparser;

import java.util.Set;

public class CronExpression{
    private CronField minute;
    private CronField hour;
    private CronField dayOfMonth;
    private CronField month;
    private CronField dayOfWeek;
    private String command;

    public CronExpression(String minuteExp, String hourExp, String dayOfMonthExp,
                          String monthExp, String dayOfWeekExp, String command) {
        this.minute = new CronField(CronFieldType.MINUTES, minuteExp);
        this.hour = new CronField(CronFieldType.HOURS, hourExp);
        this.dayOfMonth = new CronField(CronFieldType.DAY_OF_MONTH, dayOfMonthExp);
        this.month = new CronField(CronFieldType.MONTH, monthExp);
        this.dayOfWeek = new CronField(CronFieldType.DAY_OF_WEEK, dayOfWeekExp);
        validateCommand(command);
        this.command = command;
    }

    public void printCronExpression() {
        printField("minute", minute.getParsedValues());
        printField("hour", hour.getParsedValues());
        printField("day of month", dayOfMonth.getParsedValues());
        printField("month", month.getParsedValues());
        printField("day of week", dayOfWeek.getParsedValues());
        printField("command", command);
    }

    private void validateCommand(String command){
        if (command == null || command.trim().isEmpty()) {
            throw new IllegalArgumentException("Command field cannot be empty.");
        }
    }

    private void printField(String name, Set<Integer> values){
        System.out.printf("%-14s ", name);
        for(int val : values){
            System.out.print(val + " ");
        }
        System.out.println();
    }

    private void printField(String name, String value){
        System.out.printf("%-14s %s%n", name, value);
    }
}