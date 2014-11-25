package main.java.IRParser;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gwani on 11/11/2014.
 */
public class FilterTags {
    FileProcessor fileProcessor;

    List<String> tokens = new ArrayList();

    List<String> startTags = new ArrayList<String>();
    List<String> endTags = new ArrayList<String>();

   public FilterTags( FileProcessor inFileProcessor){
       fileProcessor = inFileProcessor;
        initiateFilters();
   }

   private void initiateFilters(){

   }

   public void filterDocument(){
       String line = "";
       String []splitString;
       StringBuilder sb = new StringBuilder();
       while( true){
           line = fileProcessor.readLineFromFile();
           if( line == null)
               break;
           sb.append(line);
//           splitString = line.split(" ");
//           for( String temp : splitString){
//               tokens.add(temp);
//           }

       }
       System.out.println( sb.toString().replaceAll("[[:ascii:]]*<body","<"));
       System.out.println( sb.toString().replaceAll("\\<.*?>",""));
//       removeTags( tokens);
//       System.out.println(tokens.toString());
   }

   private void removeTags( List<String> tokens){
       String tag, token;
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

//       Pattern patttern = Pattern.compile("<a[[:ascii:]]*>");
//       Matcher matcher = null;
        while( listIterator.hasNext()){
            token = listIterator.next();
            if( token.equals("")){
                listIterator.remove();
                continue;
            }
//            else if( token.matches( "[\\\\u0000-\\\\u007F]*<a[\\\\u0000-\\\\u007F]*") ) {
            else {
                for( String tempTag : startTags) {
                    if( token.contains( tempTag)){
                        String tempToken = token.replaceAll(tempTag, "");
                        if (tempToken.equals(""))
                            listIterator.remove();
                        else
                            listIterator.set(tempToken);

                        while (listIterator.hasNext()) {
                            token = listIterator.next();
                            if (token.contains(">")) {
                                Pattern patttern = Pattern.compile("\\p{ASCII}*>");
                                Matcher matcher = patttern.matcher(token);
                                tempToken = matcher.replaceAll("");
                                if (tempToken == "")
                                    listIterator.remove();
                                else
                                    listIterator.set(tempToken);
//                                    listIterator.`
                                break;
                            } else {
                                listIterator.remove();
                            }
                        }
                        break;
                    }
                }
            }
        }
   }
}
