import java.io.*;
import java.util.*;

public class LasersAndMirrors8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lasers.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int xL = Integer.parseInt(st.nextToken());
        int yL = Integer.parseInt(st.nextToken());
        int xB = Integer.parseInt(st.nextToken());
        int yB = Integer.parseInt(st.nextToken());
        HashMap<line, Integer> dist = new HashMap<>();
        LinkedList<line> queue = new LinkedList<>();
        line hor = new line(yL, true);
        line ver = new line(xL, false);
        dist.put(hor, 0);
        dist.put(ver, 0);
        queue.add(hor);
        queue.add(ver);
        HashMap<Integer, ArrayList<Integer>> verToHor = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> horToVer = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (!verToHor.containsKey(x)) {
                verToHor.put(x, new ArrayList<>());
            }
            verToHor.get(x).add(y);
            if (!horToVer.containsKey(y)) {
                horToVer.put(y, new ArrayList<>());
            }
            horToVer.get(y).add(x);
        }

        pw.println(BFS(verToHor, horToVer, dist, queue, xB, yB));
        pw.close();
    }

    private static int BFS(HashMap<Integer, ArrayList<Integer>> verToHor, HashMap<Integer, ArrayList<Integer>> horToVer, HashMap<line, Integer> dist, LinkedList<line> queue, int xB, int yB) {
        while (!queue.isEmpty()) {
            line cur = queue.poll();

            if (cur.horizontal) {
                if (cur.val == yB) {
                    return dist.get(cur);
                }

                if (horToVer.containsKey(cur.val)) {
                    for (int next : horToVer.get(cur.val)) {
                        line temp = new line(next, false);
                        if (!dist.containsKey(temp)) {
                            dist.put(temp, dist.get(cur) + 1);
                            queue.add(temp);
                        }
                    }
                }
            } else {
                if (cur.val == xB) {
                    return dist.get(cur);
                }

                if (verToHor.containsKey(cur.val)) {
                    for (int next : verToHor.get(cur.val)) {
                        line temp = new line(next, true);
                        if (!dist.containsKey(temp)) {
                            dist.put(temp, dist.get(cur) + 1);
                            queue.add(temp);
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static class line {
        int val;
        boolean horizontal;

        private line(int val, boolean horizontal) {
            this.val = val;
            this.horizontal = horizontal;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }

            line other = (line) obj;
            if (horizontal != other.horizontal) {
                return false;
            }

            return val == other.val;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (horizontal ? 1231 : 1237);
            result = prime * result + val;
            return result;
        }

//        private boolean equals(line other) {
//            if (this == other) {
//                return true;
//            }
//            if (other == null) {
//                return false;
//            }
//            if (horizontal != other.horizontal) {
//                return false;
//            }
//            return val == other.val;
//        }
    }
}