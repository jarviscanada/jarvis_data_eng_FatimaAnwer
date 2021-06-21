package ca.jrvs.apps.practice;
import java.util.regex.*;

public class RegexExcImp implements RegexExc{

    public boolean matchJpeg(String filename) {

        String filename_lowercase = filename.toLowerCase();
        String regex = "^.*\\.(jpg|jpeg)$";

        boolean isMatch = Pattern.matches(regex, filename_lowercase);

        return isMatch;
    }

    public boolean matchIp(String ip){

        String regex = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";

        boolean isIPMatch = Pattern.matches(regex, ip);

        return isIPMatch;
    }

    public boolean isEmptyLine(String line){

        String regex = "^\\s*$";

        boolean isEmptyLineMatch = Pattern.matches(regex, line);

        return isEmptyLineMatch;
    }
}
