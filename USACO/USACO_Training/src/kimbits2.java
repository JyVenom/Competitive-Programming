/*
ID: jerryya2
LANG: JAVA
TASK: kimbits
*/

import java.io.*;
import java.util.StringTokenizer;

public class kimbits2 {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        BufferedReader br = new BufferedReader(new FileReader("kimbits.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int i = Integer.parseInt(st.nextToken());

        int max = (int) Math.pow(2, n);
        int count = 0;
        for (int j = 0; j < max && count < i; j++) {
            StringBuilder b = new StringBuilder(Integer.toBinaryString(j));
            int num = 0;
            for (int k = 0; k < b.length(); k++) {
                if (b.charAt(k) == '1') {
                    num++;
                    if (num > l) {
                        break;
                    }
                }
            }
            if (num <= l) {
                count++;
                if (count == i) {
                    while (b.length() < n) {
                        b.insert(0, "0");
                    }
                    pw.println(b);
                    break;
                }
            }
        }

        pw.close();
        System.out.println(System.currentTimeMillis() - start);
    }
}
