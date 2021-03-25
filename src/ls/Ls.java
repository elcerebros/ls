package ls;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public void ls(String currentPath) throws IOException {
        List<String> result = new ArrayList<>();
        File obj = new File(currentPath);

        if (obj.isDirectory()) {
            File[] objList = obj.listFiles();
            assert Objects.requireNonNull(objList).length != 0;

            for (File file : objList) {
                fileFeature(result, file);
            }
        } else {
            fileFeature(result, obj);
        }

        if (reverseForm != null) { Collections.reverse(result); }
        if (outputFileName != null) { output(result, obj); }
        else {
            System.out.println(obj.getAbsolutePath());
            System.out.println();
            result.forEach(System.out::println);
        }
    }

    private void fileFeature(List<String> result, File file) throws IOException {
        if (longForm != null) {
            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            String canRead, canWrite, canExecute;

            if (humanReadableForm != null) {
                if (file.canRead()) { canRead = "r"; }
                else { canRead = "-"; }
                if (file.canWrite()) { canWrite = "w"; }
                else { canWrite = "-"; }
                if (file.canExecute()) { canExecute = "x"; }
                else { canExecute = "-"; }
            } else {
                if (file.canRead()) { canRead = "1"; }
                else { canRead = "0"; }
                if (file.canWrite()) { canWrite = "1"; }
                else { canWrite = "0"; }
                if (file.canExecute()) { canExecute = "1"; }
                else { canExecute = "0"; }
            }

            result.add(canRead + canWrite + canExecute + " " + attr.lastModifiedTime() + " "
                    + objSize(attr) + " " + file.getName());
        } else {
            result.add(file.getName());
        }

    }

    private double objSize(BasicFileAttributes attr) {
        double size = attr.size();

        if (humanReadableForm != null) {
            size = BigDecimal.valueOf(size / 1024)
                    .setScale(3, RoundingMode.HALF_UP)
                    .doubleValue();
        }

        return size;
    }

    private void output(List<String> list, File obj) {
        try (FileWriter writer = new FileWriter(outputFileName)) {
            writer.write(obj.getAbsolutePath() + " \n\n");
            list.forEach(element -> {
                try {
                    writer.write(element + " \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
