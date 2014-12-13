package parserIR;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by gwani on 11/11/2014.
 */
public class Parser {

    private static QueryHandler queryHandler = new QueryHandler();
    public static void main(String []args){

        if( args.length != 1){
            System.out.println("Please enter folder name");
            System.out.println(args[0]);
            System.exit(1);
        }
        /*else if( args.length %2 !=0){
            System.out.println( "Please enter the input and output files in order");
            System.exit(1);
        }
//        if(args.length != 1 ){
//            System.out.println(" Fo   rmat: java <main-class> <filename>");
//            System.exit(1);
//        }

//        System.out.println("Arguments are as follows");
//        for( int i =0; i< args.length; i++){
//            System.out.println(args[i]);
//        }
*/
//        DirectoryParser directoryParser = new DirectoryParser(args[0]);
  //      List<?> fileNames = directoryParser.getFileList();

  //      Dictionary dictionary = Dictionary.getDictionary();
//        Map<Integer, String> docTable = new TreeMap<Integer, String>();
//
//        int docId = 1;
//        for(Object fileName : fileNames){
//            String filename = "reviews/"+ fileName;
//            FileProcessor fileProcessor = new FileProcessor(filename);
////            System.out.println( "Write filename is" + args[ i + (args.length/2) ]);
////            FilterTags filterTags = new FilterTags(fileProcessor, args[ i + (args.length/2)]);
//            FilterTags filterTags = new FilterTags(fileProcessor, filename);
//            filterTags.filterDocument( dictionary, docId, docTable);
//
//            ++docId;
//        }
        PopulateDictionary populateDictionary = new PopulateDictionary();
        populateDictionary.populate();
        queryHandler.initialize( populateDictionary.getDictionary(), populateDictionary.getDocTable());
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Please enter your query : ");
            String input = scanner.nextLine();
            if( input.toLowerCase().equals("exit") ){
                System.exit(1);
            }
            handleQuery(input);
        }

    //    dictionary.printDictionary();
  //      FileWriter fileWriter = new FileWriter();
//        fileWriter.dumpEverything( docTable, dictionary);
//        for( int i = 0; i< args.length/2; i++){
//            String filename = args[i];
//            FileProcessor fileProcessor = new FileProcessor(filename);
////            System.out.println( "Write filename is" + args[ i + (args.length/2) ]);
//            FilterTags filterTags = new FilterTags(fileProcessor, args[ i + (args.length/2)]);
//            filterTags.filterDocument();
//        }
    }

    public static void handleQuery(String input){

        if( input == null)
            return;
        String []queryType = input.split(" ", 2);
        if( queryType[0].toLowerCase().equals("or")){
            queryHandler.handleOrQuery(queryType[1]);
        }else if( queryType[0].toLowerCase().equals("and")){
            queryHandler.handleAndQuery(queryType[1]);
        }
        else{
            queryHandler.handleNormalQuery(queryType[0]);
        }
    }
}
