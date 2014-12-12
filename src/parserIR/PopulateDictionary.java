package parserIR;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ganeshwani on 12/11/14.
 */
public class PopulateDictionary {
    private final String postingFileName = "Postings.txt";
    private final String dictionaryFileName= "Dictionary.txt";
    private final String docsTableFileName = "DocsTable.txt";

    private Dictionary dictionary = null;
    private Map<Integer,String> docTable = new TreeMap<Integer, String>();

    public PopulateDictionary( ){
        dictionary = Dictionary.getDictionary();
    }

    public void populate(){
        FileProcessor postingFileProcessor = new FileProcessor( postingFileName);
        FileProcessor dictionaryFileProcessor = new FileProcessor( dictionaryFileName);

        int currentOffset = 0;
        while(true){
            String line = dictionaryFileProcessor.readLineFromFile();
            if( line == null)
                break;
            String [] tokens = line.split(" ");
            String key = tokens[0];
//            int iterations = Integer.parseInt( tokens[2]) - currentOffset;
 //           currentOffset = Integer.parseInt( tokens[2]);

            int iterations = Integer.parseInt(tokens[1]);
            if( key.equals("thriller")){
                System.out.println("Yes thriller!!");
            }
            int docId = 0;
            while( iterations-- != 0){
                String postingLine = postingFileProcessor.readLineFromFile();
                if( postingLine == null)
                    break;
                String [] postingTokens = postingLine.split(" ");
                try {
                    docId = Integer.parseInt(postingTokens[0]);
                }catch( Exception e){
                    e.printStackTrace();
                }
                dictionary.insertPostingList( key, docId);
            }
        }
        populateDocTable();
//        System.out.println("I am done");
    }

    private void populateDocTable( ){
        FileProcessor docsTableFileProcessor = new FileProcessor( docsTableFileName);
        while(true){
            String line = docsTableFileProcessor.readLineFromFile();
            if(line == null){
                break;
            }
            String [] tokens = line.split(" ", 2);
            docTable.put( Integer.parseInt(tokens[0]), tokens[1]);
        }
//        System.out.println("HI");
    }

    public Dictionary getDictionary(){
        return Dictionary.getDictionary();
    }

    public Map<Integer, String> getDocTable(){
        return docTable;
    }
}
