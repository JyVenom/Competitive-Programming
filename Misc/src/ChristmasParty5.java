import java.io.IOException;

public class ChristmasParty5 {
    public static void main(String[] args) throws IOException {
        int c = System.in.read();
        int n = 0;
        do {
            n *= 10;
            n += c - '0';
            c = System.in.read();
        } while (c != '\n');

        if (n == 1)
            System.out.print(0);
        else {
            long a = 0, b = 1, k = 1;
            for (int i = 2; i < n; i++) {
                k = ((a + b) * i % 1000000007);
                a = b;
                b = k;
            }
            System.out.print(k);
        }
    }
}
