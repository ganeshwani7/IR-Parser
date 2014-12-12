package parserIR;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ganeshwani on 11/27/14.
 */
public class Dictionary {
    private Map<String, List<Posting>> dictionary = new TreeMap<String, List<Posting>>();
    private static Dictionary classDictionary = null;

    private Dictionary(){

    }

    public static Dictionary getDictionary (){
        if( classDictionary == null)
            classDictionary = new Dictionary();
        return classDictionary;
    }

    private List<Posting> getPostingList( String term){
        return dictionary.get(term);
    }

    public Map<String, List<Posting>> getDictionaryMap(){
        return dictionary;
    }

    public void insertPostingList( String key, int docIdIn){
//        System.out.println("Inserting" + key + " with docId " + docIdIn);

        boolean isDocIdPresentInPosting = false;
        List<Posting> postingList = dictionary.get(key);
        if( postingList == null){
            postingList = new ArrayList<Posting>();
        }

        for (Posting posting : postingList) {
            //System.out.println(" Doc ID in :" + docIdIn + " posting doc ID " + posting.getDocId());
            if (posting.getDocId() == docIdIn) {
                isDocIdPresentInPosting = true;
               // System.out.println( "Setting term frequency as : " + (posting.getTermFrequency()+1));
                posting.setTermFrequency(posting.getTermFrequency() + 1);
                break;
            }
        }

        if( isDocIdPresentInPosting == false){
            postingList.add( new Posting( 1, docIdIn));
        }

        dictionary.put( key, postingList);
    }


    public void printDictionary () {
//        System.out.println( dictionary);
    }
}
