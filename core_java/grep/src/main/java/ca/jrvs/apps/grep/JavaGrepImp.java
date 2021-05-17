package ca.jrvs.apps.grep;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.*;
import java.util.regex.*;

public class JavaGrepImp implements JavaGrep{

    final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

    private String regex;
    private String rootPath;
    private String outFile;

    public static void main(String[] args) {
        if(args.length != 3){
            throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
        }

        //Use default logger config
        BasicConfigurator.configure();

        JavaGrepImp javaGrepImp = new JavaGrepImp();
        javaGrepImp.setRegex(args[0]);
        javaGrepImp.setRootPath(args[1]);
        javaGrepImp.setOutFile(args[2]);

        try{
            javaGrepImp.process();
        }catch (Exception ex){
            javaGrepImp.logger.error(ex.getMessage(), ex);
        }
    }

    /**
     * Top level search workflow
     * @throws IOException
     */
    @Override
    public void process() throws IOException {

        List<String> matchedLines = new ArrayList<String>();

        for(File file : listFiles(rootPath)){
            for(String line : readLines(file)){
                if(containsPattern(line)){
                    matchedLines.add(line);
                }
            }
        }
        writeToFile(matchedLines);
    }

    /**
     * Traverse a given directory and return all files
     * @param rootDir input directory
     * @return files under the rootDir
     */
    @Override
    public List<File> listFiles(String rootDir)  {
        List<File> allFilesInDir = new ArrayList<File>();
        File dir = new File(rootDir);
        for(File filename : dir.listFiles()){
            allFilesInDir.add(filename);
        }
        return allFilesInDir;
    }

    /**
     * Read a file and return all the lines
     *
     * Explain FileReader, BufferedReader, and character encoding
     *
     * @param inputFile file to be read
     * @return lines
     * @throws IllegalArgumentException if a given inputFile is not a file
     */
    @Override
    public List<String> readLines(File inputFile) throws IllegalArgumentException {

        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            List<String> allLines = new ArrayList<>();
            String line;

            while((line = reader.readLine()) != null){
                allLines.add(line);
            }

            reader.close();
            return allLines;

        }catch (FileNotFoundException e){
            throw new IllegalArgumentException("File does not exist");
        }catch (IOException e){
            throw new IllegalArgumentException("Input file is not a file");
        }
    }


    /**
     * check if a line contains the regex pattern (passed by user)
     * @param line input string
     * @return true if there is a match
     */
    @Override
    public boolean containsPattern(String line) {

        boolean isMatch = Pattern.matches(regex, line);
        return isMatch;
    }

    /**
     *Write lines to a file
     *
     * Explore: FileOutputStream, OutputStreamWriter, and BufferedWriter
     *
     * @param lines matched line
     * @throws IOException if write failed
     */
    @Override
    public void writeToFile(List<String> lines) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(rootPath + "/" + outFile));
        for (String line : lines) {
            writer.write(line + "\n");
        }
        writer.close();
    }

    @Override
    public String getRootPath() {

        return rootPath;
    }

    @Override
    public void setRootPath(String rootPath) {

        this.rootPath = rootPath;
    }

    @Override
    public String getRegex() {

        return regex;
    }

    @Override
    public void setRegex(String regex) {

        this.regex = regex;
    }

    @Override
    public String getOutFile() {

        return outFile;
    }

    @Override
    public void setOutFile(String outFile) {

        this.outFile = outFile;
    }
}
