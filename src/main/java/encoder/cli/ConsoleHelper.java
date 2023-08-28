package encoder.cli;

import java.util.Scanner;

public class ConsoleHelper {
    private Scanner scanner;

    public ConsoleHelper() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.println("Enter command (ENCRYPT, DECRYPT, BRUTE_FORCE):");
        return scanner.nextLine().toUpperCase();
    }

    public String readFilePath() {
        System.out.println("Enter file path:");
        return scanner.nextLine();
    }

    public int readKey() {
        System.out.println("Enter key:");
        return scanner.nextInt();
    }

    public void printEncryptedContent(String content) {
        System.out.println("Encrypted content:");
        System.out.println(content);
    }

    public void printDecryptedContent(String content) {
        System.out.println("Decrypted content:");
        System.out.println(content);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}
