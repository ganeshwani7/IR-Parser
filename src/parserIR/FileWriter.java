package parserIR;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by ganeshwani on 11/27/14.
 */
public class FileWriter {
    String dictionaryFile = "Dictionary.txt";
    String postingFile = "Postings.txt";
    String docTableFile = "DocsTable.txt";

    FileWriter(){}

    private void writeDocTable(Map<Integer, String> docTable){
    PrintWriter writer = null;
        try {
            writer = new PrintWriter(docTableFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println( "Writing doc table tokens");
        Iterator iterator = docTable.entrySet().iterator();
        while ( iterator.hasNext()){
            Map.Entry pairs = (Map.Entry)iterator.next();
            writer.print( pairs.getKey() + " "+ pairs.getValue() + "\n");
        }
        writer.close();
    }

    private void writeDictionaryAndPostingList(Dictionary dictionary){
        Map<String, List<Posting>> dictionaryMap = dictionary.getDictionaryMap();

        PrintWriter dictionaryWriter = null, postingWriter = null;
        try {
            dictionaryWriter = new PrintWriter(dictionaryFile);
            postingWriter = new PrintWriter(postingFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println( "Writing in Dictionary file");
        Iterator iterator = dictionaryMap.entrySet().iterator();
        int offset = 0;
        while ( iterator.hasNext()){

            Map.Entry pairs = (Map.Entry)iterator.next();

            List<Posting> postingList = (List<Posting>)pairs.getValue();
            dictionaryWriter.print( pairs.getKey() + " " + postingList.size() + " " +  offset + "\n");

            for( Posting posting : postingList){
                postingWriter.write( posting.getDocId() + " " + posting.getTermFrequency() + "\n" );
            }

            offset += postingList.size();
        }
        dictionaryWriter.close();
        postingWriter.close();
    }

    private void writePostingList(){

    }

    public void dumpEverything(Map<Integer, String> docTable, Dictionary dictionary){
        writeDocTable(docTable);
        writeDictionaryAndPostingList(dictionary);
        writePostingList();
    }
}
