package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    public static void main(String[] args) {
        if (args.length != 3) {

            BasicConfigurator.configure();

            JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
            javaGrepLambdaImp.setRegex(args[0]);
            javaGrepLambdaImp.setRootPath(args[1]);
            javaGrepLambdaImp.setOutFile(args[2]);

            try {
                javaGrepLambdaImp.process();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<File> listFiles(String rootDir) {

        try{
            List<File> listOfFiles;
            Stream<Path> filePaths = Files.walk(Paths.get(rootDir));
            listOfFiles = filePaths.filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            return listOfFiles;

        } catch (IOException e) {
            logger.error("No file found", e);
        }
        return null;
    }

    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException {

        try{
            Path path = Paths.get(inputFile.getAbsolutePath());
            return Files.lines(path).collect(Collectors.toList());
        }catch (IOException e){
            logger.error("Lines from the file cannot be read", e);
        }
        return null;
    }
}

