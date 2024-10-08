package com.deliveroo.cronparser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class CronFieldHandler {
    private static final Map<String, Integer> dayMap = new HashMap<>();
    
    static{
        dayMap.put("SUN", 1);
        dayMap.put("MON", 2);
        dayMap.put("TUE", 3);
        dayMap.put("WED", 4);
        dayMap.put("THU", 5);
        dayMap.put("FRI", 6);
        dayMap.put("SAT", 7);
    }
    
    public static Set<Integer> handleField(String expression, int min, int max) {
        
        expression = parseDayName(expression);

        if (expression.equals("*")) {
            return parseWildcard(min, max);
        } else if (expression.contains(",")) {
            return parseList(expression, min, max);
        } else if (expression.contains("/")) {
            return parseStep(expression, min, max);
        } else if (expression.contains("-")) {
            return parseRange(expression, min, max);
        } else {
            return parseSingleValue(expression, min, max);
        }
    }

    private static String parseDayName(String expression){
        expression = expression.toUpperCase();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < expression.length();){
            char ch = expression.charAt(i);
            if(ch >= 'A' && ch <= 'Z'){
                String s = expression.substring(i, i + 3);
                int index = dayMap.get(s);
                sb.append(index);
                i = i + 3;
            }
            else{
                sb.append(ch);
                i++;
            }
        }
        return sb.toString();
    }

    private static Set<Integer> parseWildcard(int min, int max) {
        Set<Integer> values = new TreeSet<>();
        for (int i = min; i <= max; i++) {
            values.add(i);
        }
        return values;
    }

    private static Set<Integer> parseList(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        String[] parts = expression.split(",");
        for (String part : parts) {
            values.addAll(handleField(part, min, max));
        }
        return values;
    }

    private static Set<Integer> parseRange(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        String[] rangeParts = expression.split("-");
        int start = Integer.parseInt(rangeParts[0]);
        int end = Integer.parseInt(rangeParts[1]);

        // Validate range
        if (start > end || start < min || end > max) {
            throw new IllegalArgumentException(
                    String.format("Invalid range '%s'. Allowed range is %d-%d.", expression, min, max));
        }

        for (int i = start; i <= end; i++) {
            values.add(i);
        }
        return values;
    }

    private static Set<Integer> parseStep(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        String[] parts = expression.split("/");
        
        //Validate for step values like 12-19/2/3
        if (parts.length > 2) {
            throw new IllegalArgumentException(
                    String.format("Invalid expression '%s'. Only one '/' is allowed for a range.", expression));
        }
        
        int step = Integer.parseInt(parts[1]);

        // Validate step
        if (step <= 0 || step > max - min + 1) {
            throw new IllegalArgumentException(
                    String.format("Invalid step '%d'. Step value should be within the range %d-%d.", step, min, max));
        }

        String base = parts[0];
        //1st arg of step should be a wildcard or a range
        if(!(base.contains("*") || base.contains("-"))){
            throw new IllegalArgumentException(
                    String.format("Invalid expression '%s'. Argument for step should either be a wildcard or a range.", expression));
        }

        Set<Integer> baseValues = handleField(base, min, max);

        int currentStepIndex = 0;
        for (int value : baseValues) {
            if (currentStepIndex % step == 0) {
                values.add(value);
            }
            currentStepIndex++;
        }

        return values;
    }

    private static Set<Integer> parseSingleValue(String expression, int min, int max) {
        Set<Integer> values = new TreeSet<>();
        int value = Integer.parseInt(expression);

        // Validate single value
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    String.format("Invalid value '%d'. Allowed range is %d-%d.", value, min, max));
        }
        values.add(value);
        return values;
    }
}
