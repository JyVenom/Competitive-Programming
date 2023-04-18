/*
ID: jerryya2
LANG: JAVA
TASK: msquare
*/

import java.io.*;
import java.util.*;

public class msquare {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("msquare.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("msquare.out")));

        int[] tar = new int[8];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 8; i++) {
            tar[i] = Integer.parseInt(st.nextToken());
        }
        Square target = new Square(0, tar, "");
        br.close();

        Queue<Square> queue = new LinkedList<>();
        HashSet<Integer> flag = new HashSet<>();
        queue.add(new Square(0, new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, ""));
        Square square = null;
        while (!queue.isEmpty()) {
            square = queue.remove();
            int num = square.getNum();
            if (flag.contains(num)) continue;
            flag.add(num);
            if (square.equals(target)) break;
            queue.add(square.A());
            queue.add(square.B());
            queue.add(square.C());
        }

        assert square != null;
        pw.println(square.N);
        pw.println(square.str);
        pw.close();
    }

    static class Square {
        public int N;
        public String str;
        private final int[] squares;

        public Square(int N, int[] squares, String str) {
            this.N = N;
            this.squares = squares;
            this.str = str;
        }

        public int getNum() {
            int res = 0;
            for (int num : squares)
                res = res * 10 + num;
            return res;
        }

        public Square A() {
            int[] res = new int[8];
            for (int i = 0; i < 8; i++)
                res[i] = this.squares[7 - i];
            return new Square(N + 1, res, str + 'A');
        }

        public Square B() {
            int[] res = new int[8];
            System.arraycopy(this.squares, 0, res, 1, 3);
            res[0] = this.squares[3];
            System.arraycopy(this.squares, 5, res, 4, 3);
            res[7] = this.squares[4];
            return new Square(N + 1, res, str + 'B');
        }

        public Square C() {
            int[] res = new int[8];
            System.arraycopy(this.squares, 0, res, 0, 8);
            res[1] = this.squares[6];
            res[2] = this.squares[1];
            res[5] = this.squares[2];
            res[6] = this.squares[5];
            return new Square(N + 1, res, str + 'C');
        }

        public boolean equals(Square other) {
            for (int i = 0; i < 8; i++)
                if (this.squares[i] != other.squares[i])
                    return false;
            return true;
        }
    }

}
