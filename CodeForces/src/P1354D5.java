import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P1354D5 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int q = ir.nextInt();
        sortedList list = new sortedList(n + 1);
        for (int i = 0; i < n; i++) {
            list.add(ir.nextInt());
        }

        for (int i = 0; i < q; i++) {
            int query = ir.nextInt();
            if (query < 0) {
                list.remove(-query);
            } else {
                list.add(query);
            }
        }

        pw.println(list.size() == 0 ? 0 : list.get());
        pw.close();
    }

    private static class sortedList {
        private static int[] num;
        private static ArrayList<Integer> list;

        public sortedList(int n) {
            num = new int[n];
            list = new ArrayList<>();
        }

        private void add(int element) {
            if (num[element] == 0) {
                list.add(binSearch(list, element), element);
            }
            num[element]++;
        }

        private void remove(int loc) {
            for (int i = 0; i < list.size(); i++) {
                int key = list.get(i);
                loc -= num[key];
                if (loc <= 0) {
                    num[key]--;
                    if (num[key] == 0) {
                        list.remove(i);
                    }
                    break;
                }
            }
        }

        private int size() {
            return list.size();
        }

        private int get() {
            return list.get(0);
        }

        private int binSearch(ArrayList<Integer> arr, int key) {
            int low = 0;
            int high = arr.size() - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                if (arr.get(mid) < key) {
                    low = mid + 1;
                } else if (arr.get(mid) > key) {
                    high = mid - 1;
                } else if (arr.get(mid) == key) {
                    return mid;
                }
            }
            return low;
        }
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 16;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
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
