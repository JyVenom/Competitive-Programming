import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Cipher2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);


        ArrayList<Integer> all = new ArrayList<>(20000);
        HashMap<Integer, Integer> map = new HashMap<>(20000);
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);

            String type = st.nextToken();
            if (type.equals("ADD")) {
                int tmp = Integer.parseInt(st.nextToken());

                if (!map.containsKey(tmp)) {
                    map.put(tmp, 0);
                    all.add(binSearch(all, tmp), tmp);
                }
                map.replace(tmp, map.get(tmp) + 1);
            } else if (type.equals("REMOVE")) {
                int tmp = Integer.parseInt(st.nextToken());

                map.replace(tmp, map.get(tmp) - 1);
                if (map.get(tmp) == 0) {
                    map.remove(tmp);
                    all.remove(binSearch(all, tmp));
                }
            } else {
                st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                st.nextToken();
                int b = Integer.parseInt(st.nextToken());

                int start = binSearchStart(all, a);
                int end = binSearchEnd(all, b);
                if (start == end) {
                    pw.println(0);
                } else {
                    int val = all.get(start);
                    int amt = map.get(val);
                    int cur = val;
                    for (int i = 1; i < amt; i++) {
                        cur ^= val;
                    }
                    for (int i = start + 1; i < end; i++) {
                        val = all.get(i);
                        amt = map.get(val);
                        for (int j = 0; j < amt; j++) {
                            cur ^= val;
                        }
                    }
                    pw.println(cur);
                }
            }
        }

        pw.close();
    }

    private static int binSearch(ArrayList<Integer> arr, int key) {
        int low = 0, high = arr.size() - 1;

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

    private static int binSearchStart(ArrayList<Integer> arr, int key) {
        int low = 0, high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) < key) {
                low = mid + 1;
            } else if (arr.get(mid) >= key) {
                high = mid - 1;
            }
        }
        return low;
    }

    private static int binSearchEnd(ArrayList<Integer> arr, int key) {
        int low = 0, high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) <= key) {
                low = mid + 1;
            } else if (arr.get(mid) > key) {
                high = mid - 1;
            }
        }
        return low;
    }
}
