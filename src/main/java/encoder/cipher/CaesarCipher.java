package encoder.cipher;

public class CaesarCipher {
    private final String alphabet;
    private final int alphabetLength;

    public CaesarCipher(String alphabet) {
        this.alphabet = alphabet;
        this.alphabetLength = alphabet.length();
    }

    public String encrypt(String text, int key) {
        return processText(text, key, 1);
    }

    public String decrypt(String text, int key) {
        return processText(text, key, -1);
    }

    private String processText(String text, int key, int shiftModifier) {
        StringBuilder processedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            int index = alphabet.indexOf(c);
            if (index != -1) {
                int modifiedKey = key % alphabetLength;
                int shiftedIndex = (index + modifiedKey * shiftModifier + alphabetLength) % alphabetLength;
                char shiftedChar = alphabet.charAt(shiftedIndex);
                processedText.append(shiftedChar);
            } else {
                processedText.append(c);
            }
        }
        return processedText.toString();
    }

    public int getAlphabetLength() {
        return alphabetLength;
    }

}