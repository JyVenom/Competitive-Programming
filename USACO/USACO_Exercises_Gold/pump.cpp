#include <cstring>
#include <iostream>
#include <queue>

using namespace std;

typedef pair<int, int> pii;
typedef pair<int, pii> edge; // <flow, cost>
typedef pair<int, pii> vertex; // <vertex, flow>

int dp[1001][1001];
vector<edge> edges[1001];
int main() {
  freopen("pump.in", "r", stdin);
  freopen("pump.out", "w", stdout);
  memset(dp, 1, sizeof(dp));
  int n, m;
  cin >> n >> m;
  dp[1][1000] = 0;
  while(m--) {
    int a, b, c, f;
    cin >> a >> b >> c >> f;
    edges[a].push_back(edge(b, {f, c}));
    edges[b].push_back(edge(a, {f, c}));
  }
  priority_queue<vertex> q;
  q.push(vertex(0, {1, 1000}));
  double ret = -1;
  while(q.size()) {
    vertex curr = q.top(); q.pop();
    if(curr.second.first == n) {
      ret = max(ret, curr.second.second / (double)dp[curr.second.first][curr.second.second]);
      continue;
    }
    for(edge out: edges[curr.second.first]) {
      int nf = min(out.second.first, curr.second.second);
      int nc = dp[curr.second.first][curr.second.second] + out.second.second;
      int nd = out.first;
      if(nc < dp[nd][nf]) {
        dp[nd][nf] = nc;
        q.push(vertex(-dp[nd][nf], {nd, nf}));
      }
    }
  }
  cout << (int)(1000000 * ret) << "\n";
}