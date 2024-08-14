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

## Getting Started

### Download the jar
You can download the jar file from [cron-parser.jar](https://github.com/Umabharathi-G/deliveroo/releases)

Or you can follow the below steps to install the cron parser.

### Prerequisites

- **Java Version**: 17
- **Maven Version**: 3.9.8

### Installation

To get started with the `cron-parser` project, follow these steps:

1. **Clone** the repository:

    ```bash
    git clone https://github.com/yourusername/cron-parser.git
    ```
    ```bash
    cd cron-parser
    ```

2. **Build** the project using Maven:

    ```bash
    mvn clean install
    ```
This will create a jar file in the target directory.


## Usage

The `cron-parser` application allows you to parse cron expressions. Provide a valid cron expression as a string argument when running the program. The cron expression must consist of five fields representing:

- **Minutes** (0 - 59)
- **Hours** (0 - 23)
- **Day of the Month** (1 - 31)
- **Month** (1 - 12)
- **Day of the Week** (0 - 6)
- **Command to execute**

The program will output each field's parsed values, formatted for easy reading.

## Example
Navigate to the target directory and run this command:

```bash
java -jar cronparser-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```
Or run the downloaded jar from it's location

```bash
java -jar cron-parser.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

Would output the parsed Cron Expression in the below format

```
minute        0 10 20 30 40 50
hour          0
day of month  15 17 19 21 23 25 27
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   2 3 4 5
command       /usr/bin/find
```
## Running Tests
To run the unit tests for this project, use the following Maven command:

```bash
mvn test
```

This will execute all the tests and provide a summary of the test results.