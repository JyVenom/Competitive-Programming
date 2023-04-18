import java.io.*;
import java.util.StringTokenizer;

public class Cereal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] prefs = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            prefs[i][0] = Integer.parseInt(st.nextToken()) - 1;
            prefs[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
        br.close();

        for (int start = 0; start < n; start++) {
            boolean[] used = new boolean[m];
            int count = 0;
            for (int i = start; i < n; i++) {
                int[] pref = prefs[i];
                if (!used[pref[0]]) {
                    count++;
                    used[pref[0]] = true;
                }
                else if (!used[pref[1]]) {
                    count++;
                    used[pref[1]] = true;
                }
            }
            pw.println(count);
        }

        pw.close();
    }
}
