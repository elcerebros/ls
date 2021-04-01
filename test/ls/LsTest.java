package ls;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

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
        Ls example = new Ls(false, true, false,
                "files/results/result1.txt");
        example.ls("files");
        assertFileContent("files/results/result1.txt",
                """
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
    public void ls2() throws IOException {
        Ls example = new Ls(true, true, true,
                "files/results/result2.txt");
        example.ls("files/xtDWZ8C57xc.jpg");
        assertFileContent("files/results/result2.txt",
                """ 
                        rwx  2021-03-14T09:45:04.4658571Z  490.846 KB  xtDWZ8C57xc.jpg\s
                        """);
    }

    @Test
    public void ls3() throws IOException {
        Ls example = new Ls(true, true, false,
                "files/results/result3.txt");
        example.ls("files/documents");
        assertFileContent("files/results/result3.txt",
                """
                        rwx  2021-02-12T11:12:29.7300476Z  4.181 MB  OneDrive_1_12.02.2021.zip\s
                        rwx  2021-03-11T15:43:25.0394002Z  856.359 KB  Физика_дз_5.pdf\s
                        """);
    }

    @Test
    public void ls4() throws IOException {
        Ls example = new Ls(true, true, false,
                "files/results/result4.txt");
        example.ls("files/empty");
        assertFileContent("files/results/result4.txt",
                """
                        """);
    }

    @Test
    public void ls5() throws IOException {
        Ls example = new Ls(true, false, false,
                "files/results/result5.txt");
        example.ls("files");
        assertFileContent("files/results/result5.txt",
                """
                        111  2021-03-19T18:15:13.1818124Z  0.0 B  documents\s
                        111  2021-03-14T09:45:00.3790231Z  133300.0 B  EL5kMeW3BFE.jpg\s
                        111  2021-03-25T11:55:43.5479263Z  0.0 B  empty\s
                        111  2021-04-01T13:39:36.1460032Z  0.0 B  results\s
                        111  2021-03-19T18:08:53.4509003Z  1773.0 B  text1.txt\s
                        111  2021-03-19T18:09:48.7103843Z  4318.0 B  text2.txt\s
                        111  2021-03-19T18:10:58.3406925Z  1492.0 B  text3.txt\s
                        111  2021-03-14T09:45:04.4658571Z  502626.0 B  xtDWZ8C57xc.jpg\s        
                        """);
    }
}
