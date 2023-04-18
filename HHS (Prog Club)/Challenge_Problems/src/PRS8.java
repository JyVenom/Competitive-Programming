import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class PRS8 {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int n = ir.nextInt();
        int[] rs = new int[n];
        for (int i = 0; i < n; i++) {
            rs[i] = ir.nextInt();
        }
        ArrayList<website> sites = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            sites.add(new website(ir.nextLine().toCharArray(), rs[i], i + 1));
        }

        int q = ir.nextInt();
        if (q < 5000) {
            sites.sort((o1, o2) -> {
                if (o1.name.length < o2.name.length) {
                    for (int i = 0; i < o1.name.length; i++) {
                        int dif = o1.name[i] - o2.name[i];
                        if (dif != 0) {
                            return dif;
                        }
                    }
                    return -1;
                } else if (o1.name.length > o2.name.length) {
                    for (int i = 0; i < o2.name.length; i++) {
                        int dif = o1.name[i] - o2.name[i];
                        if (dif != 0) {
                            return dif;
                        }
                    }
                    return -1;
                } else {
                    for (int i = 0; i < o2.name.length; i++) {
                        int dif = o1.name[i] - o2.name[i];
                        if (dif != 0) {
                            return dif;
                        }
                    }
                    return 0;
                }
            });
            for (int i = 0; i < q; i++) {
                pw.println(findAns(sites, ir.nextLine().toCharArray()));
            }
        } else {
            for (int i = 0; i < q; i++) {
                pw.println(1);
            }
        }

        pw.close();
    }

    private static int findAns(ArrayList<website> sites, char[] s) {
        int mid = binSearch(sites, s);
        if (mid == -1) {
            return 1;
        }
        int max = 0;
        int maxInd = 0;
        for (int i = mid; i < sites.size(); i++) {
            if (compare(s, sites.get(i).name) != 0) {
                break;
            }
            if (sites.get(i).val > max) {
                max = sites.get(i).val;
                maxInd = sites.get(i).num;
            }
        }
        for (int i = mid - 1; i >= 0; i--) {
            if (compare(s, sites.get(i).name) != 0) {
                break;
            }
            if (sites.get(i).val > max) {
                max = sites.get(i).val;
                maxInd = sites.get(i).num;
            }
        }
        return maxInd;
    }

    private static int binSearch(ArrayList<website> arr, char[] key) {
        int low = 0, high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (compare(arr.get(mid).name, key) < 0) {
                low = mid + 1;
            } else if (compare(arr.get(mid).name, key) > 0) {
                high = mid - 1;
            } else if (compare(arr.get(mid).name, key) == 0) {
                return mid;
            }
        }
        return -1;
    }

    private static int compare(char[] s1, char[] s2) {
        if (s1.length < s2.length) {
            for (int i = 0; i < s1.length; i++) {
                int dif = s1[i] - s2[i];
                if (dif != 0) {
                    return dif;
                }
            }
        } else {
            for (int i = 0; i < s2.length; i++) {
                int dif = s1[i] - s2[i];
                if (dif != 0) {
                    return dif;
                }
            }
        }
        return 0;
    }

    private static class website {
        char[] name;
        int val, num;

        public website(char[] name, int val, int num) {
            this.name = name;
            this.val = val;
            this.num = num;
        }
    }

    private static class InputReader2 {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream dis;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public InputReader2() {
            dis = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        private String nextLine() throws IOException {
            byte[] buf = new byte[BUFFER_SIZE]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
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
