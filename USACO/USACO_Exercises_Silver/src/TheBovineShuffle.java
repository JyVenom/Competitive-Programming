import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TheBovineShuffle {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] moves = new int[n];
        for (int i = 0; i < n; i++) {
            moves[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int count = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int cur = i;
            ArrayList<Integer> path = new ArrayList<>();
            boolean[] visited2 = new boolean[n];
            while (!visited[cur]) {
                visited[cur] = true;
                visited2[cur] = true;
                path.add(cur);
                cur = moves[cur];
            }
            if (visited2[cur]) {
                for (int j = path.size() - 1; j >= 0; j--) {
                    count++;
                    if (path.get(j) == cur) {
                        break;
                    }
                }
            }
        }

        pw.println(count);
        pw.close();
    }
}
