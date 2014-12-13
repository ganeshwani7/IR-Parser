package parserIR;

/**
 * Created by ganeshwani on 11/27/14.
 */
public class Posting {
    private int termFrequency;
    private int docId;

    public Posting() {
        termFrequency = 0;
        docId = 0;
    }

    public Posting( int termFrequencyIn, int docIdIn){
        termFrequency = termFrequencyIn;
        docId = docIdIn;
    }

    public int getTermFrequency() {
        return termFrequency;
    }

    public void setTermFrequency(int termFrequency) {
        this.termFrequency = termFrequency;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    @Override
    public boolean equals(Object object){
        return  this.getDocId() == ((Posting)object).getDocId();
    }
}
