import java.io.*;
import java.util.Arrays;

public class AngryCows3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));

        int n = Integer.parseInt(br.readLine());
        int[] hay = new int[n];
        for (int i = 0; i < n; i++) {
            hay[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(hay);
//        System.out.println(Arrays.toString(hay));
        int N = n - 1;
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = hay[i + 1] - hay[i];
        }
        int[] costF = new int[n];
        int[] costR = new int[n];
        int pwr = 0;
        for (int i = 0; i < n;) {
            pwr++;
            if (i > 0 && hay[i - 1] + pwr < hay[i]) {
                pwr = hay[i] - hay[i - 1];
            }
            costF[i] = pwr;
            if (i < N) {
                int max;
                if (i > 0) {
                    max = hay[i - 1] + pwr;
                }
                else {
                    max = hay[i] + pwr;
                }
                int j = i + 1;
                if (hay[j] > max) {
                    pwr = hay[j] - hay[i];
                    max = hay[j];
                }
                for (; j < n; j++) {
                    if (hay[j] <= max) {
                        costF[j] = pwr;
                    } else {
                        break;
                    }
                }
                i = j;
            }
            else {
                break;
            }
        }
        pwr = 0;
        for (int i = N; i >= 0;) {
            pwr++;
            if (i < N && hay[i + 1] - pwr > hay[i]) {
                pwr = hay[i + 1] - hay[i];
            }
            costR[i] = pwr;
            if (i > 0) {
                int max;
                if (i < N) {
                    max = hay[i + 1] - pwr;
                }
                else {
                    max = hay[i] - pwr;
                }
                int j = i - 1;
                if (hay[j] < max) {
                    pwr = hay[i] - hay[j];
                    max = hay[j];
                }
                for (; j >= 0; j--) {
                    if (hay[j] >= max) {
                        costR[j] = pwr;
                    } else {
                        break;
                    }
                }
                i = j;
            }
            else {
                break;
            }
        }
        System.out.println(Arrays.toString(costF));
        System.out.println(Arrays.toString(costR));
        int min = -1;
        for (int i = 0; i < n; i++) {
            if (costF[i] >= costR[i]) {
                System.out.println(((double) (hay[i] + hay[i - 1])) / 2);
                min = Math.max(costR[i], costF[i - 1]) + 1;
                break;
            }
        }

        pw.println((double) min);
        pw.close();
    }
}
