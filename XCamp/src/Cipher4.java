import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Cipher4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        HashMap<Integer, Integer> remove = new HashMap<>();
        ArrayList<Integer> all = new ArrayList<>(20000);
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);

            String type = st.nextToken();
            if (type.equals("ADD")) {
                int tmp = Integer.parseInt(st.nextToken());

                if (remove.containsKey(tmp)) {
                    if (remove.get(tmp) == 1) {
                        remove.remove(tmp);
                    }
                    else {
                        remove.replace(tmp, remove.get(tmp) - 1);
                    }
                }
                else {
                    all.add(binSearch(all, tmp), tmp);
                }
            } else if (type.equals("REMOVE")) {
                int tmp = Integer.parseInt(st.nextToken());

                if (!remove.containsKey(tmp)) {
                    remove.put(tmp, 0);
                }
                remove.replace(tmp, remove.get(tmp)+1);
            } else {
                st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                st.nextToken();
                int b = Integer.parseInt(st.nextToken());

                for (int tmp : remove.keySet()) {
                    int loc = binSearch(all, tmp);
                    for (int i = 0; i < remove.get(tmp); i++) {
                        if (all.get(loc) > tmp) {
                            loc--;
                        }
                        all.remove(loc);
                    }
                }
                remove.clear();
                int start = binSearchStart(all, a);
                int end = binSearchEnd(all, b);
                if (start == end) {
                    pw.println(0);
                } else {
                    int cur = all.get(start);
                    for (int i = start + 1; i < end; i++) {
                        cur ^= all.get(i);
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
