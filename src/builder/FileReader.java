package builder;

import enums.FileExtension;
import interfaces.IFileReader;
import interfaces.IFileReaderBuilder;
import readers.*;

import java.util.List;

public class FileReaderBuilder implements IFileReaderBuilder {
    protected IFileReader fileReader;
    protected String inputFilename;
    public FileReaderBuilder(FileExtension extension, String inputFilename) {
        if (extension == FileExtension.Txt) {
            fileReader = new TxtFileReader(inputFilename);
        }
        else if (extension == FileExtension.Json) {
            fileReader = new JsonFileReader(inputFilename);
        }
        else if (extension == FileExtension.Xml) {
            fileReader = new XmlFileReader(inputFilename);
        }
        this.inputFilename = inputFilename;
    }

    public FileReaderBuilder(String extension, String inputFilename) {
        var fileExtension = extension.toUpperCase();
        if (fileExtension.equals(FileExtension.Txt.name().toUpperCase())) {
            fileReader = new TxtFileReader(inputFilename);
        }
        else if ((fileExtension.equals(FileExtension.Json.name().toUpperCase()))){
            fileReader = new JsonFileReader(inputFilename);
        }
        else {
            fileReader = new XmlFileReader(inputFilename);
        }
        this.inputFilename = inputFilename;
    }

    @Override
    public void setEncrypting(String key) {
        fileReader = new EncryptedFileReader(key, fileReader);
    }

    @Override
    public void setEncrypting(List<String> keys) {
        if (keys != null) {
            for (var key : keys) {
                fileReader = new EncryptedFileReader(key, fileReader);
            }
        }
    }

    @Override
    public void setZipping(boolean isZip) {
        if (isZip) {
            fileReader = new ZipFileReader(fileReader);
        }
    }

    @Override
    public IFileReader getFileReader() {
        return fileReader;
    }
}
