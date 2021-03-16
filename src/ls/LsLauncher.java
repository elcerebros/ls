package ls;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

public class LsLauncher {

    @Option(name = "-l", metaVar = "Long", usage = "Long form")
    private Boolean longForm;

    @Option(name = "-h", metaVar = "HumanReadable", usage = "Human-readable form")
    private Boolean humanReadableForm;

    @Option(name = "-r", metaVar = "Reverse", usage = "Reverse form")
    private Boolean reverseForm;

    @Option(name = "-o", metaVar = "OutputName", usage = "Output file name")
    private String outputFileName;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String pathIn;

    public static void main(String[] args) {
        new LsLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Ls.jar -l -h -r -o output.file path");
            parser.printUsage(System.err);
            return;
        }

        Ls path = new Ls(longForm, humanReadableForm, reverseForm, outputFileName, pathIn);
        try {
            int result = path.ls(pathIn, outputFileName);
            System.out.println("Total of " + result + " symbols recoded");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }


    }
}
