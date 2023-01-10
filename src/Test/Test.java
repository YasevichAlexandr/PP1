package Tests;

import Write+Read.archivers.RarArchives;
import Write+Read.FilesTypes.json;
import Write+Read.FilesTypes.txt;
import Write+Read.FilesTypes.xml;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Arrays;

class Tests {

    private static boolean isEqual(Path firstFile, Path secondFile)
    {
        try {
            long size = Files.size(firstFile);
            if (size != Files.size(secondFile)) {
                return false;
            }

            if (size < 2048)
            {
                return Arrays.equals(Files.readAllBytes(firstFile),
                        Files.readAllBytes(secondFile));
            }
            try (BufferedReader bf1 = Files.newBufferedReader(firstFile);
                 BufferedReader bf2 = Files.newBufferedReader(secondFile)) {

                String line;
                while ((line = bf1.readLine()) != null)
                {
                    if (line != bf2.readLine()) {
                        return false;
                    }
                }
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Test
    public void whenEncryptingIntoFile_andDecryptingFileAgain_thenOriginalStringIsReturned() throws GeneralSecurityException, IOException {
        Key key = new DecryptEncrypt().getKey("12345");
        new File("result.txt").delete();
        File firstFile=new File("input.txt");
        File secondFile=new File("res.txt");
        assertEquals(true, isEqual(firstFile.toPath(), secondFile.toPath()));
        new File("res.txt").delete();
    }

    @Test
    public void whenFileIsArhivateIntoZip_andUnarchivateFile_thenOriginalTextIsReturned() throws IOException {
        String originText="Hello";
        assertEquals(originText, text);
    }
    @Test
    public void WritingReadingjson() throws IOException, GeneralSecurityException, ParserConfigurationException, SAXException, XMLStreamException, TransformerException {
        String origintest="hello .";
        IFileReadingWriting read = new json();
        assertEquals(origintest, text);
        new File("D:result.json").delete();
    }
    @Test
    public void WritingReadingxml() throws IOException, GeneralSecurityException, ParserConfigurationException, SAXException, XMLStreamException, TransformerException {
        String origintest="hello .";
        IFileReadingWriting read = new XmlFile();
        assertEquals(origintest, text);
        new File("D:result.xml").delete();
    }
    @Test
    public void WritingReadingtxt() throws IOException, GeneralSecurityException, ParserConfigurationException, SAXException, XMLStreamException, TransformerException {
        String origintest="hello .";
        IFileReadingWriting read = new TxtFile();
        assertEquals(origintest, text);
        new File("D:result.txt").delete();
    }
}
