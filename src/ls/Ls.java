package ls;

import java.io.*;

@SuppressWarnings("WeakerAccess")
public class Ls {
    private final Boolean longForm;

    private final Boolean humanReadableForm;

    private final Boolean reverseForm;

    public Ls(Boolean longForm, Boolean humanReadableForm, Boolean reverseForm) {
        this.longForm = longForm;
        this.humanReadableForm = humanReadableForm;
        this.reverseForm = reverseForm;
    }

    public String ls(String pathIn, String outputFileName) {
        File file = new File(pathIn);

        
        return "";
    }
}
