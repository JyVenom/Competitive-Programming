import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DistanceQueries {
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        Reader r = new Reader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = r.nextInt();
        int q = r.nextInt();
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            int a = r.nextInt() - 1;
            int b = r.nextInt() - 1;
            edges.get(a).add(b);
            edges.get(b).add(a);
        }
        HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
        for (int i = 0; i < q; i++) {
            int a = r.nextInt() - 1;
            int b = r.nextInt() - 1;

            if (!map.containsKey(a)) {
                map.put(a, new ArrayList<>());
            }
            map.get(a).add(new int[]{b, i});

            if (a != b) {
                if (!map.containsKey(b)) {
                    map.put(b, new ArrayList<>());
                }
                map.get(b).add(new int[]{a, i});
            }
        }
        r.close();

        int[] lvls = new int[n];
        int[][] times = new int[n][2];
        dfs(edges, times, lvls, 0, 1);
        int[] ans = new int[q];
        dfs(map, edges, new ArrayList<>(), new boolean[n], times, lvls, ans, 0);
        StringBuilder sb = new StringBuilder();
        for (int an : ans) {
            sb.append(an).append("\n");
        }

        pw.print(sb);
        pw.close();
    }

    private static void dfs(HashMap<Integer, ArrayList<int[]>> map, ArrayList<ArrayList<Integer>> edges, ArrayList<Integer> path, boolean[] visited, int[][] times, int[] lvls, int[] ans, int at) {
        visited[at] = true;
        int sz = path.size();
        path.add(at);

        if (map.containsKey(at)) {
            for (int[] b : map.get(at)) {
                if (ans[b[1]] == 0) {
                    HashSet<Integer> set = new HashSet<>(path);
                    if (set.contains(b[0])) {
                        int loc = path.indexOf(b[0]);
                        if (loc >= 0) {
                            ans[b[1]] = sz - loc;
                        }
                    } else if (isAnc(times, at, b[0])) {
                        ans[b[1]] = lvls[b[0]] - lvls[at];
                    } else {
                        int low = 0, high = sz - 1;
                        int[] temp = new int[sz + 1];
                        while (low <= high) {
                            int mid = (low + high) / 2;

                            if (temp[mid] == 0) {
                                temp[mid] = isAnc(times, path.get(mid), b[0]) ? 1 : -1;
                            }
                            int mid1 = mid + 1;
                            if (temp[mid1] == 0) {
                                temp[mid1] = isAnc(times, path.get(mid1), b[0]) ? 1 : -1;
                            }

                            if (temp[mid1] == 1) {
                                low = mid + 1;
                            } else if (temp[mid] == -1) {
                                high = mid - 1;
                            } else if (temp[mid] == 1 && temp[mid1] == -1) {
                                ans[b[1]] = lvls[b[0]] - lvls[path.get(mid)] + lvls[at] - lvls[path.get(mid)];
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int next : edges.get(at)) {
            if (!visited[next]) {
                dfs(map, edges, path, visited, times, lvls, ans, next);
            }
        }

        path.remove(sz);
    }

    private static boolean isAnc(int[][] times, int a, int b) {
        return times[a][0] < times[b][0] && times[a][1] >= times[b][1];
    }

    private static void dfs(ArrayList<ArrayList<Integer>> edges, int[][] times, int[] lvls, int at, int lvl) {
        lvls[at] = lvl;
        times[at][0] = time++;

        for (int next : edges.get(at)) {
            if (lvls[next] == 0) {
                dfs(edges, times, lvls, next, lvl + 1);
            }
        }
        times[at][1] = time - 1;
    }

    private static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
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
            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            dis.close();
        }
    }
}
