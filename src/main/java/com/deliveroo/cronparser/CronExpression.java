package com.deliveroo.cronparser;

import java.util.Set;

public class CronExpression {
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
        StringBuilder output = new StringBuilder();

        appendField(output, "minute", minute.getParsedValues());
        appendField(output, "hour", hour.getParsedValues());
        appendField(output, "day of month", dayOfMonth.getParsedValues());
        appendField(output, "month", month.getParsedValues());
        appendField(output, "day of week", dayOfWeek.getParsedValues());
        appendField(output, "command", command);

        System.out.print(output.toString());
    }

    private void validateCommand(String command) {
        if (command == null || command.trim().isEmpty()) {
            throw new IllegalArgumentException("Command field cannot be empty.");
        }
    }

    private void appendField(StringBuilder output, String name, Set<Integer> values) {
        output.append(String.format("%-14s", name));
        if (values != null && !values.isEmpty()) {
            for (int val : values) {
                output.append(val);
                output.append(" ");
            }
        }
        output.setLength(output.length() - 1);
        output.append("\n");
    }

    private void appendField(StringBuilder output, String name, String value) {
        output.append(String.format("%-14s%s%n", name, value));
    }
}
