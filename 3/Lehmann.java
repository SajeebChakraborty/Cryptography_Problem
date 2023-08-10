import java.math.BigInteger;
import java.util.Random;
import java.io.*;

public class Lehmann {

    public static boolean isPrime(BigInteger n, int iterations) {
        // Base cases for small numbers
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return false;
        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3)))
            return true;

        Random random = new Random();

        for (int i = 0; i < iterations; i++) {
            BigInteger a = new BigInteger(n.bitLength(), random);
            a = a.mod(n.subtract(BigInteger.TWO)).add(BigInteger.TWO); // Generate a random number between 2 and n-1

            BigInteger x = a.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.TWO), n);

            if (!x.equals(BigInteger.ONE) && !x.equals(n.subtract(BigInteger.ONE))) {
                return false; // n is composite
            }
        }

        return true; // n is likely prime
    }

    public static void main(String[] args) {
        String bigNumber = readFile("input.txt");
        BigInteger number = new BigInteger(bigNumber);
        int iterations = 10;

        if (isPrime(number, iterations)) {
            System.out.println(number + " is likely prime.");
        } else {
            System.out.println(number + " is composite.");
        }
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
