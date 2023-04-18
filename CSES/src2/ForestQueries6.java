import java.io.*;
import java.util.StringTokenizer;

public class ForestQueries6 {
	static Kattio io = new Kattio();

	public static void main(String[] args) throws IOException {
		PrintWriter pw = new PrintWriter(System.out);

		int n = io.nextInt();
		int q = io.nextInt();
		int N = n + 1;
		int[][] forest = new int[N][N];
		for (int i = 1; i < N; i++) {
			String row = io.nextLine();
			for (int j = 1; j < N; j++) {
				if (row.charAt(j - 1) == '*') {
					forest[i][j] = 1;
				}
			}
		}

		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				forest[i][j] += forest[i][j - 1];
			}
		}
		for (int j = 1; j < N; j++) {
			for (int i = 1; i < N; i++) {
				forest[i][j] += forest[i - 1][j];
			}
		}
		StringBuilder sb = new StringBuilder();
		while (q-- > 0) {
			int y1 = io.nextInt();
			int x1 = io.nextInt();
			int y2 = io.nextInt();
			int x2 = io.nextInt();
			sb.append(forest[y2][x2] -
					forest[y1 - 1][x2] -
					forest[y2][x1 - 1] +
					forest[y1 - 1][x1 - 1]).append("\n");
		}

		pw.println(sb);
		pw.close();
	}

	static class Kattio extends PrintWriter {
		private final BufferedReader r;
		private StringTokenizer st;

		// standard input
		public Kattio() {
			this(System.in, System.out);
		}

		public Kattio(InputStream i, OutputStream o) {
			super(o);
			r = new BufferedReader(new InputStreamReader(i));
		}

		// returns null if no more input
		public String next() {
			try {
				while (st == null || !st.hasMoreTokens())
					st = new StringTokenizer(r.readLine());
				return st.nextToken();
			} catch (Exception ignored) {
			}
			return null;
		}

		public String nextLine() {
			try {
				return r.readLine();
			} catch (Exception ignored) {
			}
			return null;
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}
	}
}
