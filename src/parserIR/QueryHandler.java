package parserIR;

import javafx.geometry.Pos;

import java.util.List;
import java.util.Map;

/**
 * Created by ganeshwani on 12/11/14.
 */
public class QueryHandler {

    Dictionary dictionary = null;
    Map<Integer, String> docTable = null;

    public void initialize( Dictionary dictionaryIn, Map<Integer, String > docTableIn){
        this.dictionary = dictionaryIn;
        this.docTable = docTableIn;
    }

    public void handleAndQuery(String query){
        String []queryTerms = query.split(" ");
        for( String queryTerm : queryTerms) {
            List<Posting> docList = dictionary.getDictionaryMap().get(queryTerm);
            for (Posting posting : docList) {
                System.out.println(posting.getDocId());
            }
        }
    }

    public void handleOrQuery(String query){

    }

    public void handleNormalQuery(String query){
        List<Posting> docList = dictionary.getDictionaryMap().get(query);
        for(Posting posting: docList){
            System.out.println( posting.getDocId());
        }
    }
}
