package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by gwani on 11/11/2014.
 */
public class FileProcessor {
    BufferedReader in;

    public FileProcessor( String filename){
        try {
            in = new BufferedReader( new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String readLineFromFile(){
        String line = "";

        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}
