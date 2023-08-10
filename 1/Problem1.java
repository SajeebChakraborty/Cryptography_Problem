import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class Problem1 {
    // ceaserEncrypts text using a shift of s
    public String ceaserEncrypt(String text, int s) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) +
                        s - 65) % 26 + 65);
                result += ch;
            } else if (Character.isLowerCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) +
                        s - 97) % 26 + 97);
                result += ch;
            } else
                result += text.charAt(i);
        }
        return result;
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

    public static String transposeDecrypt(String name, int width) {
        int len = name.length();
        int rem = len % width;
        int row = len / width;
        char[] dec = new char[len];
        Arrays.fill(dec, '?');
        int ind = 0;
        for (int i = 0; i < width; i++, rem = Math.max(rem - 1, 0)) {
            int tem = row + (rem > 0 ? 1 : 0);
            for (int j = 0; j < tem; j++) {
                dec[j * width + i] = name.charAt(ind++);
            }
        }
        return new String(dec);
    }

    public String ceaserDecrypt(String text, int s) {
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) -
                        s - 65) % 26 + 65);
                result += ch;
            } else if (Character.isLowerCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) -
                        s - 97) % 26 + 97);
                result += ch;
            } else
                result += text.charAt(i);
        }
        return result;
    }

    // Driver code
    public static void main(String[] args) {
        String text, result;
        text = readFile("./input.txt");
        int shift_for_ceaser = 3;
        int width = 5;
        Problem1 obj = new Problem1();
        result = obj.ceaserEncrypt(text, shift_for_ceaser);
        writeFile("./Chipertext.txt", result);
        result = transposeCipher(result, width);
        writeFile("./Transposition.txt", result);
        result = transposeDecrypt(result, width);
        result = obj.ceaserDecrypt(result, shift_for_ceaser);
        writeFile("./RecoverFile.txt", result);
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