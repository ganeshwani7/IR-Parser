package parserIR;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ganeshwani on 11/27/14.
 */
public class DirectoryParser {
    private String folderName = "";

    public DirectoryParser( String folderNameIn){
        folderName = folderNameIn;
    }

    public List<?> getFileList() {
        List<String> fileNames = new ArrayList<String>();
        final File folder = new File( folderName);
        listFilesForFolder( folder, fileNames);
        return fileNames;
    }

    private void listFilesForFolder( final File folder, List<String> fileNames){
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry, fileNames);
            } else {
                // Add the file names in List
                fileNames.add( fileEntry.getName());
                System.out.println(fileEntry.getName());
            }
        }
    }
}
