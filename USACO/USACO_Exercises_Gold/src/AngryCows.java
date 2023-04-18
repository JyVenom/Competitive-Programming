import java.io.*;
import java.util.Arrays;

public class AngryCows {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));

        int n = Integer.parseInt(br.readLine());
        int[] hay = new int[n];
        for (int i = 0; i < n; i++) {
            hay[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(hay);
        int N = n - 1;
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = hay[i + 1] - hay[i];
        }
        int[] costF = new int[n];
        int[] costR = new int[n];
        for (int i = 1; i < n; i++) {
            int prev = i - 1;
            costF[i] = costF[prev] + 1;
            if (dist[prev] > costF[i]) {
                costF[i] = dist[prev];
            }
        }
        for (int i = n - 2; i >= 0; i--) {
            int prev = i + 1;
            costR[i] = costR[prev] + 1;
            if (dist[i] > costR[i]) {
                costR[i] = dist[i];
            }
        }
        int min = 500000000;
        int start = 0;
        for (int i = 1; i < n; i++) {
            for (; start < n; start++) {
                int a = costF[start] + 1;
                int b = costR[i] + 1;
                if (hay[start] + a >= hay[i] - b) {
                    min = Math.min(min, Math.max(a, b));
                    if (min == 18550) {
                        System.out.println();
                    }
                    break;
                }
            }
        }

        pw.println((double) min);
        pw.close();
    }
}
