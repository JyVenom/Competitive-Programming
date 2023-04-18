import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Cipher5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        LinkedList<Integer> all = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);

            String type = st.nextToken();
            if (type.equals("ADD")) {
                int tmp = Integer.parseInt(st.nextToken());

                all.add(binSearch(all, tmp), tmp);
            } else if (type.equals("REMOVE")) {
                int tmp = Integer.parseInt(st.nextToken());

                all.remove(binSearch(all, tmp));
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
                    List<Integer> sub = all.subList(start, end);
                    int cur = sub.get(0);
                    for (int i = 1; i < end; i++) {
                        cur ^= sub.get(i);
                    }
                    pw.println(cur);
                }
            }
        }

        pw.close();
    }

    private static int binSearch(LinkedList<Integer> arr, int key) {
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

    private static int binSearchStart(LinkedList<Integer> arr, int key) {
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

    private static int binSearchEnd(LinkedList<Integer> arr, int key) {
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
