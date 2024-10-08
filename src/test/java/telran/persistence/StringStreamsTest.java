package telran.persistence;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
public class StringStreamsTest {
    static final String PRINT_STREAM_FILE = "printStreamFile.txt";
    static final String PRINT_WRITER_FILE = "printWriterFile.txt";
    @Test
   // @Disabled
    void printStreamTest() throws Exception{
        PrintStream printStream = new PrintStream(PRINT_STREAM_FILE);
        printStream.println("HELLO");
        printStream.close();
    }
    @Test
    //@Disabled
    void printWriterTest() throws Exception{
        PrintWriter printWriter = new PrintWriter(PRINT_WRITER_FILE);
        printWriter.println("HELLO");
        printWriter.close();
    }
    @Test
    //@Disabled
    void bufferedReaderTest() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(PRINT_WRITER_FILE));
        assertEquals("HELLO",reader.readLine());
        reader.close();
    }
    @Test
    void printingDirectoryTest(){
        printDirectoryContent("\\",3);
    }
    /**
     * 
     * @param path -  path to a directory
     * @param depth -  number of been walked levels
     */
    
        //TODO
        //dir1
          //dir11
            //file
            //dir111
          //dir12
        //Consider class Path
        //Consider class Files
        //Consider method https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Files.html#walkFileTree(java.nio.file.Path,java.util.Set,int,java.nio.file.FileVisitor)
    
    
           
    

    static void printDirectoryContent(String path, int depth) {
        try {
            Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    
                    System.out.println(getIndent(depth) + file.getFileName());
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    
                    System.err.println("Error visiting file: " + file);
                    return FileVisitResult.CONTINUE;
                }

                private String getIndent(int depth) {
                    
                    StringBuilder indent = new StringBuilder();
                    for (int i = 0; i < depth; i++) {
                        indent.append("  "); 
                    }
                    return indent.toString();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}    
    

