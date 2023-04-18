import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Prob24 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int I = Integer.parseInt(st.nextToken());

            helper[][] triplets = new helper[T][3];
            for (int i = 0; i < T; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 3; j++) {
                    String s = st.nextToken();
                    triplets[i][j] = new helper(s.length() == 2, s.charAt(s.length() - 1) - 'A');
                }
            }
            int t3 = 3 * T;
            while (I-- > 0) {
                int[] data = new int[t3];
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < data.length; i++) {
                    data[i] = Integer.parseInt(st.nextToken());
                }

                pw.println(evaluate(triplets, data) ? "TRUE" : "FALSE");
            }
        }

        pw.close();
    }

    private static boolean evaluate(helper[][] triplets, int[] data) {
        for (helper[] triplet : triplets) {
            boolean fail = true;
            for (int j = 0; j < 3; j++) {
                if ((triplet[j].not && data[triplet[j].name] == 0) || (!triplet[j].not && data[triplet[j].name] == 1)) {
                    fail = false;
                    break;
                }
            }
            if (fail) {
                return false;
            }
        }
        return true;
    }

    private static class helper {
        boolean not;
        int name;

        public helper(boolean not, int name) {
            this.not = not;
            this.name = name;
        }
    }
}
