## Description

A merge conflict at the end of the project's git history caused the test to have a compilation error

## Expected Behavior

The test compiles and runs

## Current Behavior

The test does not compile

## Steps to Reproduce

Provide detailed steps for reproducing the issue.

1. Run All Tests
2. Test does not compile

## Failure Logs

C:\Users\andre\Documents\22-23_Sem1\CS307\Projects\ooga_team09\src\test\java\ooga\model\actions\moveractions\basicmovement\UpwardMovementTest.java:26
java: illegal start of expression

## Hypothesis for Fixing the Bug

We just need to remove the 
<<<<<<< HEAD
======
>>>>>>> master