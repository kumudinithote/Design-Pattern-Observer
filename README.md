# CSX42: Assignment 2
## Name: 

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile numberPlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile numberPlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant run -buildfile numberPlay/src/build.xml \
-DinputNumStream="<input file path>" \
-DrunAvgWindowSize="<size of the window for running average calculations>" \
-DrunAvgOutFile="<output file path to which running averages are written>" \
-Dk="<max size of the list containing the top K numbers>" \
-DtopKNumOutFile="<path of output file to which the top K numbers are written>" \
-DnumPeaksOutFile="<path of output file to which the peaks in the number stream are written>"
```

-----------------------------------------------------------------------
## Description:

The program accepts in input file which contains integer and floting point numbers. NumberProcessor will get the number which are read and will operate as bellow:
1) Event will be triggered based upton data type of number.
2) Three events which are Interger, Floting and processing complete can be triggered.

If the number read is interger then IntegerEvent will get triggered.
If the number read is float or double then FloatEvent will get triggered.
Respective observer will get notified based on the Event triggered along with the number and notification.
Each Observer then carries out some operations using the provided number and stores the result of the operation.
Once all the number has been read, NumberProcessor will trigger ProcessingComplete Event and will notify all the observer.And the observer should persit the result into corresponding output file.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [February 23 2020]


