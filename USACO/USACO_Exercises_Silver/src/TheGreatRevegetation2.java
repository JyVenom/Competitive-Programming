import java.io.*;
import java.util.ArrayList;

public class TheGreatRevegetation2 {
    private static int[] L;
    private static boolean impossible;
    private static final ArrayList<ArrayList<Integer>> SNumbers = new ArrayList<>();
    private static final ArrayList<ArrayList<Integer>> DNumbers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter out = new PrintWriter(new File("revegetate.out"));

        String line = br.readLine();
        int N = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int M = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        L = new int[100001];

        for (int i = 0; i < 100001; i++) {
            SNumbers.add(new ArrayList<>());
            DNumbers.add(new ArrayList<>());
        }

        int temp;
        int f1;
        int f2;
        for (int i = 0; i < M; i++) {
            line = br.readLine();
            temp = line.indexOf(' ') + 1;
            f1 = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            f2 = Integer.parseInt(line.substring(temp));
            if (line.charAt(0) == 'S') {
                SNumbers.get(f1).add(f2);
                SNumbers.get(f2).add(f1);

            } else {
                DNumbers.get(f1).add(f2);
                DNumbers.get(f2).add(f1);
            }
        }


        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (L[i] == 0) {
                visit(i, 1);
                answer++;
            }
        }
        if (impossible) {
            out.println(0);
        } else {
            out.print(1);
            for (int i = 0; i < answer; i++) {
                out.print(0);
            }
            out.println();
        }

        out.close();
    }

    private static void visit(int x, int v) {
        L[x] = v;
        for (int n : SNumbers.get(x)) {
            if (L[n] == 3 - v) {
                impossible = true;
            }
            if (L[n] == 0) {
                visit(n, v);
            }
        }
        for (int n : DNumbers.get(x)) {
            if (L[n] == v) {
                impossible = true;
            }
            if (L[n] == 0) {
                visit(n, 3 - v);
            }
        }
    }
}
