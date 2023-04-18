import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChristmasParty3 {
    static final int MD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 1)
            System.out.println(0);
        else {
            long a = 0, b = 1, k = 1;
            for (int i = 2; i < n; i++) {
                k = ((a + b) * i % MD);
                a = b;
                b = k;
            }
            System.out.println(k);
        }
    }
}
