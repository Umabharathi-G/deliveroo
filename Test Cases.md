# Test Cases for Cron Parser

Note: While the behaviour is expected to be the same as mentioned here, the actual wording of the error messages might differ. 

### 1. Valid Expression with All Fields

#### Input: 
```bash
"*/15 0 1-7/2 * 1 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 15 30 45
hour          0 
day of month  1 3 5 7 
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 
command       /usr/bin/find
```

### 2. Invalid Minute Range
#### Input: 
```bash
"60 0 1 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid value '60'. Allowed range is 0-59.
```

### 3. Invalid Hour Range
#### Input: 
```bash
"0 24 1 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid value '24'. Allowed range is 0-23.
```

### 4. Invalid Day of Month Range
#### Input: 
```bash
"0 0 32 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid value '32'. Allowed range is 1-31.
```

### 5. Invalid Month Range (0)
#### Input: 
```bash
"0 0 1 0 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid value '0'. Allowed range is 1-12.
```

### 6. Invalid Month Range (13)
#### Input: 
```bash
"0 0 1 13 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid value '13'. Allowed range is 1-12.
```

### 7. Invalid Day of Week Range (0)
#### Input: 
```bash
"0 0 1 1 0 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid value '0'. Allowed range is 1-7.
```

### 8. Invalid Day of Week Range (8)
#### Input: 
```bash
"0 0 1 1 8 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid value '8'. Allowed range is 1-7.
```

### 9. Multiple Value Ranges
#### Input: 
```bash
"0 12 1-15,20-25 1 1,7 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 
hour          12 
day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 20 21 22 23 24 25 
month         1 
day of week   1 7 
command       /usr/bin/find
```

### 10. Wildcard
#### Input: 
```bash
"*/10 * * * 2,4,6 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 10 20 30 40 50 
hour          0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 
day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   2 4 6 
command       /usr/bin/find
```

### 11. Invalid Syntax (Multiple Step for 1 wildcard)
#### Input: 
```bash
"*/15/5 0 1 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid expression '*/15/5'. Multiple step values are not allowed.
```

### 12. Invalid Syntax (Multiple Step for 1 Range)
#### Input: 
```bash
"0 0 1-5/2/3 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid expression '1-5/2/3'. Multiple step values are not allowed.
```

### 13. Valid Step Expression
#### Input: 
```bash
"*/5 0 1 * * /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 5 10 15 20 25 30 35 40 45 50 55
hour          0 
day of month  1 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   1 2 3 4 5 6 7 
command       /usr/bin/find
```

### 14. Valid List and Range Combination
#### Input: 
```bash
"0 0 1,5,10-12 3 3 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 
hour          0 
day of month  1 5 10 11 12 
month         3 
day of week   3 
command       /usr/bin/find
```

### 15. Valid Expression with All Wildcards
#### Input: 
```bash
"* * * * * /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59
hour          0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 
day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   1 2 3 4 5 6 7 
command       /usr/bin/find
```

### 16. Valid Range with Step Expression
#### Input:
```bash
"*/5 1-6/2 1-15/3 * 2-4 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 5 10 15 20 25 30 35 40 45 50 55
hour          1 3 5 
day of month  1 4 7 10 13 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   2 3 4 
command       /usr/bin/find
```

### 17. Empty Command
#### Input: 
```bash
"* * * * * "
```
#### Expected Output:
```bash
Error: Command cannot be empty.
```

### 18. Specific Day of the Week Range
#### Input: 
```bash
"0 0 * * 1-7 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 
hour          0 
day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   1 2 3 4 5 6 7 
command       /usr/bin/find
```

### 19. Out-of-Bounds Day of the Week Range
#### Input: 
```bash
"0 0 * * 6-8 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid range '6-8'. Allowed range is 1-7.
```

### 20. Combined Specific Values and Wildcards
#### Input: 
```bash
"15,30,45 6-18 * * 2 /usr/bin/find"
```
#### Expected Output:
```bash
minute        15 30 45 
hour          6 7 8 9 10 11 12 13 14 15 16 17 18 
day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 
month         1 2 3 4 5 6 7 8 9 10 11 12 
day of week   2 
command       /usr/bin/find
```

### 21. Combined List and Range in Minute Field
#### Input: 
```bash
"0,15-30 12 1 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 
hour          12 
day of month  1 
month         1 
day of week   1 
command       /usr/bin/find
```

### 22. Combined List, Range, and Step in Hour Field
#### Input: 
```bash
"0 1,5-10/2 1 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 
hour          1 5 7 9 
day of month  1 
month         1 
day of week   1 
command       /usr/bin/find
```

### 23. List, Range, and Step in Day of Month Field
#### Input: 
```bash
"0 12 1,10-20/3 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 
hour          12 
day of month  1 10 13 16 19 
month         1 
day of week   1 
command       /usr/bin/find
```

### 24. List, Range, and Step in Month Field
#### Input: 
```bash
"0 12 1 2,3-9/2 1 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 
hour          12 
day of month  1 
month         2 3 5 7 9 
day of week   1 
command       /usr/bin/find
```

### 25. List, Range, and Step in Day of Week Field
#### Input: 
```bash
"0 12 1 1 1,2-6/2 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 
hour          12 
day of month  1 
month         1 
day of week   1 2 4 6 
command       /usr/bin/find
```

### 26. Invalid Multiple Steps in Month Field
#### Input: 
```bash
"0 12 1 */2/3 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid expression '*/2/3'. Multiple step values are not allowed.
```

### 27. List, Range, and Step in Every Field
#### Input: 
```bash
"5,10-30/5 1,2-10/2 1,10-20/2 1,2-6/2 2,3-7/2 /usr/bin/find"
```
#### Expected Output:
```bash
minute        5 10 15 20 25 30 
hour          1 2 4 6 8 10 
day of month  1 10 12 14 16 18 20 
month         1 2 4 6 
day of week   2 3 5 7
command       /usr/bin/find
```

### 28. Combination of Wildcard, Range, List, and Step
#### Input: 
```bash
"*/10 3-6/2,12 1,15-20/3 1 2,4-6 /usr/bin/find"
```
#### Expected Output:
```bash
minute        0 10 20 30 40 50 
hour          3 5 12 
day of month  1 15 18 
month         1 
day of week   2 4 5 6 
command       /usr/bin/find
```

### 29. Command not given
#### Input: 
```bash
"* * * * *"
```
#### Expected Output:
```bash
Error: Expected 5 fields and a command.
```

### 30. Range not given for Step
#### Input: 
```bash
"0 0 5/3 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid expression '5/3'. Argument for step should either be a wildcard or a range.
```

### 31. Invalid Step Range
#### Input: 
```bash
"0 0 5-29/35 1 1 /usr/bin/find"
```
#### Expected Output:
```bash
Error: Invalid step '35'. Step value should be within the range 1-31.
```