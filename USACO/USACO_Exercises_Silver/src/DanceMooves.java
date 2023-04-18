import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

public class DanceMooves {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int k = ir.nextInt();

        int N = n + 1;
        ArrayList<HashSet<Integer>> visited = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            visited.add(new HashSet<>());
            visited.get(i).add(i);
        }
        int[] ord = new int[N];
        for (int i = 0; i < N; i++) {
            ord[i] = i;
        }
        for (int i = 0; i < k; i++) {
            int a = ir.nextInt();
            int b = ir.nextInt();

            visited.get(ord[a]).add(b);
            visited.get(ord[b]).add(a);

            int temp = ord[a];
            ord[a] = ord[b];
            ord[b] = temp;
        }
        int[] to = new int[N];
        for (int i = 1; i < N; i++) {
            to[ord[i]] = i;
        }
        int[] num = new int[N];
        for (int i = 1; i < N; i++) {
            if (num[i] != 0) {
                continue;
            }

            ArrayList<Integer> path = new ArrayList<>();
            int temp = i;
            do {
                temp = to[temp];
                path.add(temp);
                visited.get(i).addAll(visited.get(temp));
            }
            while (temp != i);
            for (int loc : path) {
                num[loc] = visited.get(i).size();
            }
        }

        for (int i = 1; i < N; i++) {
            pw.println(num[i]);
        }
        pw.close();
    }

    private static class InputReader {
        final private int BUFFER_SIZE = 1 << 24;
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
