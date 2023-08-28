package encoder.start;

import encoder.cipher.CaesarCipher;
import encoder.cli.CLI;
import encoder.cli.ConsoleHelper;
import encoder.file.FileService;


public class Runner {
    public void run(ConsoleHelper consoleHelper) {
        String englishAlphabet = "abcdefghijklmnopqrstuvwxyz";


        CaesarCipher englishCaesarCipher = new CaesarCipher(englishAlphabet);

        FileService fileService = new FileService(englishCaesarCipher);
        CLI cli = new CLI(fileService, englishCaesarCipher,consoleHelper);

        cli.run();
    }


    public static void main(String[] args) {
        Runner runner = new Runner();
        runner.run(new ConsoleHelper());
    }
}
