package parserIR;

import javafx.geometry.Pos;

import java.util.*;

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
        List<Posting> result = new LinkedList<Posting>();
        List<Posting> firstDocList = dictionary.getDictionaryMap().get(queryTerms[0].toLowerCase());

        for (Posting posting : firstDocList) {
            result.add(posting);
        }


        for( int i = 1; i< queryTerms.length; i++) {
            String queryTerm = queryTerms[i];
            List<Posting> docList = dictionary.getDictionaryMap().get(queryTerm);

            if( docList == null)
                break;
            int resultIndex = 0, index = 0;
            while( index < docList.size() && resultIndex < result.size()){
                if( result.get(resultIndex).equals( docList.get(index)) ){
                    resultIndex++;
                    index++;
                }
                else if( result.get(resultIndex).getDocId() < docList.get(index).getDocId()){
                    result.remove(resultIndex);
                  //  resultIndex++;
                }
                else{
                    index++;
                }
            }
            while( resultIndex < result.size())
                result.remove(resultIndex);
        }
        printPostingList(result);
    }

    public void handleOrQuery(String query){
        String []queryTerms = query.split(" ");
        List<Posting> result = new LinkedList<Posting>();
        List<Posting> firstDocList = dictionary.getDictionaryMap().get(queryTerms[0]);

        for (Posting posting : firstDocList) {
            result.add(posting);
        }


        for( int i = 1; i< queryTerms.length; i++) {
            String queryTerm = queryTerms[i];
            List<Posting> docList = dictionary.getDictionaryMap().get(queryTerm);

            if( docList == null)
                break;
            int resultIndex = 0, index = 0;
            while( index < docList.size() && resultIndex < result.size()){
                if( result.get(resultIndex).equals( docList.get(index)) ){
                    resultIndex++;
                    index++;
                }
                else if( result.get(resultIndex).getDocId() < docList.get(index).getDocId()){
                    while( resultIndex < result.size() &&
                        result.get(resultIndex).getDocId() < docList.get(index).getDocId()){
                            resultIndex++;
                    }
                    if( resultIndex == result.size() ||
                            result.get(resultIndex).equals( docList.get(index)))
                        continue;
                    result.add(resultIndex, docList.get(index));
                    resultIndex++;
                    index++;
                }
                else{
                    result.add( resultIndex, docList.get(index));
                    index++;
                    resultIndex++;
                }
            }
//            while( index < docList.size())
//                result.add(docList.get(index++));
        }
//        System.out.println("Hey, merged or query");
//        System.out.println(result);
        printPostingList(result);
    }

    public void handleNormalQuery(String query){
        List<Posting> docList = dictionary.getDictionaryMap().get(query);
        for(Posting posting: docList){
            System.out.println( posting.getDocId());
        }
    }

    public void printPostingList( List<Posting> postingList){
        for( Posting posting : postingList){
            System.out.println( posting.getDocId() + " "+ docTable.get(posting.getDocId()));
        }
        FileWriter fileWriter = new FileWriter();
        fileWriter.writeResults( postingList, docTable);
    }
}
