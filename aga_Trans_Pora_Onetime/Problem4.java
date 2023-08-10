import java.io.*;

public class Problem4 {
    public static void main(String[] args) {
        // Declaring plain text
        String plainText = readFile("plaintext.txt");

        // Declaring key
        String key = readFile("key.txt");

        // plainText = plainText.toUpperCase();
        key = key.toUpperCase();

        int width = 5;

        String result = transposeCipher(plainText, width);
        writeFile("transpositionEncypted.txt", result);

        result = encryptOnetimepad(result, key);
        writeFile("oneTimePadencrypt.txt", result);

        result = decryptOnetimepad(result, key);

        result = transposeDecrypt(result, width);

        writeFile("decrypt.txt", result);

    }

    public static String transposeCipher(String name, int width) {
        StringBuilder enc = new StringBuilder();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j * width + i < name.length(); j++) {
                enc.append(name.charAt(j * width + i));
            }
        }
        return enc.toString();
    }

    // Method 1: Returning encrypted text
    public static String encryptOnetimepad(String text, String key) {
        String cipherText = "";
        int[] cipher = new int[text.length()];

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                cipher[i] = (text.charAt(i) - 'A' + key.charAt(i) - 'A') % 26;
                int x = cipher[i] + 'A' + 1;
                cipherText += (char) x;
            } else if (Character.isLowerCase(text.charAt(i))) {
                cipher[i] = (text.charAt(i) - 'a' + key.charAt(i) - 'A') % 26;
                int x = cipher[i] + 'a' + 1;
                cipherText += (char) x;
            } else {
                cipherText += text.charAt(i);
            }

        }
        return cipherText.toString();
    }

    // Method 2: Returning plain text
    public static String decryptOnetimepad(String s, String key) {
        String plainText = "";
        int[] plain = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                plain[i] = s.charAt(i) - 'A' - (key.charAt(i) - 'A');
                if (plain[i] < 0) {
                    plain[i] = plain[i] + 26;
                }
                int x = plain[i] + 'A' - 1;
                plainText += (char) x;
            } else if (Character.isLowerCase(s.charAt(i))) {
                plain[i] = s.charAt(i) - 'a' - (key.charAt(i) - 'A');
                if (plain[i] < 0) {
                    plain[i] = plain[i] + 26;
                }
                int x = plain[i] + 'a' - 1;
                plainText += (char) x;
            } else {
                plainText += s.charAt(i);
            }

        }
        return plainText.toString();
    }

    public static String transposeDecrypt(String name, int width) {
        int len = name.length();
        int rem = len % width;
        int row = len / width;
        char[] dec = new char[len];
        int ind = 0;
        for (int i = 0; i < width; i++, rem = Math.max(rem - 1, 0)) {
            int tem = row + (rem > 0 ? 1 : 0);
            for (int j = 0; j < tem; j++) {
                dec[j * width + i] = name.charAt(ind++);
            }
        }
        return new String(dec);
    }

    public static String readFile(String filename) {
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return content.toString();
    }

    public static void writeFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
