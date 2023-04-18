import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DynamicInversionPairs3 {
    private static int ans = 0;

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

        int[] loc = new int[n - 1];
        for (int i = 0; i < n; i++) {
            loc[arr[i]] = i;
        }
        node tree = constructTree(arr, 0, n - 1);

        while (m-- > 0) {
            pw.println(ans);

            int cur = Integer.parseInt(br.readLine()) - 1;

        }

        pw.close();
    }

    private static node constructTree(int[] values, int lhs, int rhs) {
        node cur = new node(lhs, rhs);

        if (lhs == rhs) {
            cur.values = new int[]{values[lhs]};
            return cur;
        }

        int mid = (lhs + rhs) / 2;

        cur.left = constructTree(values, lhs, mid);
        cur.right = constructTree(values, mid + 1, rhs);
        cur.values = new int[cur.left.values.length + cur.right.values.length];
        ans += merge(cur.values, cur.left.values, cur.right.values);

        return cur;
    }

    private static int merge(int[] res, int[] a, int[] b) {
        int i = 0, j = 0, k = 0, cnt = 0;

        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                res[k++] = a[i++];
            } else {
                res[k++] = b[j++];

                cnt += (a.length - i);
            }
        }

        while (i < a.length) {
            res[k++] = a[i++];
        }
        while (j < b.length) {
            res[k++] = b[j++];
        }

        return cnt;
    }

    private static class node {
        int[] values;
        int start, end;
        node left, right;

        public node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
