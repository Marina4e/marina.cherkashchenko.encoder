package encoder.cipher;

public class CaesarCipher {
    private final String alphabet;
    private final String upperCaseAlphabet;
    private final int alphabetLength;

    public CaesarCipher(String alphabet) {
        this.alphabet = alphabet.toLowerCase();
        this.upperCaseAlphabet = alphabet.toUpperCase();
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
            int index = alphabet.indexOf(Character.toLowerCase(c));
            if (index != -1) {
                char shiftedChar;
                if (Character.isUpperCase(c)) {
                    int shiftedIndex = calculateShiftedIndex(key, shiftModifier, index);
                    shiftedChar = upperCaseAlphabet.charAt(shiftedIndex);
                } else {
                    int shiftedIndex = calculateShiftedIndex(key, shiftModifier, index);
                    shiftedChar = alphabet.charAt(shiftedIndex);
                }
                processedText.append(shiftedChar);
            } else {
                processedText.append(c);
            }
        }
        return processedText.toString();
    }

    private int calculateShiftedIndex(int key, int shiftModifier, int index) {
        int modifiedKey = key % alphabetLength;
        int shiftedIndex = (index + modifiedKey * shiftModifier + alphabetLength) % alphabetLength;
        return shiftedIndex;
    }

    public int getAlphabetLength() {
        return alphabetLength;
    }
}
