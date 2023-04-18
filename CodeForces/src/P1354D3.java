import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P1354D3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        sortedList list = new sortedList();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < q; i++) {
            int query = Integer.parseInt(st.nextToken());
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
        private static HashMap<Integer, Integer> map;
        private static ArrayList<Integer> list;

        public sortedList() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        private void add(int element) {
            if (!map.containsKey(element)) {
                map.put(element, 0);
                list.add(binSearch(list, element), element);
            }
            map.replace(element, map.get(element) + 1);
        }

        private void remove(int loc) {
            for (int i = 0; i < list.size(); i++) {
                int key = list.get(i);
                loc -= map.get(key);
                if (loc <= 0) {
                    map.replace(key, map.get(key) - 1);
                    if (map.get(key) == 0) {
                        map.remove(key);
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
}
