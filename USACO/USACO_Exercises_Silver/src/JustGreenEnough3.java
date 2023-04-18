//import java.io.DataInputStream;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Arrays;
//
//public class JustGreenEnough2 {
//    public static void main(String[] args) throws IOException {
//        InputReader2 ir = new InputReader2();
//        PrintWriter pw = new PrintWriter(System.out);
//
//        int n = ir.nextInt();
//        int[][] data = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                data[i][j] = ir.nextInt();
//            }
//        }
//
//        int n2 = 2 * n + 1;
//        boolean[][] ok = new boolean[n2][n2];
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                ok[i][j] = data[i][j] >= 100;
//            }
//        }
//        long ans = solve();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                ok[i][j] = data[i][j] > 100;
//            }
//        }
//        ans -= solve();
//
//        pw.println(ans);
//        pw.close();
//    }
//
//
//    private static long solve(boolean[][] ok, int N) {
//        long ans = 0;
//        int[] lst = new int[N];
//        Arrays.fill(lst, N - 1);
//        int[] to_add = new int[1000];
//        for (int i = N - 1; i >= 0; --i) {
//            for (int k = 0; k < N; ++k) {
//                if (!ok[i][k]) lst[k] = i - 1;
//                else to_add[lst[k]]=k;
//            }
//            int sum_comb = 0;
//            int[] lef = new int[N];
//            Arrays.fill(lef, -1);
//            int[] rig = new int[N];
//            Arrays.fill(lef, -1);
//            for (int j = N - 1; j >= i; --j) {
//                for (int k : to_add[j]) {
//                    // all_ones_{i,j+1}[k] = 0, all_ones_{i,j}[k] = 1
//                    int l = k, r = k;
//                    var c2 = []( int x){
//                        return (x + 1) * (x + 2) / 2;
//                    }
//                    if (k && lef[k - 1] != -1) {
//                        l = lef[k - 1];
//                        sum_comb -= c2(k - 1 - l);
//                    }
//                    if (k + 1 < N && rig[k + 1] != -1) {
//                        r = rig[k + 1];
//                        sum_comb -= c2(r - k - 1);
//                    }
//                    lef[r] = l, rig[l] = r;
//                    sum_comb += c2(r - l);
//                }
//                ans += sum_comb;
//            }
//        }
//        return ans;
//    }
//
//    private static class InputReader2 {
//        final private int BUFFER_SIZE = 1 << 16;
//        private final DataInputStream dis;
//        private final byte[] buffer;
//        private int bufferPointer, bytesRead;
//
//        public InputReader2() {
//            dis = new DataInputStream(System.in);
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
//        public InputReader2(String file_name) throws IOException {
//            dis = new DataInputStream(new FileInputStream(file_name));
//            buffer = new byte[BUFFER_SIZE];
//            bufferPointer = bytesRead = 0;
//        }
//
////        private String nextLine() throws IOException {
////            byte[] buf = new byte[BUFFER_SIZE]; // line length
////            int cnt = 0, c;
////            while ((c = read()) != -1) {
////                if (c == '\n')
////                    break;
////                buf[cnt++] = (byte) c;
////            }
////            return new String(buf, 0, cnt);
////        }
//
//        private String nextLine() throws IOException {
//            int c = read();
//            while (isSpaceChar(c)) {
//                c = read();
//            }
//            StringBuilder res = new StringBuilder();
//            do {
//                res.appendCodePoint(c);
//                c = read();
//            } while (!isSpaceChar(c));
//            return res.toString();
//        }
//
//        private boolean isSpaceChar(int c) {
//            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
//        }
//
//        private int nextInt() throws IOException {
//            int ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            } while ((c = read()) >= '0' && c <= '9');
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        private long nextLong() throws IOException {
//            long ret = 0;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        private double nextDouble() throws IOException {
//            double ret = 0, div = 1;
//            byte c = read();
//            while (c <= ' ')
//                c = read();
//            boolean neg = (c == '-');
//            if (neg)
//                c = read();
//            do {
//                ret = ret * 10 + c - '0';
//            }
//            while ((c = read()) >= '0' && c <= '9');
//            if (c == '.') {
//                while ((c = read()) >= '0' && c <= '9') {
//                    ret += (c - '0') / (div *= 10);
//                }
//            }
//            if (neg)
//                return -ret;
//            return ret;
//        }
//
//        private void fillBuffer() throws IOException {
//            bytesRead = dis.read(buffer, bufferPointer = 0, BUFFER_SIZE);
//            if (bytesRead == -1)
//                buffer[0] = -1;
//        }
//
//        private byte read() throws IOException {
//            if (bufferPointer == bytesRead)
//                fillBuffer();
//            return buffer[bufferPointer++];
//        }
//
//        private void close() throws IOException {
//            dis.close();
//        }
//    }
//}
//
//
//
//
//
///*
//
//using ll = long long;
//
//int N;
//bool ok[1000][1000];
//
//ll solve() {
//	ll ans = 0;
//	vector<int> lst(N,N-1);
//	vector<int> to_add[1000];
//	for (int i = N-1; i >= 0; --i) {
//		for (int j = i; j < N; ++j) to_add[j].clear();
//		for (int k = 0; k < N; ++k) {
//			if (ok[i][k] == 0) lst[k] = i-1;
//			else to_add[lst[k]].push_back(k);
//		}
//		int sum_comb = 0;
//		vector<int> lef(N,-1), rig(N,-1);
//		for (int j = N-1; j >= i; --j) {
//			for (int k: to_add[j]) {
//				// all_ones_{i,j+1}[k] = 0, all_ones_{i,j}[k] = 1
//				int l = k, r = k;
//				auto c2 = [](int x) { return (x+1)*(x+2)/2; };
//				if (k && lef[k-1] != -1) {
//					l = lef[k-1];
//					sum_comb -= c2(k-1-l);
//				}
//				if (k+1 < N && rig[k+1] != -1) {
//					r = rig[k+1];
//					sum_comb -= c2(r-k-1);
//				}
//				lef[r] = l, rig[l] = r;
//				sum_comb += c2(r-l);
//			}
//			ans += sum_comb;
//		}
//	}
//	return ans;
//}
//
//int main() {
//	cin >> N;
//	vector<vector<int>> pasture(N,vector<int>(N));
//	for (vector<int>& a: pasture)
//		for (int& b: a) cin >> b;
//
//	for (int i = 0; i < N; ++i)
//		for (int j = 0; j < N; ++j)
//			ok[i][j] = pasture[i][j] >= 100;
//	ll ans = solve();
//
//	for (int i = 0; i < N; ++i)
//		for (int j = 0; j < N; ++j)
//			ok[i][j] = pasture[i][j] > 100;
//	ans -= solve();
//
//	cout << ans << "\n";
//}
// */
