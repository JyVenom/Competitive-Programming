import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoad2 {
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
        Arrays.sort(nTime, (o1, o2) -> Integer.compare(o2[0], o1[0]));
        Arrays.sort(nTime, (o1, o2) -> Integer.compare(o2[1], o1[1]));

        int pairs = 0;
        int start = c - 1;
        for (int i = 0; i < n; i++) {
            int a = nTime[i][0];
            int b = nTime[i][1];
            for (int j = start; j >= 0; j--) {
                int curC = cTime[j];
                if (curC > b) {
                    start--;
                }
                else if (curC >= a && curC <= b) {
                    pairs++;
                    start = j - 1;
                    break;
                }
                else if (curC < a) {
                    break;
                }
            }
        }

        pw.println(pairs);
        pw.close();
    }
}
