import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class FarmerJohnSolves3SUM {
    public static void main(String[] args) throws IOException {
        Reader r = new Reader("threesum.in");
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")));

        int n = r.nextInt();
        int q = r.nextInt();
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = r.nextInt();
            nums[i][1] = i;
        }
        int[][] ranges = new int[q][2];
        for (int i = 0; i < q; i++) {
            ranges[i][0] = r.nextInt() - 1;
            ranges[i][1] = r.nextInt() - 1;
        }

        ArrayList<int[]> all = threeSum(nums);
        all.sort(Comparator.comparingInt(o -> o[1]));
        all.sort(Comparator.comparingInt(o -> o[0]));
        int high = all.size() - 1;
        for (int[] range : ranges) {
            int start = findStart(all, range[0], high);
//            int end = findEnd(all, range[1], high);

            if (start == -1) {
                pw.println(0);
            }
            else {
                int count = 0;
                for (int i = start; i < all.size(); i++) {
                    if (all.get(start)[0] >= range[0]) {
                        break;
                    }
                    if (all.get(start)[0] >= range[0] && all.get(start)[1] <= range[1]) {
                        count++;
                    }
                }
                pw.println(count);
            }
        }

        pw.println(all);
        pw.close();
    }

    private static int findStart(ArrayList<int[]> arr, int key, int high) {
        int low = 0;
        int[] res = new int[arr.size()];

        while (low <= high) {
            int mid = (low + high) / 2;

            if (res[mid] == 0) {
                if (arr.get(mid)[0] < key) {
                    res[mid] = -1;
                } else if (arr.get(mid)[0] >= key) {
                    res[mid] = 1;
                }
            }
            if (mid > 0 && res[mid - 1] == 0) {
                if (arr.get(mid - 1)[0] < key) {
                    res[mid - 1] = -1;
                } else if (arr.get(mid - 1)[0] >= key) {
                    res[mid - 1] = 1;
                }
            }

            if (res[mid] == 1) {
                if (mid == 0) {
                    return mid;
                }
                else {
                    if (res[mid - 1] == -1) {
                        return mid;
                    }
                    else {
                        high = mid - 1;
                    }
                }
            }
            else {
                low = mid + 1;
            }
        }
        return -1;
    }

    private static int findEnd(ArrayList<int[]> arr, int key, int high) {
        int low = 0;
        int[] res = new int[arr.size()];
        int max = high;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (res[mid] == 0) {
                if (arr.get(mid)[1] <= key) {
                    res[mid] = -1;
                } else if (arr.get(mid)[1] > key) {
                    res[mid] = 1;
                }
            }
            if (mid > 0 && res[mid - 1] == 0) {
                if (arr.get(mid - 1)[1] <= key) {
                    res[mid - 1] = -1;
                } else if (arr.get(mid - 1)[1] > key) {
                    res[mid - 1] = 1;
                }
            }

            if (res[mid] == -1) {
                if (mid == max) {
                    return mid;
                }
                else {
                    if (res[mid + 1] == 1) {
                        return mid;
                    }
                    else {
                        low = mid + 1;
                    }
                }
            }
            else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static ArrayList<int[]> threeSum(int[][] nums) {
        Arrays.sort(nums, Comparator.comparingInt(o -> o[0]));
        ArrayList<int[]> result = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {

                if (nums[i][0] + nums[j][0] + nums[k][0] > 0) {
                    k--;
                } else if (nums[i][0] + nums[j][0] + nums[k][0] < 0) {
                    j++;
                } else {
                    if (nums[j][0] == nums[k][0]) {
                        for (int l = k; l > j; l--) {
                            for (int m = j; m < l; m++) {
                                int[] temp = new int[2];
                                temp[0] = Math.min(nums[i][1], Math.min(nums[m][1], nums[l][1]));
                                temp[1] = Math.max(nums[i][1], Math.max(nums[m][1], nums[l][1]));
                                result.add(temp);
                            }
                        }
                        break;
                    }
                    else {
                        int J = j + 1;
                        int K = k - 1;
                        while (nums[J][0] == nums[j][0]) {
                            J++;
                        }
                        while (nums[K][0] == nums[k][0]) {
                            K--;
                        }
                        for (int l = j; l < J; l++) {
                            for (int m = K + 1; m <= k; m++) {
                                int[] temp = new int[2];
                                temp[0] = Math.min(nums[i][1], Math.min(nums[l][1], nums[m][1]));
                                temp[1] = Math.max(nums[i][1], Math.max(nums[l][1], nums[m][1]));
                                result.add(temp);
                            }
                        }
                        if (J >= K) {
                            break;
                        }
                        else {
                            j = J;
                            k = K;
                        }
                    }
                }
            }
        }

        return result;
    }

//    private static ArrayList<ArrayList<Integer>> threeSum(int[] nums) {
//        Arrays.sort(nums);
//
//        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
//
//        for (int i = 0; i < nums.length; i++) {
//            int j = i + 1;
//            int k = nums.length - 1;
//
//            if (i > 0 && nums[i] == nums[i - 1]) {
//                continue;
//            }
//
//            while (j < k) {
//                if (k < nums.length - 1 && nums[k] == nums[k + 1]) {
//                    k--;
//                    continue;
//                }
//
//                if (nums[i] + nums[j] + nums[k] > 0) {
//                    k--;
//                } else if (nums[i] + nums[j] + nums[k] < 0) {
//                    j++;
//                } else {
//                    ArrayList<Integer> l = new ArrayList<>();
//                    l.add(nums[i]);
//                    l.add(nums[j]);
//                    l.add(nums[k]);
//                    result.add(l);
//                    j++;
//                    k--;
//                }
//            }
//        }
//
//        return result;
//    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 24;
        private final DataInputStream din;
        private final byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
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

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
            if (neg)
                return -ret;
            return ret;
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

        public void close() throws IOException {
            din.close();
        }
    }
}
