import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class StuckInARut4 {
    public static void main(String[] args) throws IOException {
        InputReader ir = new InputReader();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int n = ir.nextInt();
        cow[] cows = new cow[n];
        for (int i = 0; i < n; i++)
            cows[i] = new cow(ir.nextChar() == 78, ir.nextInt(), ir.nextInt(), i);

        ArrayList<cow> curEast = new ArrayList<>();
        Arrays.sort(cows, Comparator.comparingInt(o -> o.x));
        int[] stopped = new int[n];
        for (int i = 0; i < n; i++) {
            if (cows[i].d) {
                for (int j = binSearch(curEast, cows[i].y); j < curEast.size(); j++) {
                    int x = cows[i].x - curEast.get(j).x;
                    int y = curEast.get(j).y - cows[i].y;
                    if (x < y) {
                        stopped[curEast.get(j).id] += (stopped[cows[i].id] + 1);
                        break;
                    } else if (y < x) {
                        stopped[cows[i].id] += (stopped[curEast.get(j).id] + 1);
                        curEast.remove(j);
                        j--;
                    }
                }
            } else {
                curEast.add(cows[i]);
                curEast.sort(Comparator.comparingInt(o -> o.y));
            }
        }

        for (int num : stopped)
            pw.println(num);
        pw.close();
    }

    private static int binSearch(ArrayList<cow> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid).y < key)
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    private static class cow {
        int x, y, id;
        boolean d;

        public cow(boolean d, int x, int y, int id) {
            this.d = d;
            this.x = x;
            this.y = y;
            this.id = id;
        }
    }

    private static class InputReader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            return ret;
        }

        public char nextChar() throws IOException {
            return (char) read();
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
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
