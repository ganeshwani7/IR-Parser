package parser;

/**
 * Created by gwani on 11/11/2014.
 */
public class Parser {

    public static void main(String []args){

        if( args.length == 0){
            System.out.println("Please enter even number of files");
            System.exit(1);
        }
        else if( args.length %2 !=0){
            System.out.println( "Please enter the input and output files in order");
            System.exit(1);
        }
//        if(args.length != 1 ){
//            System.out.println(" Format: java <main-class> <filename>");
//            System.exit(1);
//        }

//        System.out.println("Arguments are as follows");
//        for( int i =0; i< args.length; i++){
//            System.out.println(args[i]);
//        }

        for( int i = 0; i< args.length/2; i++){
            String filename = args[i];
            FileProcessor fileProcessor = new FileProcessor(filename);
//            System.out.println( "Write filename is" + args[ i + (args.length/2) ]);
            FilterTags filterTags = new FilterTags(fileProcessor, args[ i + (args.length/2)]);
            filterTags.filterDocument();
        }
    }
}
