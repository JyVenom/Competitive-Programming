import java.io.*;
import java.util.*;

public class LasersAndMirrors6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lasers.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lasers.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int xL = Integer.parseInt(st.nextToken());
        int yL = Integer.parseInt(st.nextToken());
        int xB = Integer.parseInt(st.nextToken());
        int yB = Integer.parseInt(st.nextToken());
        Map<Line, Integer> dist = new HashMap<>();
        LinkedList<Line> queue = new LinkedList<>();
        queue.add(new Line(yL, true));
        dist.put(new Line(yL, true), 0);
        queue.add(new Line(xL, false));
        dist.put(new Line(xL, false), 0);
        Map<Integer, ArrayList<Integer>> xtoY = new HashMap<>();
        Map<Integer, ArrayList<Integer>> ytoX = new HashMap<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (!xtoY.containsKey(x)) {
                xtoY.put(x, new ArrayList<>());
            }
            xtoY.get(x).add(y);
            if (!ytoX.containsKey(y)) {
                ytoX.put(y, new ArrayList<>());
            }
            ytoX.get(y).add(x);
        }

        int res = -1;
        while (!queue.isEmpty()) {
            Line curr = queue.removeFirst();
            if (curr.horizontal && curr.val == yB) {
                res = dist.get(curr);
                break;
            }
            if (!curr.horizontal && curr.val == xB) {
                res = dist.get(curr);
                break;
            }
            Map<Integer, ArrayList<Integer>> source = curr.horizontal ? ytoX : xtoY;
            if (source.containsKey(curr.val)) {
                for (int dest : source.get(curr.val)) {
                    Line nextLine = new Line(dest, !curr.horizontal);
                    if (!dist.containsKey(nextLine)) {
                        dist.put(nextLine, dist.get(curr) + 1);
                        queue.add(nextLine);
                    }
                }
            }
        }

        pw.println(res);
        pw.close();
    }

    static class Line {
        public int val;
        public boolean horizontal;

        public Line(int val, boolean horizontal) {
            super();
            this.val = val;
            this.horizontal = horizontal;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + (horizontal ? 1231 : 1237);
            result = prime * result + val;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Line other = (Line) obj;
            if (horizontal != other.horizontal)
                return false;
            return val == other.val;
        }

    }
}