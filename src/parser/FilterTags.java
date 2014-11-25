package parser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by gwani on 11/11/2014.
 */
public class FilterTags {
    FileProcessor fileProcessor;
    String writeFile = "";
    List<String> tokens = new ArrayList();

    List<String> startTags = new ArrayList<String>();
    List<String> endTags = new ArrayList<String>();

   public FilterTags(FileProcessor inFileProcessor, String writeFile){
       fileProcessor = inFileProcessor;
       this.writeFile = writeFile;
   }

   public void filterDocument(){
       String line = "";
       String []splitString;
       String totalString = "";
       StringBuilder sb = new StringBuilder();
       while( true){
           line = fileProcessor.readLineFromFile();
           if( line == null)
               break;
//           sb.append(line);
           splitString = line.split(" ");
           for( String temp : splitString){
               tokens.add(temp);
           }
       }

       removeTags( tokens);
       totalString = tokens.toString();
//       System.out.println( sb.toString().replaceAll(" ,","").replaceAll("[a-zA-Z]*[-]+[^a-z]*",""));

       totalString = totalString.replaceAll(" ,","");
       totalString = totalString.replaceAll("\\<.*?>","");
       totalString = totalString.replaceAll(",","");
       totalString = totalString.replaceAll("\\[","");
       totalString = totalString.replaceAll("\\]","");
       totalString = totalString.replaceAll("\"","");
       totalString = totalString.replaceAll("\\*","");
       totalString = totalString.replaceAll(":"," ");
       totalString = totalString.replaceAll("\\("," ");
       totalString = totalString.replaceAll("\\)"," ");
       totalString = totalString.replaceAll("!"," ");
       totalString = totalString.replaceAll("/"," ");
       totalString = totalString.replaceAll("\\?","");

       StringBuilder stringBuilder = new StringBuilder( totalString);

       for( int i =0; i< stringBuilder.length(); i++){
           char tempChar = stringBuilder.charAt(i);
           if( i+1 == stringBuilder.length()) {
               if( tempChar == '-' && tempChar == '-')
                   stringBuilder.setCharAt(i,' ');
               break;
           }
           char nextChar = stringBuilder.charAt(i + 1);
           if( tempChar =='-' && !Character.isDigit(nextChar) ){
               stringBuilder.setCharAt(i,' ');
           }
       }

       removeDots( stringBuilder);
   }

    private void removeDots(StringBuilder stringBuilder) {
        String[] tokens = stringBuilder.toString().split(" ");
        List<String> listOfTokens = new ArrayList<String>();

        for( String token: tokens){
            listOfTokens.add( token);
        }
        ListIterator<String>iterator = listOfTokens.listIterator();

        while( iterator.hasNext()) {
            String token = iterator.next();
            if (token.matches(" *\\. *") || token.matches(" *")) {
                iterator.remove();
            } else if (token.length() == 2 && Character.isDigit(token.charAt(0))) {
                iterator.set(token.substring(0, 1).toLowerCase());
            } else if (token.length() > 2 && token.charAt(token.length() - 1) == '.') {
                boolean abbr = isAbbrevation(token);
                if (!abbr) {
                    int i = token.length() - 1;
                    while (i > 0) {
                        if (token.charAt(i) == '.' ) {
                            --i;
                        } else break;
                    }
                    iterator.set(token.substring(0, i + 1).toLowerCase());
                }
                else {
                    iterator.set(token.toLowerCase());
                }
                } else {
                    iterator.set(token.toLowerCase());
                }
            }
//        System.out.println(listOfTokens);
            Set<String> setOfTokens = new TreeSet<String>();
//        System.out.println("List of tokens are");
//        System.out.println(listOfTokens);
            for (String token : listOfTokens) {
                setOfTokens.add(token);
            }
//        System.out.println( setOfTokens);
//
//        System.out.println( "Length of original tokens : " + listOfTokens.size());
//        System.out.println( "Length of Set tokens : " + setOfTokens.size());

            writeResults(setOfTokens);

    }

    private boolean isAbbrevation(String token) {
        int len=token.length();
        len=len-1;
        if(token.charAt(len)=='.' && token.charAt(len-2)=='.')
        {
            if(Character.isDigit(token.charAt(len-1)))
            {
                return false;
            }
            else
                return true;
        }
        return false;
    }

    private void writeResults(Set<String> setOfTokens) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(writeFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
//        System.out.println( "Writing tokens");
        for (String token : setOfTokens){
//            System.out.println(token);
            writer.print(token + "\n");
        }
        writer.close();
    }

    private void removeTags( List<String> tokens){
       String token;
       int  i = 0;

       // This loop removes all the tags and texts till <body>

       ListIterator<String> listIterator = tokens.listIterator();
        while( listIterator.hasNext()){
            token = listIterator.next();
            if( token.toLowerCase().contains("<body")) {
                listIterator.remove();
                while( listIterator.hasNext()){
                    token = listIterator.next();
                    if( token.toLowerCase().contains(">")) {
                        listIterator.remove();
                        break;
                    }
                    listIterator.remove();
                }
                break;
            }
            listIterator.remove();
        }
   }
}
