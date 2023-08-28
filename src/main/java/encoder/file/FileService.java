package encoder.file;

import encoder.cipher.CaesarCipher;
import java.io.*;

public class FileService {
    private CaesarCipher caesarCipher;

    public FileService(CaesarCipher caesarCipher) {
        this.caesarCipher = caesarCipher;
    }

    public String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader
                (filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();

    }

    public void writeFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter
                (new FileWriter(filePath))) {
            writer.write(content);
        }
    }

}


