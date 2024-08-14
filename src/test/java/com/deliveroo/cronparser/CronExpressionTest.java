package com.deliveroo.cronparser;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CronExpressionTest {

    @Test
    public void testPrintCronExpression() {
        String minuteExp = "*/10";
        String hourExp = "0";
        String dayOfMonthExp = "1-15/3";
        String monthExp = "*";
        String dayOfWeekExp = "2-4";
        String command = "/usr/bin/find";

        CronExpression cronExpression = new CronExpression(minuteExp, hourExp, dayOfMonthExp, monthExp, dayOfWeekExp, command);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        cronExpression.printCronExpression();

        String expectedOutput = 
            "minute        0 10 20 30 40 50\n" +
            "hour          0\n" +
            "day of month  1 4 7 10 13\n" +
            "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
            "day of week   2 3 4\n" +
            "command       /usr/bin/find\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    public void testInvalidCronExpression() {
        String minuteExp = "*/10";
        String hourExp = "0";
        String dayOfMonthExp = "32";
        String monthExp = "*";
        String dayOfWeekExp = "2-4";
        String command = "/usr/bin/find";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CronExpression(minuteExp, hourExp, dayOfMonthExp, monthExp, dayOfWeekExp, command);
        });

        String expectedMessage = "Invalid value '32'. Allowed range is 1-31.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
