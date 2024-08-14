package com.deliveroo.cronparser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class CronParserTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private PrintStream originalErr;

    @BeforeEach
    public void setUpStreams() {
        originalOut = System.out;
        originalErr = System.err;
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testValidCronExpression() {
        String cronExpression = "*/10 0 1-15/3 * 2-4 /usr/bin/find";

        String[] args = { cronExpression };

        CronParser.handleCronExpression(args);

        String expectedOutput = 
        "minute        0 10 20 30 40 50\n" +
        "hour          0\n" +
        "day of month  1 4 7 10 13\n" +
        "month         1 2 3 4 5 6 7 8 9 10 11 12\n" +
        "day of week   2 3 4\n" +
        "command       /usr/bin/find\n";

        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }

    @Test
    public void testInvalidCronExpressionFieldCount() {
        String cronExpression = "*/10 0 1-15/3 *";

        String[] args = { cronExpression };

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CronParser.handleCronExpression(args);
        });

        String expectedError = "Invalid cron expression. Expected 5 fields and a command.";
        String actualError = exception.getMessage();

        assertTrue(actualError.contains(expectedError));
    }

    @Test
    public void testInvalidCronExpressionArgumentCount() {
        String[] args = {}; // No arguments

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CronParser.handleCronExpression(args);
        });

        String expectedError = "Usage: java CronParser \"<cron_expression>\"";
        String actualError = exception.getMessage();

        assertTrue(actualError.contains(expectedError));
    }
}
