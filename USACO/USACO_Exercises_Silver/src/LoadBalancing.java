import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class LoadBalancing {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter pw = new PrintWriter(new File("balancing.out"));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] cows = new int[n][2];
        int avX = 0;
        int avY = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
            avX += cows[i][0];
            avY += cows[i][1];
        }
        avX /= n;
        avY /= n;
        if (avX % 2 == 1){
            avX += 1;
        }
        if (avY % 2 == 1){
            avY += 1;
        }
        Arrays.sort(cows, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(cows, Comparator.comparingInt(arr -> arr[0]));

        int ul = 0;
        int ur = 0;
        int ll = 0;
        int lr = 0;
        for (int i = 0; i < n; i++){
            if (cows[i][0] < avX){
                if (cows[i][1] < avY){
                    ll++;
                }
                else {
                    ul++;
                }
            }
            else {
                if (cows[i][1] < avY){
                    lr++;
                }
                else {
                    ur++;
                }
            }
        }
        int max = Math.max(Math.max(ul, ur), Math.max(ll, lr));
        pw.println(max);
        pw.close();
    }
}
