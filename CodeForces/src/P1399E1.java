import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P1399E1 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        while (t-- > 0) {
            int n = ir.nextInt();
            long s = ir.nextInt(); //replace with nextLong

            int N = n - 1;
            int[] ws = new int[N];
            int cnt[] = new int[N];
//            Edge[] edges = new Edge[N];
            ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                edges.add(new ArrayList<>());
            }
            for (int i = 0; i < N; i++) {
//                edges[i].init();
                int v = ir.nextInt() - 1;
                int u = ir.nextInt() - 1;
                int w = ir.nextInt();

                edges.get(v).add(u);
                edges.get(u).add(v);
            }

            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += cnt[i] * ws[i];
            }
        }

        pw.close();
    }

    private static void dfs() {

    }

    private static class Edge {
        int from, to, cost, cnt;

        public void init(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    private static class InputReader {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader() {
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




/*
#include <bits/stdc++.h>

using namespace std;

vector<int> w, cnt;
vector<vector<pair<int, int>>> g;

long long getdiff(int i) {
	return w[i] * 1ll * cnt[i] - (w[i] / 2) * 1ll * cnt[i];
}

void dfs(int v, int p = -1) {
	if (g[v].size() == 1) cnt[p] = 1;
	for (auto [to, id] : g[v]) {
		if (id == p) continue;
		dfs(to, id);
		if (p != -1) cnt[p] += cnt[id];
	}
}

	int t;
	cin >> t;
	while (t--) {
		int n;
		long long s;
		cin >> n >> s;
		w = cnt = vector<int>(n - 1);
		g = vector<vector<pair<int, int>>>(n);
		for (int i = 0; i < n - 1; ++i) {
			int x, y;
			cin >> x >> y >> w[i];
			--x, --y;
			g[x].push_back({y, i});
			g[y].push_back({x, i});
		}
		dfs(0);
		set<pair<long long, int>> st;
		long long cur = 0;
		for (int i = 0; i < n - 1; ++i) {
			st.insert({getdiff(i), i});
			cur += w[i] * 1ll * cnt[i];
		}
		cerr << cur << endl;
		int ans = 0;
		while (cur > s) {
			int id = st.rbegin()->second;
			st.erase(prev(st.end()));
			cur -= getdiff(id);
			w[id] /= 2;
			st.insert({getdiff(id), id});
			++ans;
		}
		cout << ans << endl;
	}

	return 0;
}
 */