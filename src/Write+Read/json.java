import java.io.*;
import java.security.GeneralSecurityException;
import java.security.Key;

public class JsonFile implements IFileReadingWriting {
    @Override
    public String reading(String FileName, boolean a) throws GeneralSecurityException, IOException {
        if (a == true)
        {
            Key key = new DecryptEncrypt().getKey("12345");
            new DecryptEncrypt().encrypt(FileName, FileName, key);
        }
        BufferedReader reader = new BufferedReader(new FileReader(FileName));
        String text = new Gson().fromJson(reader, String.class);
        reader.close();
        return text;
    }

    @Override
    public void writing(String text, String way) throws IOException {
        FileWriter fw = new FileWriter(mkdirFiles(way));
        fw.write(new Gson().toJson(text));
        fw.close();
    }
    private File mkdirFiles(String filePath) throws IOException {
        File file = new File(filePath);
        File parent=file.getParentFile();
        if (!file.getParentFile().exists()) {
            file.getParentFile().dirs();
        }
        file.createNewFile();

        return file;
    }
}