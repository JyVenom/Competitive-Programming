import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P337D3 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int m = ir.nextInt();
        int d = ir.nextInt();
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());
        for (int k = 0; k < n - 1; k++) {
            int i = ir.nextInt() - 1;
            int j = ir.nextInt() - 1;
            adj.get(i).add(j);
            adj.get(j).add(i);
        }

        boolean[] marked = new boolean[n];
        while (m-- > 0) {
            marked[ir.nextInt() - 1] = true;
        }
        int[] maxDistanceDown = new int[n];
        dfs1(0, -1, maxDistanceDown, marked, adj);
        int[] maxDistanceUp = new int[n];
        maxDistanceUp[0] = marked[0] ? 0 : -1;
        dfs2(0, -1, maxDistanceUp, maxDistanceDown, marked, adj);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (maxDistanceUp[i] <= d && maxDistanceDown[i] <= d ? 1 : 0);
        }

        pw.println(ans);
        pw.close();
    }

    private static int dfs1(int v, int p, int[] maxDistanceDown, boolean[] marked, ArrayList<ArrayList<Integer>> adj) {
        maxDistanceDown[v] = marked[v] ? 0 : -1;
        for (int i = 0; i < adj.get(v).size(); i++) {
            int u = adj.get(v).get(i);
            if (u == p) continue;
            int d = dfs1(u, v, maxDistanceDown, marked, adj);
            if (d > -1) {
                maxDistanceDown[v] = Math.max(maxDistanceDown[v], d + 1);
            }
        }
        return maxDistanceDown[v];
    }

    private static void dfs2(int v, int p, int[] maxDistanceUp, int[] maxDistanceDown, boolean[] marked, ArrayList<ArrayList<Integer>> adj) {
        int mx1 = -1, mx2 = -1;
        for (int i = 0; i < adj.get(v).size(); i++) {
            int u = adj.get(v).get(i);
            if (u == p) continue;
            if (maxDistanceDown[u] > mx1) {
                mx2 = mx1;
                mx1 = maxDistanceDown[u];
            } else if (maxDistanceDown[u] > mx2) {
                mx2 = maxDistanceDown[u];
            }
        }

        for (int i = 0; i < adj.get(v).size(); i++) {
            int u = adj.get(v).get(i);
            if (u == p) continue;
            int siblingDistance = maxDistanceDown[u] == mx1 ? mx2 : mx1;
            if (siblingDistance != -1) {
                siblingDistance += 2;
            }
            maxDistanceUp[u] = siblingDistance;
            if (maxDistanceUp[v] != -1) {
                maxDistanceUp[u] = Math.max(maxDistanceUp[u], maxDistanceUp[v] + 1);
            }
            if (marked[u]) {
                maxDistanceUp[u] = Math.max(maxDistanceUp[u], 0);
            }
            dfs2(u, v, maxDistanceUp, maxDistanceDown, marked, adj);
        }
    }

    private static class InputReader2 {
        private final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
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
    }
}
