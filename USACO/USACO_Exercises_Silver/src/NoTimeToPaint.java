import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class NoTimeToPaint {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int q = ir.nextInt();
        int[] fence = new int[n];
        for (int i = 0; i < n; i++) {
            fence[i] = ir.nextChar();
        }

        HashSet<Integer> helper = new HashSet<>();
        ArrayList<int[]> changes = new ArrayList<>();
        int prev = -1;
        int[] fow = new int[n];
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            helper.add(fence[i]);
            ArrayList<Integer> colors = new ArrayList<>(helper);
            Collections.sort(colors);
            if (fence[i] != prev) {
                prev = fence[i];
                changes.add(new int[]{i, prev});
            }
            fow[i] = findCost(colors, changes);
        }


        StringBuilder sb = new StringBuilder();


        pw.println();
        pw.close();
    }

    private static int findCost(ArrayList<Integer> colors, ArrayList<int[]> changes) {
        int count = 1;
        for (int i = 1; i < colors.size(); i++) {
            int start = 0;
            for (int j = 0; j < changes.size(); j++) {
                if (changes.get(j)[1] == colors.get(i)) {
                    start = j + 1;
                    break;
                }
            }
            count++;
            for (int j = start; j < changes.size(); j++) {
                if (changes.get(j)[1] < colors.get(i)) {
                    for (int k = j + 1; k < changes.size(); k++) {
                        if (changes.get(k)[1] == colors.get(i)) {
                            j = k;
                            count++;
                            break;
                        }
                    }
                }
            }
        }
        return count;
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

        private int nextChar() throws IOException {
            return (read() - 'A');
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
