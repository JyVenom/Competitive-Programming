import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class P455B2 {
    private static final int max_len = 100500;
    private static final int max_alpha = 26;

    private static final int[][] t = new int[max_len][max_alpha];
    private static final boolean[] win = new boolean[max_len];
    private static final boolean[] lose = new boolean[max_len];
    private static int szT;
    private static int root;

    private static int newT() {
        return ++szT;
    }

    private static void addTrie(char[] s) {
        int v = root;
        for (char value : s) {
            int c = value - 'a';
            if (t[v][c] == 0) t[v][c] = newT();
            v = t[v][c];
        }
    }

    private static void dfs(int v) {
        win[v] = false;
        lose[v] = false;
        boolean isLeaf = true; //
        for (int i = 0; i < max_alpha; i++) {
            if (t[v][i] != 0) {
                isLeaf = false;
                int to = t[v][i];
                dfs(to);
                win[v] |= !win[to];
                lose[v] |= !lose[to];
            }
        }
        if (isLeaf) {
            lose[v] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();
        root = newT();
        for (int i = 1; i <= n; i++) {
            addTrie(ir.nextLine().toCharArray());
        }
        dfs(root);
        if (k == 1) {
            pw.println(win[root] ? "First" : "Second");
        } else if (!win[root]) {
            pw.println(win[root] ? "First" : "Second");
        } else if (lose[root]) {
            pw.println(win[root] ? "First" : "Second");
        } else if (k % 2 == 1) {
            pw.println(win[root] ? "First" : "Second");
        } else {
            pw.println(win[root] ? "Second" : "First");
        }
        pw.close();
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

        private String nextLine() throws IOException {
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

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
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
template<class T> T max(T a, T b, T c) {
    return max(a, max(b, c));
}
 
int nextInt() {
    int x = 0, p = 1;
    char c;
    do {
        c = getchar();
    } while (c <= 32);
    if (c == '-') {
        p = -1;
        c = getchar();
    }
    while (c >= '0' && c <= '9') {
        x = x * 10 + c - '0';
        c = getchar();
    }
    return x * p;
}
 
const int max_len = 100500, max_alpha = 26;
 
int t[max_len][max_alpha];
int szT;
 
int newT() {
    return ++szT;
}
 
int n, k, root;
char s[max_len];
boolean win[max_len], lose[max_len];
 
void addTrie(char * s) {
    int len = strlen(s);
    int v = root;
    for (int i = 0; i < len; i++) {
        int c = s[i] - 'a';
        if (t[v][c] == 0) t[v][c] = newT();
        v = t[v][c];
    }
}
 
void dfs(int v) {
    win[v] = false;
    lose[v] = false;
    boolean isLeaf = true; //
    for (int i = 0; i < max_alpha; i++) {
        if (t[v][i] != 0) {
            isLeaf = false;
            int to = t[v][i];
            dfs(to);
            win[v] |= !win[to];
            lose[v] |= !lose[to];
        }
    }
    if (isLeaf) {
        lose[v] = true;
    }
}
 
void answer(boolean res) {
    puts(res ? "First" : "Second");
    exit(0);
}
 
int main() {
    //freopen("input.txt", "r", stdin);
    n = nextInt();
    k = nextInt();
    root = newT();
    for (int i = 1; i <= n; i++) {
        scanf("%s", s);
        addTrie(s);
    }
    dfs(root);
    if (k == 1) answer(win[root]); //1 game
    else if (!win[root]) answer(win[root]); //answer(0);
    else if (lose[root]) answer(win[root]); //answer(1);
    else if (k % 2 == 1) answer(win[root]); //answer(1)
    else answer(!win[root]); //answer(0)
}
 */
