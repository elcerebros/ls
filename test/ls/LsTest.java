package ls;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LsTest {
    private void assertFileContent(String name, String expectedContent) {
        StringBuilder content = new StringBuilder();
        File file = new File(name);
        try (FileReader reader = new FileReader(file)) {
            int x;
            while ((x = reader.read()) != -1) {
                content.append((char) x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(expectedContent, content.toString());
    }

    @Test
    public void ls1() throws IOException {
        Ls example = new Ls(null, null, null, null);
        example.ls("files");
    }

    @Test
    public void ls2() throws IOException {
        Ls example = new Ls(true, null, null, null);
        example.ls("files");
    }

    @Test
    public void ls3() throws IOException {
        Ls example = new Ls(true, true, null, null);
        example.ls("files");
    }

    @Test
    public void ls4() throws IOException {
        Ls example = new Ls(true, true, true, null);
        example.ls("files");
    }

    @Test
    public void ls5() throws IOException {
        Ls example = new Ls(null, null, null, null);
        example.ls("files/text1.txt");
    }

    @Test
    public void ls6() {
        Ls example = new Ls(null, null, null, null);
        assertThrows(AssertionError.class, () -> example.ls("files/empty"));
    }

    @Test
    public void ls7() throws IOException {
        Ls example = new Ls(null, true, null,
                "files/results/result1.txt");
        example.ls("files");
        assertFileContent("files/results/result1.txt",
                """
                        C:\\Users\\wings\\IdeaProjects\\ls\\files\s
                          
                        documents\s
                        EL5kMeW3BFE.jpg\s
                        empty\s
                        results\s
                        text1.txt\s
                        text2.txt\s
                        text3.txt\s
                        xtDWZ8C57xc.jpg\s          
                        """);
    }

    @Test
    public void ls8() throws IOException {
        Ls example = new Ls(true, true, true,
                "files/results/result2.txt");
        example.ls("files/xtDWZ8C57xc.jpg");
        assertFileContent("files/results/result2.txt",
                """
                        C:\\Users\\wings\\IdeaProjects\\ls\\files\\xtDWZ8C57xc.jpg\s
                          
                        rwx 2021-03-14T09:45:04.4658571Z 490.846 xtDWZ8C57xc.jpg\s
                        """);
    }

    @Test
    public void ls9() throws IOException {
        Ls example = new Ls(true, true, null,
                "files/results/result3.txt");
        example.ls("files/documents");
        assertFileContent("files/results/result3.txt",
                """
                        C:\\Users\\wings\\IdeaProjects\\ls\\files\\documents\s

                        rwx 2021-02-12T11:12:29.7300476Z 4281.452 OneDrive_1_12.02.2021.zip\s
                        rwx 2021-03-11T15:43:25.0394002Z 856.359 Физика_дз_5.pdf\s
                        """);
    }
}
