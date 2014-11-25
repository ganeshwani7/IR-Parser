package main.java.IRParser;

/**
 * Created by gwani on 11/11/2014.
 */
public class Parser {

    public static void main(String []args){
        if(args.length != 1 ){
            System.out.println(" Format: java <jar><filename>");
            System.exit(1);
        }

        String filename = "";

        filename = args[0];
        FileProcessor fileProcessor = new FileProcessor(filename);
        FilterTags filterTags = new FilterTags(fileProcessor);
        filterTags.filterDocument();
    }
}
