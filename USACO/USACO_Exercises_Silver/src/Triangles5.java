import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Triangles5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));

        int n = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<point>> rows = new HashMap<>();
        HashMap<Integer, ArrayList<point>> cols = new HashMap<>();
        int[][] points = new int[n][2];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());

            points[i][0] = row;
            points[i][1] = col;

            if (!rows.containsKey(row)) {
                rows.put(row, new ArrayList<>());
            }
            rows.get(row).add(new point(col, i));
            if (!cols.containsKey(col)) {
                cols.put(col, new ArrayList<>());
            }
            cols.get(col).add(new point(row, i));
        }

        int ans = 0;
        int MAX = 1000000007;
        for (int[] point : points) {
            for (point row : rows.get(point[0])) {
                long cur = Math.abs(row.val - point[1]);
                for (point col : cols.get(point[1])) {
                    long temp = cur * (long) (Math.abs(col.val - point[0]));
                    ans += temp;
                    ans = ans % MAX;
                }
            }
//            long temp = ((long) (rows.get(point[0]).size() - 1) * (long) (cols.get(point[1]).size() - 1)) % MAX;
//            ans += ((long) (rows.get(point[0]).size() - 1) * (long) (cols.get(point[1]).size() - 1)) % MAX;
        }

        pw.println(ans);
        pw.close();
    }

    private static class point {
        int val, index;

        private point(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
}

/*
#include <bits/stdc++.h>
using namespace std;

typedef long long ll;
#define f first
#define s second

const int MOD = 1e9+7;

void setIO(string s) {
  ios_base::sync_with_stdio(0); cin.tie(0);
  freopen((s+".in").c_str(),"r",stdin);
  freopen((s+".out").c_str(),"w",stdout);
}

struct mi {
  int v; explicit operator int() const { return v; }
  mi(ll _v) : v(_v%MOD) { v += (v<0)*MOD; }
  mi() : mi(0) {}
};
mi operator+(mi a, mi b) { return mi(a.v+b.v); }
mi operator-(mi a, mi b) { return mi(a.v-b.v); }
mi operator*(mi a, mi b) { return mi((ll)a.v*b.v); }

int N;
vector<pair<int,int>> v;
vector<mi> sum[100005];
vector<pair<int,int>> todo[20001];

void check() {
	for (int i = 0; i <= 20000; ++i) if (todo[i].size() > 0) {
		int sz = todo[i].size();
		sort(begin(todo[i]),end(todo[i]));
		mi cur = 0;
		for (int j = 0; j < sz; ++j)
			cur = cur+todo[i][j].f-todo[i][0].f;
		for (int j = 0; j < sz; ++j) {
			if (j) cur = cur+(2*j-sz)*(todo[i][j].f-todo[i][j-1].f);
			sum[todo[i][j].s].push_back(cur);
		}
	}
}

int main() {
	setIO("triangles");
	cin >> N; v.resize(N);
	for (int i = 0; i < N; ++i) cin >> v[i].f >> v[i].s;
	for (int i = 0; i <= 20000; ++i) todo[i].clear();
	for (int i = 0; i < N; ++i)
		todo[v[i].f+10000].push_back({v[i].s,i});
	check();
	for (int i = 0; i <= 20000; ++i) todo[i].clear();
	for (int i = 0; i < N; ++i)
		todo[v[i].s+10000].push_back({v[i].f,i});
	check();
	mi ans = 0;
	for (int i = 0; i < N; ++i) ans = ans+sum[i][0]*sum[i][1];
	cout << ans.v << "\n";
}
 */
