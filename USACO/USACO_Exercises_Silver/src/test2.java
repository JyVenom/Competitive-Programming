import java.io.*;
import java.util.*;

public class test2 {
    public static void main (String [] args) {
        int n = 1000000;
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 200000; i++) {
//            Double.compare(Math.random() * 1000000000, Math.random() * 1000000000);
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        int x;
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 2147483647; i++) {
//            x = i;
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        long start = System.currentTimeMillis();
//        int j = 1;
//        for (int i = 0; i < 1000000000; i++) {
//            if (j == 1) {
//
//            }
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        int[] temp = new int[n];
//        ArrayList<change> temp = new ArrayList<>();
//        for (int i = 0; i < 200000; i++) {
////            temp[i] = Double.compare(Math.random() * n, Math.random() * n);
//            temp.add(new change(i, (int) (Math.random() * 1000000000), true));
//        }
//        long start = System.currentTimeMillis();
//        temp.sort(Comparator.comparingInt(o -> o.loc));
//        System.out.println(System.currentTimeMillis() - start);
//
//        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
//        edges.add(new ArrayList<>());
//        edges.add(new ArrayList<>());
//        edges.get(0).add(1);
//        edges.get(1).add(0);
//        int cur = 0;
//        start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            cur = edges.get(cur).get(0);
//        }
//        System.out.println(System.currentTimeMillis() - start);
//
//
//        ArrayList<ArrayList<int[]>> edges = new ArrayList<>();
//        edges.add(new ArrayList<>());
////        int[] dist = new int[n];
//        for (int i = 1; i < n; i++) {
//            edges.add(new ArrayList<>());
//            edges.get(i - 1).add(new int[]{i, (int) (Math.random() * 1000000000)});
//        }
//        edges.get(999999).add(new int[]{0, (int) (Math.random() * 1000000000)});
////        int[]  cur = new int[2];
//        boolean[] visited = new boolean[n];
//        LinkedList<Integer> queue = new LinkedList<>();
//        queue.add(0);
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            int cur = queue.poll();
//            visited[cur] = true;
//
//            for (int[] next : edges.get(cur)) {
//                if (!visited[next[0]]) {
//                    if (next[1] < 1000000000) {
//                        queue.add(next[0]);
//                    }
//                }
//            }
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        LinkedList<Integer> temp = new LinkedList<>();
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            temp.add(i);
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        int[] temp2 = new int[n];
//        start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            temp2[i] = temp.poll();
//        }
//        System.out.println(System.currentTimeMillis() - start);
//
//        LinkedList<Integer> temp = new LinkedList<>();
//        for (int i = 0; i < 5000; i++) {
//            temp.add(i);
//        }
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            temp.add(i);
//            int cur = temp.poll();
//        }
//        System.out.println(System.currentTimeMillis() - start);
//    }
//
//    private static class change {
//        int cow, loc;
//        boolean start;
//
//        private change(int cow, int loc, boolean start) {
//            this.cow = cow;
//            this.loc = loc;
//            this.start = start;
//        }

//        double[] temp = new double[n];
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            temp[i] = Math.random() / Math.random();
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        int[] a = new int[n];
//        int[] b = new int[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = (int) (Math.random() * 1000000000);
//            b[i] = (int) (Math.random() * 1000000000);
//        }
//        int[] temp = new int[n];
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            temp[i] = a[i] - b[i];
//        }
//        System.out.println(System.currentTimeMillis() - start);
//        double[] temp = new double[n];
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            temp[i] = Math.random() - Math.random();
//        }
//        System.out.println(System.currentTimeMillis() - start);

//        int[] a = new int[n];
//        int[] b = new int[n];
//        for (int i = 0; i < n; i++) {
//            a[i] = (int) (Math.random() * 1000000000);
//            b[i] = (int) (Math.random() * 1000000000);
//        }
//        int[] temp = new int[n];
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            temp[i] = Integer.compare(a[i], b[i]);
//        }
//        System.out.println(System.currentTimeMillis() - start);

        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (int) (Math.random() * 1000000000);
            b[i] = (int) (Math.random() * 1000000000);
        }
        boolean[] temp = new boolean[n];
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            temp[i] = a[i] > b[i];
        }
        System.out.println(System.currentTimeMillis() - start);
//        System.out.println(Arrays.toString(temp));
    }
}