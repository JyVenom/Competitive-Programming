import java.io.*;
import java.util.StringTokenizer;

public class BreedCounting {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bcount.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] breeds = new int[n];
        for (int i = 0; i < n; i++) {
            breeds[i] = Integer.parseInt(br.readLine()) - 1;
        }
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken()) - 1;
            queries[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        int[][] sums = new int[n][3];
        sums[0][breeds[0]] = 1;
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1].clone();
            sums[i][breeds[i]]++;
        }
        for (int[] query : queries) {
            int[] temp = sums[query[1]].clone();
            temp[0] -= sums[query[0]][0];
            temp[1] -= sums[query[0]][1];
            temp[2] -= sums[query[0]][2];
            temp[breeds[query[0]]]++;
            pw.println(temp[0] + " " + temp[1] + " " + temp[2]);
        }

        pw.close();
    }
}
