import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class LoadBalancing2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("balancing.in"));
        PrintWriter pw = new PrintWriter(new File("balancing.out"));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] cows = new int[n][2];
        ArrayList<Integer> difX = new ArrayList<>();
        ArrayList<Integer> difY = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i][0] = Integer.parseInt(st.nextToken());
            cows[i][1] = Integer.parseInt(st.nextToken());
            if (!difX.contains(cows[i][0])) {
                difX.add(cows[i][0]);
            }
            if (!difY.contains(cows[i][1])) {
                difY.add(cows[i][1]);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int x = 0; x < difX.size() - 1; x++){
            int a = difX.get(x) + 1;
            for (int y = 0; y < difY.size() - 1; y++){
                int b = difY.get(y) + 1;
                int ul = 0;
                int ur = 0;
                int ll = 0;
                int lr = 0;
                for (int i = 0; i < n; i++){
                    if (cows[i][0] < a){
                        if (cows[i][1] < b){
                            ll++;
                        }
                        else {
                            ul++;
                        }
                    }
                    else {
                        if (cows[i][1] < b){
                            lr++;
                        }
                        else {
                            ur++;
                        }
                    }
                }

                min = Math.min(min, Math.max(Math.max(ul, ur), Math.max(ll, lr)));
            }
        }
        pw.println(min);
        pw.close();
    }
}
