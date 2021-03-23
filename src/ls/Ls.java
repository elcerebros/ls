package ls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

@SuppressWarnings("WeakerAccess")
public class Ls {
    private final Boolean longForm;

    private final Boolean humanReadableForm;

    private final Boolean reverseForm;

    private final String outputFileName;

    public Ls(Boolean longForm, Boolean humanReadableForm, Boolean reverseForm, String outputFileName) {
        this.longForm = longForm;
        this.humanReadableForm = humanReadableForm;
        this.reverseForm = reverseForm;
        this.outputFileName = outputFileName;
    }

    public String ls(String currentPath, String outputFileName) throws IOException {

        File obj = new File(currentPath);
        Path pathObj = obj.toPath();

        BasicFileAttributes attr = Files.readAttributes(pathObj, BasicFileAttributes.class);

        if (outputFileName == null) {
            if (obj.isDirectory()) {
                System.out.println(obj.getAbsolutePath());

                pathObj.forEach(System.out::println);
            } else {
                System.out.println("creationTime: " + attr.creationTime());
                System.out.println("lastAccessTime: " + attr.lastAccessTime());
                System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

                System.out.println("isDirectory: " + attr.isDirectory());
                System.out.println("isOther: " + attr.isOther());
                System.out.println("isRegularFile: " + attr.isRegularFile());
                System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
                System.out.println("size: " + attr.size());
            }
        }

        
        return "1";
    }
}
