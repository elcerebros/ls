package ls;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class LsTest {
    private void assertFileContent(String name, String expectedContent) {
        String content = "";

        try(FileReader file = new FileReader(name))
            {
                content = file.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }

        assertEquals(expectedContent, content);
    }

    @Test
    public void ls() throws IOException {
        Ls ls = new Ls(false, true, false, false);
        ls.ls("files", null);
    }
}
