#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int dr[] = {-1, 0, 1, 0};
int dc[] = {0, -1, 0, 1};

struct state {
  int r;
  int c;
  int ld;
  bool smell;

  state(int r, int c, int ld, bool smell) :
      r(r), c(c), ld(ld), smell(smell) {
  }

  int pack() {
    return (smell ? 1 : 0) + 2 * (ld + 1) + 10 * c + 10000 * r;
  }

  static state unpack(int x) {
    return state(x / 10000, (x / 10) % 1000,
                 (x / 2) % 5 - 1, x & 1);
  }
};

int getcell(const vector<vector<int>>& A, int r, int c) {
  if (r < 0 || r >= A.size() || c < 0 || c >= A[r].size()) {
    return 0;
  }
  return A[r][c];
}

int main() {
  ios_base::sync_with_stdio(false);

  int N, M;
  cin >> N >> M;
  vector<vector<int>> A(N, vector<int>(M));
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      cin >> A[i][j];
    }
  }
  queue<int> q;
  vector<int> D(10000000, -1);

  int s = state(0, 0, -1, false).pack();
  q.push(s);
  D[s] = 0;

  while (!q.empty()) {
    state st = state::unpack(q.front());
    q.pop();

    if (st.r == N - 1 && st.c == M - 1) {
      cout << D[st.pack()] << '\n';
      return 0;
    }
    if (A[st.r][st.c] == 4 && st.ld != -1) {
      int col = getcell(A, st.r + dr[st.ld], st.c + dc[st.ld]);
      if (col != 0 && col != 3) {
        state nst = state(st.r + dr[st.ld], st.c + dc[st.ld], st.ld, col == 2);
        if (D[nst.pack()] != -1) {
          continue;
        }
        D[nst.pack()] = D[st.pack()] + 1;
        q.push(nst.pack());
        continue;
      }
    }
    for (int i = 0; i < 4; i++) {
      int col = getcell(A, st.r + dr[i], st.c + dc[i]);
      if (col == 0 || (col == 3 && !st.smell)) {
        continue;
      }
      state nst = state(st.r + dr[i], st.c + dc[i], i,
                        col == 2 ? true : col == 4 ? false : st.smell);
      if (D[nst.pack()] != -1) {
        continue;
      }
      D[nst.pack()] = D[st.pack()] + 1;
      q.push(nst.pack());
    }
  }
  cout << "-1\n";
  return 0;
}
//Here is my code, which takes the first approach, using the Java PriorityQueue so that Bessie's slides can be processed all at once. I'm sorry for the awful butchery of implementing 4-tuples:
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//public class DEC15GoldA {
//	static final int N = 1005;
//
//	static int[][] tile;
//	static int[][][] visited;
//	static int[][] dirs = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
//	static int n, m;
//
//	static PriorityQueue<Pair<Integer, Pair<Integer, Pair<Integer, Integer>>>> q;
//
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//
//		n = Integer.parseInt(st.nextToken());
//		m = Integer.parseInt(st.nextToken());
//		q = new PriorityQueue<>();
//
//		tile = new int[N][N];
//		visited = new int[N][N][2];
//
//		for (int i=0; i<n; ++i) {
//			st = new StringTokenizer(br.readLine());
//			for (int j=0; j<m; ++j) {
//				int color = Integer.parseInt(st.nextToken());
//				tile[i][j] = color;
//				visited[i][j][0] = visited[i][j][1] = 100000000;
//			}
//		}
//		q.add(fromInts(0, 0, 0, 0));
//
//		while (q.size() > 0) {
//			Pair<Integer, Pair<Integer, Pair<Integer, Integer>>> front = q.remove();
//			int dist = front.left;
//			int x = front.right.left;
//			int y = front.right.right.left;
//			int isSmelly = front.right.right.right;
//
//			if (x == m-1 && y == n-1) {
//				System.out.println(dist);
//				return;
//			}
//			if (visited[y][x][isSmelly] != 100000000) {
//				continue;
//			}
//			visited[y][x][isSmelly] = dist;
//
//			for (int i=0; i<dirs.length; ++i) {
//				int nx = x + dirs[i][0];
//				int ny = y + dirs[i][1];
//				int nd = dist + 1;
//				int nSmelly = isSmelly;
//
//				if (!isPathable(nx, ny, isSmelly)) continue;
//
//				if (tile[ny][nx] == 4) {
//					while (isPathable(nx + dirs[i][0], ny + dirs[i][1], isSmelly) &&
//							tile[ny][nx] == 4) {
//						nx += dirs[i][0];
//						ny += dirs[i][1];
//						nd ++;
//						nSmelly = 0;
//					}
//				}
//				if (tile[ny][nx] == 2) {
//					nSmelly = 1;
//				}
//				if (visited[ny][nx][nSmelly] <= nd) continue;
//				q.add(fromInts(nd, nx, ny, nSmelly));
//			}
//		}
//		System.out.println("-1");
//	}
//	public static boolean isPathable(int x, int y, int smellsNice) {
//		if (x < 0 || x >= m || y < 0 || y >= n) return false;
//		if (tile[y][x] == 0) return false;
//		if (tile[y][x] == 3) return (smellsNice > 0);
//
//		return true;
//	}
//	public static Pair<Integer, Pair<Integer, Pair<Integer, Integer>>> fromInts(int a, int b, int c, int d) {
//		Pair<Integer, Integer> p1 = new Pair<>(c, d);
//		Pair<Integer, Pair<Integer, Integer>> p2 = new Pair<>(b, p1);
//		return new Pair<>(a, p2);
//	}
//}
//class Pair<L extends Comparable<L>, R> implements Comparable<Pair<L, R>> {
//	public L left;
//	public R right;
//
//	public Pair(L left, R right) {
//		this.left = left;
//		this.right = right;
//	}
//	public int compareTo(Pair<L, R> other) {
//		return this.left.compareTo(other.left);
//	}
//}