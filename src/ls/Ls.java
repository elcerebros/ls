package ls;

import java.io.*;

@SuppressWarnings("WeakerAccess")
public class Ls {
    private final Boolean longForm;

    private final Boolean humanReadableForm;

    private final Boolean reverseForm;

    private final String outputFileName;

    private final String inputFileName;

    public Ls(Boolean longForm, Boolean humanReadableForm, Boolean reverseForm, String outputFileName,
              String inputFileName) {
        this.longForm = longForm;
        this.humanReadableForm = humanReadableForm;
        this.reverseForm = reverseForm;
        this.outputFileName = outputFileName;
        this.inputFileName = inputFileName;
    }

    public int ls(InputStream in, OutputStream out) {
        return 1;
    }

    public int ls(String inputName, String outputName) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputName)) {
            try (FileOutputStream outputStream = new FileOutputStream(outputName)) {
                return ls(inputStream, outputStream);
            }
        }
    }
}
