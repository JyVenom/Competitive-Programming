import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Palindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> ans = generatePalindromes(n);
        Collections.sort(ans);
        for (int an : ans) {
            pw.println(an);
        }

        pw.close();
    }

    static int createPalindrome(int input, int isOdd) {
        int n = input;
        int palin = input;

        if (isOdd == 1)
            n /= 10;

        while (n > 0) {
            palin = palin * 10 + (n % 10);
            n /= 10;
        }
        return palin;
    }

    static ArrayList<Integer> generatePalindromes(int n) {
        int number;

        ArrayList<Integer> res = new ArrayList<>();

        int i = 1;
        while ((number = createPalindrome(i, 0)) <= n) {
            res.add(number);
            i++;
        }


        i = 1;
        while ((number = createPalindrome(i, 1)) <= n) {
            res.add(number);
            i++;
        }

        return res;
    }
}
