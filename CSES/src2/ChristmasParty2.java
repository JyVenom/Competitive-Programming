import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChristmasParty2 {
    static final int MD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k;
        if (n == 1)
            k = 0;
        else {
            int a = 0, b = 1;
            k = b;
            for (int i = 2; i < n; i++) {
                k = (int) ((long) (a + b) * i % MD);
                a = b;
                b = k;
            }
        }
        System.out.println(k);
    }
}
