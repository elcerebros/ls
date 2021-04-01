package ls;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

@SuppressWarnings("WeakerAccess")
public class Ls {
    private final boolean longForm;

    private final boolean humanReadableForm;

    private final boolean reverseForm;

    private final String outputFileName;

    public Ls(boolean longForm, boolean humanReadableForm, boolean reverseForm, String outputFileName) {
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
            if (Objects.requireNonNull(objList).length == 0) { return; }
            Arrays.sort(objList);

            for (File file : objList) {
                fileFeature(result, file);
            }
        } else {
            fileFeature(result, obj);
        }

        if (reverseForm) { Collections.reverse(result); }
        if (outputFileName != null) { output(result); }
        else {
            result.forEach(System.out::println);
        }
    }

    private void fileFeature(List<String> result, File file) throws IOException {
        if (longForm) {
            BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            String canRead, canWrite, canExecute;

            if (humanReadableForm) {
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

            result.add(canRead + canWrite + canExecute + "  " + attr.lastModifiedTime() + "  "
                    + objSize(attr) + "  " + file.getName());
        } else {
            result.add(file.getName());
        }

    }

    private String objSize(BasicFileAttributes attr) {
        double size = attr.size();
        int i = 0;
        List<String> measurement = new ArrayList<>();
        measurement.add(" B");
        measurement.add(" KB");
        measurement.add(" MB");
        measurement.add(" GB");

        if (humanReadableForm) {
            while (size >= 1024) {
                size = BigDecimal.valueOf(size / 1024)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();
                i++;
            }
        }

        return size + measurement.get(i);
    }

    private void output(List<String> list) throws IOException {
        try (FileWriter writer = new FileWriter(outputFileName)) {
            list.forEach(element -> {
                try {
                    writer.write(element + " \n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
