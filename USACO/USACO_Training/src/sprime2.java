/*
ID: jerryya2
LANG: JAVA
TASK: sprime
*/

import java.io.*;
import java.util.ArrayList;

public class sprime2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

        int n = Integer.parseInt(br.readLine());
        br.close();

        ArrayList<Integer> all = new ArrayList<>();

        int max = (int) Math.pow(10, n);
        for (int i = (int) (2 * Math.pow(10, n - 1) + 1); i < max; i += 2) {
            StringBuilder sb = new StringBuilder(Integer.toString(i));
            for (int j = sb.length() - 2; j >= 0; j--) {
                int num = Character.getNumericValue(sb.charAt(j));
                if (j != 0 && num % 2 == 0) {
                    i += Math.pow(10, sb.length() - j - 1);
                }
            }
            all.add(i);
        }

        int count = 0;
        for (int i = 0; i < all.size(); i++) {
            int num = all.get(i);
            boolean isPrime = true;
            while (num > 0) {
                if (!isPrime(num)) {
                    isPrime = false;
                    break;
                }
                num /= 10;
                count++;
            }
            if (!isPrime) {
                all.remove(i);
                for (int j = i; j < all.size(); j++) {
                    if ((all.get(j) / (int) Math.pow(10, count)) == num) {
                        all.remove(j);
                        j--;
                    }
                    else {
                        break;
                    }
                }
                i--;
            }
            count = 0;
        }

        for (Integer num : all) {
            pw.println(num);
        }
        pw.close();
    }

    private static boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        else if (num == 2) {
            return true;
        }
        else if (num % 2 == 0) {
            return false;
        }
        else {
            int root = (int) Math.sqrt(num);
            for (int j = 3; j <= root; j += 2) {
                if (num % j == 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
