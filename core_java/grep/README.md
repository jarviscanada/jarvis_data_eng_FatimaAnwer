# Java Grep App

## Introduction
The Java Grep App simulates the linux grep command that searches for a specified text pattern in an input file.
Similarly, the App searches for the pattern (regex) in the files of the input root directory and its sub-directories recursively.
The lines that contain the pattern are saved to the specified output file. 

The App is implemented 
using Java I/O, such as FileReader, BufferedReader, FileWriter, and BufferedWriter, Functional Interfaces and Lambda Expressions.
To make the code efficient and light-weight, the listing and reading of files is reimplemented using Java 8 Stream API.

The project is built in IntelliJ using Maven. The App is also Dockerized and pushed to Docker Hub.

## Quick Start
1. To run using source code:
```
# build package using maven
mvn clean compile package

# run jar file
java -jar ./target/grep-1.0-SNAPSHOT.jar <regex expression> <path to folder> <path to output file>
```

2. To run using docker image:
```
# pull docker image from DockerHub
docker pull fanwer291/grep

# run docker container (--rm option deletes the container when it exits):
docker run --rm -v `pwd`/data:/data -v `pwd`/out:/out fanwer291/grep \ 
<regex expression> <path to folder> <path to output file>
```

## Implementation

The App is implemented using two approaches. In the first approach, starting from the root directory all sub-directories are iterated to get all the files. Further, all the files are iterated to get all the lines matching the regex.
The matching lines are then written to an output file.

In the second approach, the code logic is the same. To make the code more efficient, it is implemented using Java 8 Lambda Expressions and the Streams API.

### Pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            matchedLines.add(line)
writeToFile(matchedLines)
```

## Performance Issue
The first approach to the implementation does have an issue regarding
the memory used. When file sizes are significantly large and require memory that exceeds the memory allocated to the JVM this would cause the application to crash.

To fix this issue, we can use streams. Streams only keep the items in the memory that are being processed instead of storing the entire collection of items in the memory.
Streams enable us to define a pipeline of operations that can be parallelized.

## Test
To test the app, I prepared sample text files and saved them in the root directory and its sub-directories. The path to the root directory and a desired regex were provided as inputs. Finally, I checked the contents of the output file to make sure
the output lines contain the input regex. This test was run multiple times using various regex.

## Deployment
For easier distribution, the application is dockerized and uploaded to the Docker Hub. 

## Improvement
More features can be added by implementing the flags used with the grep command.
