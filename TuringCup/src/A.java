import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class A {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken()) - 1;
        String s = br.readLine();

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '{') {
                stack.addLast(i);
            } else {
                int tmp = stack.removeLast();
                if (i == p) {
                    pw.println(tmp + 1);
                    break;
                } else if (tmp == p) {
                    pw.println(i + 1);
                    break;
                }
            }
        }

        pw.close();
    }
}
