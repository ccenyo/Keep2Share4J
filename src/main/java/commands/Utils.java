package commands;

import java.io.File;
import java.util.*;

public class Utils {

    public static List<File> listFileTree(File dir, List<String> extensions) {
        List<File> fileTree = new ArrayList<>();
        if(dir==null||dir.listFiles()==null){
            return fileTree;
        }
        for (File entry : Objects.requireNonNull(dir.listFiles())) {
            if (entry.isFile() && extensions.stream().anyMatch(extension -> entry.getName().endsWith(extension))) {
                fileTree.add(entry);
            }
            else if(entry.isDirectory()) fileTree.addAll(listFileTree(entry, extensions));
        }
        return fileTree;
    }

    public static List<File> listFileTree(File dir) {
        List<File> fileTree = new ArrayList<>();
        if(dir==null||dir.listFiles()==null){
            return fileTree;
        }
        for (File entry : Objects.requireNonNull(dir.listFiles())) {
            if (entry.isFile()) {
                fileTree.add(entry);
            }
            else if(entry.isDirectory()) fileTree.addAll(listFileTree(entry));
        }
        return fileTree;
    }
}
