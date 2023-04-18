import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class RestStops2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));

        String[] line = br.readLine().split(" ");
//        int l = Integer.parseInt(line[0]);
        int n = Integer.parseInt(line[1]);
        int f = Integer.parseInt(line[2]);
        int b = Integer.parseInt(line[3]);

        long[][] stops = new long[n][2];
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            stops[i][0] = Long.parseLong(line[0]);
            stops[i][1] = Long.parseLong(line[1]);
        }
        Arrays.sort(stops, Comparator.comparingLong(arr -> arr[0]));
        long dif = f - b;

        long tasty = 0;
        long prev = 0;
        int start = 0;
        while (start < n) {
            int max = start;
            for (int i = start; i < n; i++) {
                if (stops[i][1] > stops[max][1]) {
                    max = i;
                }
            }
            start = max + 1;

            long cur = stops[max][0];
            tasty += ((cur - prev)) * dif * stops[max][1];
            prev = cur;
        }

        pw.println(tasty);
        pw.close();
    }
}
