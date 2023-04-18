import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MilkVisits28 {
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        Reader r = new Reader("milkvisits.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));

        int n = r.nextInt();
        int m = r.nextInt();
        int[] type = new int[n];
        for (int i = 0; i < n; i++) {
            type[i] = r.nextInt() - 1;
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        int N = n - 1;
        for (int i = 0; i < N; i++) {
            int a = r.nextInt() - 1;
            int b = r.nextInt() - 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> friends = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int a = r.nextInt() - 1;
            int b = r.nextInt() - 1;
            int c = r.nextInt() - 1;

            if (!friends.containsKey(a)) {
                friends.put(a, new HashMap<>());
            }
            if (!friends.get(a).containsKey(b)) {
                friends.get(a).put(b, new ArrayList<>());
            }
            friends.get(a).get(b).add(new int[]{c, i});

            if (!friends.containsKey(b)) {
                friends.put(b, new HashMap<>());
            }
            if (!friends.get(b).containsKey(a)) {
                friends.get(b).put(a, new ArrayList<>());
            }
            friends.get(b).get(a).add(new int[]{c, i});
        }

        int[][] times = new int[n][2];
        dfs2(edges, times, 0, -1);
        boolean[] ans = new boolean[m];
        ArrayList<Integer> path = new ArrayList<>();
        int[][] lasts = new int[n][2];
        dfs(friends, edges, path, lasts, times, type, ans, 0, -1);

        for (boolean an : ans) {
            pw.print(an ? 1 : 0);
        }
        pw.println();
        pw.close();
    }


    private static void dfs(HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> friends, ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> path, int[][] last, int[][] times, int[] type, boolean[] ans, int cur, int parent) {
        int temp = path.size();
        path.add(cur);
        int[] prev = last[type[cur]].clone();
        last[type[cur]][0] = cur;
        last[type[cur]][1] = path.size();


        if (friends.containsKey(cur)) {
            for (int b : friends.get(cur).keySet()) {
                for (int[] pair : friends.get(cur).get(b)) {
                    int c = pair[0];
                    int d = pair[1];

                    if (ans[d]) {
                        continue;
                    }

                    if (cur == b) {
                        if (type[b] == c) {
                            ans[d] = true;
                        }
                    } else {
                        if (!(last[c][1] == 0)) {
                            int y = last[c][0];
                            if (isAnc(times, y, b)) {
                                if (y != cur) {
                                    if (edges.get(y).size() > 2) {
                                        int Y = path.get(last[c][1]);
                                        if (notAnc(times, Y, b)) {
                                            ans[d] = true;
                                        }
                                    }
                                } else {
                                    ans[d] = true;
                                }
                            } else {
                                ans[d] = true;
                            }
                        }
                    }
                }
            }
        }

        for (int next : edges.get(cur)) {
            if (next != parent) {
                dfs(friends, edges, path, last, times, type, ans, next, cur);
            }
        }

        path.remove(temp);
        last[type[cur]] = prev.clone();
    }

    private static void dfs2(ArrayList<ArrayList<Integer>> edges, int[][] times, int cur, int parent) {
        times[cur][0] = time++;

        for (int next : edges.get(cur)) {
            if (next != parent) {
                dfs2(edges, times, next, cur);
            }
        }

        times[cur][1] = time - 1;
    }

    private static boolean isAnc(int[][] times, int a, int b) {
        return times[a][0] < times[b][0] && times[a][1] >= times[b][1];
    }

    private static boolean notAnc(int[][] times, int a, int b) {
        return isAnc(times, b, a) || times[a][0] >= times[b][1] || times[a][1] <= times[b][0];
    }

    static class Reader {
        //        final private int BUFFER_SIZE = 1 << 24;
        final private int BUFFER_SIZE = 16777216;
        private final FileInputStream is;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader(String file_name) throws IOException {
            is = new FileInputStream(file_name);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = is.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            is.close();
        }
    }
}
