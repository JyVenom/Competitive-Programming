import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class P12 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            while (n-- > 0) {
                String s = br.readLine();

                int numPBits = 0;
                int tmp = s.length();
                int tmp2 = 1;
                while (tmp > 0) {
                    tmp -= tmp2;
                    tmp += 1;
                    tmp2 *= 2;
                    numPBits++;
                }

                int[] ans = new int[s.length() + numPBits + 1];
                tmp2 = 1;
                tmp = 1;
                for (int i = 0; i < s.length(); i++) {
                    for (; tmp < ans.length; tmp++) {
                        if (tmp == tmp2) {
                            tmp2 *= 2;
                        } else {
                            ans[tmp++] = s.charAt(i) - '0';
                            break;
                        }
                    }
                }
                tmp2 = 1;
                while (tmp2 < ans.length) {
                    int start = tmp2;
                    int end = 2 * tmp2;
                    int count = 0;
                    while (end < ans.length) {
                        for (; start < end; start++) {
                            count += ans[start] % 2;
                        }

                        start = end + tmp2;
                        end = start + tmp2;
                    }
                    for (; start < ans.length; start++) {
                        count += ans[start] % 2;
                    }

                    ans[tmp2] = count % 2;
                    tmp2 *= 2;
                }

                for (int i = 1; i < ans.length; i++) {
                    pw.print(ans[i]);
                }
                pw.println();
            }
        }

        pw.close();
    }
}
