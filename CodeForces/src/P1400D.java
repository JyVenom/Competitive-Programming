import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class P1400D {
    public static void main(String[] args) throws IOException {
        InputReader2 ir = new InputReader2();
        PrintWriter pw = new PrintWriter(System.out);

        int t = ir.nextInt();
        for (int i = 0; i < t; i++) {
            int n = ir.nextInt();
            HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
            int[] arr = new int[n];
            for (int j = 0; j < n; j++) {
                arr[j] = ir.nextInt();

                if (!map.containsKey(arr[j])) {
                    map.put(arr[j], new ArrayList<>());
                }
                map.get(arr[j]).add(j);
            }

            long ans = 0;
            for (int key : map.keySet()) {
                if (map.get(key).size() >= 4) {
                    ans += fact(map.get(key).size());
                }
            }
            ArrayList<Integer> keySet = new ArrayList<>(map.keySet());
            for (int j = 0; j < keySet.size(); j++) {
                int keyI = keySet.get(j);
                if (map.get(keyI).size() < 2) {
                    continue;
                }

                for (int a = 0; a < map.get(keyI).size(); a++) {
                    for (int b = a + 1; b < map.get(keyI).size(); b++) {
                        for (int k = 0; k < keySet.size(); k++) {
                            if (k == j) {
                                continue;
                            }
                            int keyJ = keySet.get(k);
                            if (map.get(keyJ).size() < 2) {
                                continue;
                            }
                            int cnt = 0;
                            for (int l = 0; l < map.get(keyJ).size(); l++) {
                                if (map.get(keyJ).get(l) > map.get(keyI).get(b)) {
                                    ans += (long) (map.get(keyJ).size() - l) * cnt;
                                    break;
                                } else if (map.get(keyJ).get(l) > map.get(keyI).get(a)) {
                                    cnt++;
                                }
                            }
                        }
                    }
                }
            }

            pw.println(ans);
        }

        pw.close();
    }

    private static long fact(int a) {
        int b = a - 4;
        long res = 1;
        if (b > 4) {
            for (int i = b + 1; i <= a; i++) {
                res *= i;
            }
            for (int i = 2; i <= 4; i++) {
                res /= i;
            }

        } else {
            for (int i = 4 + 1; i <= a; i++) {
                res *= i;
            }
            for (int i = 2; i <= b; i++) {
                res /= i;
            }
        }
        return res;
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
