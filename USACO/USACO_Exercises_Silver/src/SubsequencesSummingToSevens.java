import java.io.*;

public class SubsequencesSummingToSevens {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("div7.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));

        int n = Integer.parseInt(br.readLine());
        int[] ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = Integer.parseInt(br.readLine());
        }

        int max = 0;
        long[] prev = new long[n];
        prev[0] = ids[0];
        for (int i = 1; i < n; i++) {
            prev[i] = prev[i - 1] + ids[i];
        }
        for (int i = 1; i < n; i++) {
            int I = i - 1;
            long[] cur = prev.clone();
            for (int j = I; j < n; j++) {
                cur[j] -= ids[I];
            }
            for (int j = n - 1; j > I; j--) {
                if (cur[j] % 7 == 0) {
                    int temp = j - I;
                    if (temp > max) {
                        max = temp;
                    }
                    break;
                }
            }
            prev = cur;
        }

        pw.println(max);
        pw.close();
    }
}
