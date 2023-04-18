import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoad {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] cTime = new int[c];
        int[][] nTime = new int[n][2];
        for (int i = 0; i < c; i++) {
            cTime[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cTime);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            nTime[i][0] = Integer.parseInt(st.nextToken());
            nTime[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nTime, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(nTime, Comparator.comparingInt(arr -> arr[0]));

        int pairs = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            int a = nTime[i][0];
            int b = nTime[i][1];
            for (int j = start; j < c; j++) {
                int curC = cTime[j];
                if (curC < a) {
                    start++;
                }
                else if (curC >= a && curC <= b) {
                    pairs++;
                    start = j + 1;
                    break;
                }
                else if (curC > b) {
                    break;
                }
            }
        }

        pw.println(pairs);
        pw.close();
    }
}
