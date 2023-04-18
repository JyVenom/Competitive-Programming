import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P13 {
    private static int min = Integer.MAX_VALUE, num = 0, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Prob13.in.txt"));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st;

        String line;
        int prev = 0, start = 0, start2 = 1, add = 1; //add = size prev
        HashMap<Integer, HashMap<Integer, Integer>> edges = new HashMap<>();
        while ((line = br.readLine()) != null) {
            st = new StringTokenizer(line);

            for (int i = 0; i < add; i++) {
                edges.put(start + i, new HashMap<>());
            }

            if (st.countTokens() > prev) {
                prev = st.countTokens();

                while (st.hasMoreTokens()) {
                    for (int i = 0; i < 3; i++) {
                        edges.get(start).put(start2 + i, Integer.parseInt(st.nextToken()));
                    }
                    start++;
                    start2++;
                }

                add += 2;
                start2 += 2;
            } else {
                prev = st.countTokens();

                if (add == 3) {
                    for (int i = 0; i < 3; i++) {
                        edges.get(start + i).put(start2, Integer.parseInt(st.nextToken()));
                    }
                } else {
                    edges.get(start).put(start2, Integer.parseInt(st.nextToken()));
                    start++;

                    edges.get(start).put(start2, Integer.parseInt(st.nextToken()));
                    edges.get(start).put(start2 + 1, Integer.parseInt(st.nextToken()));
                    start++;

                    int end = start + add - 4;
                    for (; start < end; start++) {
                        for (int i = 0; i < 3; i++) {
                            edges.get(start).put(start2 + i, Integer.parseInt(st.nextToken()));
                        }
                        start2++;
                    }

                    edges.get(start).put(start2, Integer.parseInt(st.nextToken()));
                    edges.get(start).put(start2 + 1, Integer.parseInt(st.nextToken()));
                    start++;
                    start2++;


                    edges.get(start).put(start2, Integer.parseInt(st.nextToken()));
                    start++;
                    start2++;
                }

                add -= 2;
            }
        }

        b = start2;
        dfs(edges, 0, 0);


        pw.println("Lowest path difficulty: " + min);
        pw.println("Number of paths with the lowest difficulty: " + num);
        pw.close();
    }

    private static void dfs(HashMap<Integer, HashMap<Integer, Integer>> edges, int at, int cost) {
        if (at == b) {
            if (cost < min) {
                min = cost;
                num = 0;
            }

            if (cost == min) {
                num++;
            }
            return;
        }

        for (int next : edges.get(at).keySet()) {
            dfs(edges, next, cost + edges.get(at).get(next));
        }
    }
}
