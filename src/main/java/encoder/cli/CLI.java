package encoder.cli;

import encoder.cipher.CaesarCipher;
import encoder.file.FileService;

import java.io.IOException;
import java.util.Arrays;

public class CLI {
    private FileService fileService;
    private CaesarCipher caesarCipher;
    private final String[] validCommands = {"ENCRYPT", "DECRYPT", "BRUTE_FORCE"};
    private ConsoleHelper consoleHelper;

    public CLI(FileService fileService, CaesarCipher caesarCipher, ConsoleHelper consoleHelper) {
        this.fileService = fileService;
        this.caesarCipher = caesarCipher;
        this.consoleHelper = consoleHelper;
    }

    public void run() {
        String command = consoleHelper.readCommand();

        if (!Arrays.asList(validCommands).contains(command)) {
            consoleHelper.printMessage("Invalid command.");
            return;
        }

        String filePath = consoleHelper.readFilePath();
        int key = 0;

        if (command.equals("ENCRYPT") || command.equals("DECRYPT")) {
            key = consoleHelper.readKey();
        }

        try {
            String content = fileService.readFile(filePath);
            switch (command) {
                case "ENCRYPT":
                    handleEncryptCommand(content, key, filePath);
                    break;
                case "DECRYPT":
                    handleDecryptCommand(content, key, filePath);
                    break;
                case "BRUTE_FORCE":
                    bruteForceDecryptAndPrint(content);
                    break;

                default:
                    consoleHelper.printMessage("Invalid command.");
            }
        } catch (IOException e) {
            consoleHelper.printMessage("Error reading or writing the file: " + e.getMessage());
        }
    }
    private void handleEncryptCommand(String content, int key, String filePath) throws IOException {
        String encryptedContent = caesarCipher.encrypt(content, key);
        consoleHelper.printEncryptedContent(encryptedContent);
        fileService.writeFile(getEncryptedFileName(filePath), encryptedContent);
        consoleHelper.printMessage("File encrypted.");
    }

    private void handleDecryptCommand(String content, int key, String filePath) throws IOException {
        String decryptedContent = caesarCipher.decrypt(content, key);
        consoleHelper.printDecryptedContent(decryptedContent);
        fileService.writeFile(getDecryptedFileName(filePath), decryptedContent);
        consoleHelper.printMessage("File decrypted.");
    }




    private void bruteForceDecryptAndPrint(String content) {
        int alphabetLength = caesarCipher.getAlphabetLength();
        consoleHelper.printMessage("Brute_force decryption results:");
        for (int key = 1; key <= alphabetLength; key++) {
            String decryptedContent = caesarCipher.decrypt(content, key);
            consoleHelper.printMessage("Key " + key + ": ");
            consoleHelper.printMessage(decryptedContent);
            consoleHelper.printMessage("========================================");
        }
    }

    private String getEncryptedFileName(String filePath) {
        return filePath.replace(".txt", "[ENCRYPTED].txt");
    }

    private String getDecryptedFileName(String filePath) {
        return filePath.replace("[ENCRYPTED].txt", "[DECRYPTED].txt");
    }
}
