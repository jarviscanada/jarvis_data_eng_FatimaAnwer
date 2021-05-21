#Introduction
The Java Grep App simulates the linux grep command that searches for a specified text pattern in an input file.
Similarly, the App searches for the pattern (regex) in the files of the input root directory and its sub-directories recursively.
The lines that contain the pattern are saved to the specified output file. 

The App is implemented 
using Java I/O, such as FileReader, BufferedReader, FileWriter, and BufferedWriter, Functional Interfaces and Lambda Expressions.
To make the code efficient and light-weight, the listing and reading of files is reimplemented using Java 8 Stream API.

The project is built in IntelliJ using Maven. The App is also Dockerized and pushed to Docker Hub.

#Quick Start
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

#Implementation


##Pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootDir)
    for line in readLines(file)
        if containsPattern(line)
            matchedLines.add(line)
writeToFile(matchedLines)
```

# Test


