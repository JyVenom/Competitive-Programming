import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class LoadBalancing3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter pw = new PrintWriter(new File("balancing.out"));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] cows = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cows, Comparator.comparingInt(arr -> arr[0]));

        int ans = cows.length;
        for(int i = 0; i < n; i++) {
            ArrayList<int[]> below = new ArrayList<>();
            ArrayList<int[]> above = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                if(cows[j][1] <= cows[i][1]) {
                    below.add(cows[j]);
                }
                else {
                    above.add(cows[j]);
                }
            }
            int belowIndex = 0;
            int aboveIndex = 0;
            while(belowIndex < below.size() || aboveIndex < above.size()) {
                int xBorder = Integer.MAX_VALUE;
                if(belowIndex < below.size()) {
                    xBorder = Math.min(xBorder, below.get(belowIndex)[0]);
                }
                if(aboveIndex < above.size()) {
                    xBorder = Math.min(xBorder, above.get(aboveIndex)[0]);
                }
                while(belowIndex < below.size() && below.get(belowIndex)[0] == xBorder) {
                    belowIndex++;
                }
                while(aboveIndex < above.size() && above.get(aboveIndex)[0] == xBorder) {
                    aboveIndex++;
                }
                ans = Math.min(ans, Math.max(Math.max(belowIndex, below.size() - belowIndex), Math.max(aboveIndex, above.size() - aboveIndex)));
            }
        }
        pw.println(ans);
        
        pw.close();
    }
}
