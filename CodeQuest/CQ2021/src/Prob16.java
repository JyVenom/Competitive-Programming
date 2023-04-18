import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Prob16 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] a = new int[st.countTokens()];
            for (int i = 0; i < a.length; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            int[] b = new int[st.countTokens()];
            for (int i = 0; i < b.length; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            int[] res = new int[a.length + b.length - 1];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    res[i + j] += a[i] * b[j];
                }
            }

            StringBuilder sb = new StringBuilder();
            if (res[0] != 0) {
                sb.append(res[0]).append("+");
            }
            if (res[1] != 0) {
                if (res[1] == 1) {
                    sb.append("x").append("+");

                } else if (res[1] == -1) {
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    sb.append("-x").append("+");
                } else {
                    if (res[1] < 0) {
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                    }
                    sb.append(res[1]).append("x").append("+");
                }
            }
            for (int i = 2; i < res.length; i++) {
                if (res[i] != 0) {
                    if (res[i] == 1) {
                        sb.append("x^").append(i).append("+");

                    } else if (res[i] == -1) {
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        sb.append("-x^").append(i).append("+");
                    } else {
                        if (res[i] < 0) {
                            if (sb.length() > 0) {
                                sb.deleteCharAt(sb.length() - 1);
                            }
                        }
                        sb.append(res[i]).append("x^").append(i).append("+");
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            pw.println(sb);
        }

        pw.close();
    }
}
