package telran.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeCommentsSeparation {record Streams(BufferedReader inputFilePath, PrintWriter codeOutputFilePath, PrintWriter commentsOutputFilePath) {

public static void main(String[] args) {
    //TODO - data from args[0] split to two files: args[1], args[2]
    //for sake of simplicity comments may be only on one line, like comments at this file
    // /* */ cannot be
    // code ...// comment .... cannot be
         //However // may be not only at beginning of line, like this
    //args[0] - path to file containing code and comments 
    //args[1] - path to file for placing only code
    //args[2] - path to file for placing only comments

    
        if (args.length != 3) {
            System.err.println("Usage: java CodeCommentsSeparation <inputFilePath> <codeOutputFilePath> <commentsOutputFilePath>");
            System.exit(1);
        }

        String inputFilePath = args[0];
        String codeOutputFilePath = args[1];
        String commentsOutputFilePath = args[2];

        try {
            separateCodeAndComments(inputFilePath, codeOutputFilePath, commentsOutputFilePath);
            System.out.println("Code and comments separated successfully!");
        } catch (IOException e) {
            System.err.println("Error while processing files: " + e.getMessage());
        }
    }

    static void separateCodeAndComments(String inputFilePath, String codeOutputFilePath, String commentsOutputFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter codeWriter = new PrintWriter(new FileWriter(codeOutputFilePath));
             PrintWriter commentsWriter = new PrintWriter(new FileWriter(commentsOutputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {                
                String cleanedLine = removeSingleLineComments(line);
                commentsWriter.println(line.replace(cleanedLine, "").trim());                
                codeWriter.println(cleanedLine.trim());
            }
        }
    }

    static String removeSingleLineComments(String line) {        
        Pattern pattern = Pattern.compile("//.*");
        Matcher matcher = pattern.matcher(line);
        return matcher.replaceAll("").trim();
    }
}
}
