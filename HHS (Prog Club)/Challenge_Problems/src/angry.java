import java.io.*;
import java.util.Arrays;

public class angry {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));

        int n = Integer.parseInt(br.readLine());
        int[] cows = new int[n];
        for (int i = 0; i < n; i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(cows);
        int max = 1;
        for (int i = 0; i < n; i++) {
            int count = 1;
            int cur = 1;
            int prev = i;
            for (int j = i - 1; j >= 0; j--) {
                if (cows[prev] - cows[j] <= cur) {
                    count++;
                } else {
                    if (j != i - 1) {
                        cur++;
                        prev = j + 1;
                        if (cows[prev] - cows[j] <= cur) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    else {
                        break;
                    }
                }
            }
            cur = 1;
            prev = i;
            for (int j = i + 1; j < n; j++) {
                if (cows[j] - cows[prev] <= cur) {
                    count++;
                } else {
                    if (j != i + 1) {
                        cur++;
                        prev = j - 1;
                        if (cows[j] - cows[prev] <= cur) {
                            count++;
                        } else {
                            break;
                        }
                    }
                    else {
                        break;
                    }
                }
            }
            max = Math.max(max, count);
        }

        pw.println(max);
        pw.close();
    }
}