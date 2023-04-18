import java.io.*;
import java.util.StringTokenizer;

public class SnowBoots {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] path = new int[n];
        int[][] boots = new int[b][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            path[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < b; i++) {
            st = new StringTokenizer(br.readLine());
            boots[i][0] = Integer.parseInt(st.nextToken());
            boots[i][1] = Integer.parseInt(st.nextToken());
        }

        int at = 0;
        int boot = 0;
        int s = boots[boot][0];
        int d = boots[boot][1];
        while (at < (n - 1)) {
            int next = findNext(path, at, s, d, n);
            if (next != -1) {
                at = next;
            }
            else {
                do {
                    boot++;
                    s = boots[boot][0];
                    d = boots[boot][1];
                } while (s < path[at]);
            }
        }

        pw.println(boot);
        pw.close();
    }

    private static int findNext (int[] path, int at, int s, int d, int n) {
        for (int i = Math.min(n - at - 1, d); i > 0; i--) {
            int next = at + i;
            if (path[next] <= s) {
                return next;
            }
        }

        return -1;
    }
}

//
//
////
//
//import java.util.*;
//        import java.io.*;
//
//public class snowboots_silver {
//
//    public static int n;
//    public static int[] deep;
//    public static int nBoots;
//    public static boot[] boots;
//
//    public static void main(String[] args) throws Exception {
//
//        // Read the grid.
//        Scanner stdin = new Scanner(new File("snowboots.in"));
//
//        // Read in all of the input.
//        n = stdin.nextInt();
//        nBoots = stdin.nextInt();
//        deep = new int[n];
//        for (int i=0; i<n; i++)
//            deep[i] = stdin.nextInt();
//        boots = new boot[nBoots];
//        for (int i=0; i<nBoots; i++) {
//            int d = stdin.nextInt();
//            int s = stdin.nextInt();
//            boots[i] = new boot(d, s);
//        }
//
//        // Ta da!
//        PrintWriter out = new PrintWriter(new FileWriter("snowboots.out"));
//        out.println(bfs());
//        out.close();
//        stdin.close();
//    }
//
//    public static int bfs() {
//
//        // These are all valid states.
//        boolean[][] used = new boolean[n][nBoots];
//        Arrays.fill(used[0], true);
//
//        // Put each of these states into the queue.
//        LinkedList<Integer> q = new LinkedList<Integer>();
//        for (int i=0; i<nBoots; i++) q.offer(i);
//
//        // Usual bfs.
//        while (q.size() > 0) {
//
//            int cur = q.poll();
//            int step = cur/nBoots;
//            int bNum = cur%nBoots;
//
//            // Try stepping with this boot...
//            for (int i=1; step+i<n && i<=boots[bNum].maxStep; i++) {
//                if (deep[step+i] <= boots[bNum].depth && !used[step+i][bNum]) {
//                    q.offer(nBoots*(step+i)+bNum);
//                    used[step+i][bNum] = true;
//                }
//            }
//
//            // Try switching to another boot.
//            for (int i=bNum+1; i<nBoots; i++) {
//                if (boots[i].depth >= deep[step] && !used[step][i]) {
//                    q.offer(nBoots*step+i);
//                    used[step][i] = true;
//                }
//            }
//        }
//
//        // Find the earliest boot that got us here.
//        for (int i=0; i<nBoots; i++)
//            if (used[n-1][i])
//                return i;
//
//        // Should never get here.
//        return -1;
//
//    }
//}
//
//class boot {
//
//    public int depth;
//    public int maxStep;
//
//    public boot(int d, int s) {
//        depth = d;
//        maxStep = s;
//    }
//}
