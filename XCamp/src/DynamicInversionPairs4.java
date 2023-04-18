import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DynamicInversionPairs4 {
    private static int ans = 0, less, more;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] loc = new int[n + 1];
        for (int i = 0; i < n; i++) {
            loc[arr[i]] = i;
        }
        node tree = constructTree(arr, 0, n - 1);

        int N = n - 1;
        while (m-- > 0) {
            pw.println(ans);

            int val = Integer.parseInt(br.readLine());
            int cur = loc[val];

            less = more = 0;
            countMoreInRange(tree, 0, cur - 1, val);
            countLessInRange(tree, cur + 1, N, val);
            delete(tree, cur, val);

            ans -= less;
            ans -= more;
        }

        pw.close();
    }

    private static void delete(node tree, int loc, int val) {
        if (tree == null) {
            return;
        }
        if (tree.start > loc || tree.end < loc) {
            return;
        }

        int tmp = binSearch(tree.values, val);
        tree.values.remove(tmp);
        delete(tree.left, loc, val);
        delete(tree.right, loc, val);
    }


    private static void countLessInRange(node tree, int start, int end, int val) {
        if (tree.start > end || tree.end < start) {
            return;
        }

        if (start <= tree.start && tree.end <= end) {
            int tmp = binSearch(tree.values, val);
            less += tmp;
        } else {
            countLessInRange(tree.left, start, end, val);
            countLessInRange(tree.right, start, end, val);
        }
    }

    private static void countMoreInRange(node tree, int start, int end, int val) {
        if (tree.start > end || tree.end < start) {
            return;
        }

        if (start <= tree.start && tree.end <= end) {
            int tmp = binSearch(tree.values, val);
            more += (tree.values.size() - tmp);
        } else {
            countMoreInRange(tree.left, start, end, val);
            countMoreInRange(tree.right, start, end, val);
        }
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

    private static node constructTree(int[] values, int lhs, int rhs) {
        node cur = new node(lhs, rhs);

        if (lhs == rhs) {
            cur.values = new ArrayList<>(1);
            cur.values.add(values[lhs]);
            return cur;
        }

        int mid = (lhs + rhs) / 2;

        cur.left = constructTree(values, lhs, mid);
        cur.right = constructTree(values, mid + 1, rhs);
        cur.values = new ArrayList<>(cur.left.values.size() + cur.right.values.size());
        ans += merge(cur.values, cur.left.values, cur.right.values);

        return cur;
    }

    private static int merge(ArrayList<Integer> res, ArrayList<Integer> a, ArrayList<Integer> b) {
        int i = 0, j = 0, cnt = 0;

        while (i < a.size() && j < b.size()) {
            if (a.get(i) <= b.get(j)) {
                res.add(a.get(i++));
            } else {
                res.add(b.get(j++));

                cnt += (a.size() - i);
            }
        }

        while (i < a.size()) {
            res.add(a.get(i++));
        }
        while (j < b.size()) {
            res.add(b.get(j++));
        }

        return cnt;
    }

    private static class node {
        ArrayList<Integer> values;
        int start, end;
        node left, right;

        public node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
