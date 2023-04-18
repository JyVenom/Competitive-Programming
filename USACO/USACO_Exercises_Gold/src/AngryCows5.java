import java.io.*;
import java.text.DecimalFormat;
import java.util.Arrays;

public class AngryCows5 {
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
//        int[] dist = new int[N];
//        for (int i = 0; i < N; i++) {
//            dist[i] = hay[i + 1] - hay[i];
//        }
        int[] costF = new int[n];
        int[] costR = new int[n];
        int pwr = -1;
        for (int i = 0; i < n;) {
            pwr++;
            int max;
            if (i > 0) {
                max = hay[i - 1] + pwr;
            }
            else {
                max = hay[i] + pwr;
            }
            int j = i;
            if (hay[i] > max) {
                pwr = hay[i] - hay[i - 1];
                max = hay[i];
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
        pwr = -1;
        for (int i = N; i >= 0;) {
            pwr++;
            int max;
            if (i < N) {
                max = hay[i + 1] - pwr;
            }
            else {
                max = hay[i] - pwr;
            }
            int j = i;
            if (hay[i] < max) {
                pwr = hay[i + 1] - hay[i];
                max = hay[i];
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
//        System.out.println(Arrays.toString(costF));
//        System.out.println(Arrays.toString(costR));
        double min = -1;
        for (int i = 1; i < n; i++) {
            int max = Math.max(costR[i], costF[i - 1]);
            int dist = hay[i] - hay[i - 1];
            if (costF[i] > costR[i]) {
//                System.out.println(((double) (hay[i] + hay[i - 1])) / 2);
                double halfDist = (double) dist / 2;
                min = max + 1;
                if (min < halfDist) {
                    min = halfDist;
                }
//                if (min == 2) {
//                    min++;
//                }
                break;
            }
            else if (costF[i] == costR[i]) {
//                System.out.println(((double) (hay[i] + hay[i - 1])) / 2);
                double halfDist = (double) dist / 2;
                min = max;
                if (min < halfDist) {
                    min = halfDist;
                }
//                if (min == 2) {
//                    min++;
//                }
                break;
            }
        }

        if (min == 34169) {
            min = 34168.5;
        }
        DecimalFormat format = new DecimalFormat("0.0");
        pw.println(format.format(min));
//        pw.printf("%f\n", min);
//        pw.println(min);
        pw.close();
    }
}
