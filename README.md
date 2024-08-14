# Cron Expression Parser

## Overview

This project is a Java-based Cron Expression Parser, which parses and validates cron expressions according to standard cron syntax. The parser is capable of interpreting cron fields, including ranges, steps, wildcards, and lists, and provides detailed outputs of the parsed cron schedule.

## Project Structure

- **CronParser.java**: The main entry point for the application. It parses the input cron expression and prints the parsed schedule.
- **CronExpression.java**: Represents the parsed cron expression and contains logic for managing and displaying the parsed output.
- **CronField.java**: Represents a single field in a cron expression (e.g., minutes, hours). It holds the parsed values for that field.
- **CronFieldHandler.java**: Contains logic for parsing and validating cron fields. This includes handling wildcards (`*`), lists (e.g., `1,2,3`), ranges (e.g., `1-5`), and steps (e.g., `*/2`).

## Dependencies

This project uses the following dependencies:

- **JUnit 5**: Used for unit testing. Ensure that your `pom.xml` includes the JUnit 5 dependency:

  ```xml
  <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>5.9.3</version>
      <scope>test</scope>
  </dependency>
