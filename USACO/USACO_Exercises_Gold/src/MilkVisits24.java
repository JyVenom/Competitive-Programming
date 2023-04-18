import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class MilkVisits24 {
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        InputReader in = new InputReader(new FileInputStream("milkvisits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));

        int n = in.readInt();
        int m = in.readInt();
        int[] type = new int[n];
        for (int i = 0; i < n; i++) {
            type[i] = in.readInt() - 1;
        }
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        int N = n - 1;
        for (int i = 0; i < N; i++) {
            int a = in.readInt() - 1;
            int b = in.readInt() - 1;

            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        HashMap<Integer, HashMap<Integer, ArrayList<int[]>>> friends = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int a = in.readInt() - 1;
            int b = in.readInt() - 1;
            int c = in.readInt() - 1;

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
        int[] prev = last[type[cur]];
        last[type[cur]] = new int[]{cur, path.size()};


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
        last[type[cur]] = prev;
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
        return times[a][0] >= times[b][0] || times[a][1] < times[b][1];
    }

    private static class InputReader {
        private final InputStream stream;
        private final byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }
    }
}
