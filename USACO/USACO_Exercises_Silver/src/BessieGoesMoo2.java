import java.io.*;
import java.util.StringTokenizer;

public class BessieGoesMoo2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bgm.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bgm.out")));

        int n = Integer.parseInt(br.readLine());
        long[][] num = new long[7][7];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int value = Integer.parseInt(st.nextToken());
            if ("B".equals(s)) {
                num[0][((value % 7) + 7) % 7]++;
            } else if ("E".equals(s)) {
                num[1][((value % 7) + 7) % 7]++;
            } else if ("S".equals(s)) {
                num[2][((value % 7) + 7) % 7]++;
            } else if ("I".equals(s)) {
                num[3][((value % 7) + 7) % 7]++;
            } else if ("G".equals(s)) {
                num[4][((value % 7) + 7) % 7]++;
            } else if ("O".equals(s)) {
                num[5][((value % 7) + 7) % 7]++;
            } else if ("M".equals(s)) {
                num[6][((value % 7) + 7) % 7]++;
            }
        }

        long ans = 0;
        for (int b = 0; b < 7; b++) {
            for (int e = 0; e < 7; e++) {
                for (int s = 0; s < 7; s++) {
                    for (int i = 0; i < 7; i++) {
                        for (int g = 0; g < 7; g++) {
                            for (int o = 0; o < 7; o++) {
                                for (int m = 0; m < 7; m++) {
                                    if (((b + e + s + s + i + e) * (g + o + e + s) * (m + o + o)) % 7 == 0) {
                                        ans += num[0][b] * num[1][e] * num[2][s] * num[3][i] * num[4][g] * num[5][o] * num[6][m];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        pw.println(ans);
        pw.close();
    }
}
