/*
ID: jerryya2
LANG: JAVA
PROG: crypt1
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class crypt1 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

        int n = Integer.parseInt(br.readLine());

        int[] pos = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        br.close();
        for (int i = 0; i < n; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(pos);

        int count = 0;
        for (int A = 0; A < n; A++) {
            int a = pos[A];
            for (int B = 0; B < n; B++) {
                int b = pos[B];
                for (int C = 0; C < n; C++) {
                    int c = pos[C];
                    for (int D = 0; D < n; D++) {
                        int d = pos[D];
                        for (int E = 0; E < n; E++) {
                            int e = pos[E];

                            String p1 = "" + e * (100 * a + 10 * b + c);
                            String p2 = "" + d * (100 * a + 10 * b + c);

                            boolean good = p1.length() == 3;
                            if (good) {
                                for (int i = 0; i < p1.length(); i++) {
                                    if (Arrays.binarySearch(pos, Character.getNumericValue(p1.charAt(i))) < 0) {
                                        good = false;
                                        break;
                                    }
                                }
                            }
                            if (good) {
                                if (p2.length() != 3) {
                                    good = false;
                                }
                                if (good) {
                                    for (int i = 0; i < p2.length(); i++) {
                                        if (Arrays.binarySearch(pos, Character.getNumericValue(p2.charAt(i))) < 0) {
                                            good = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (good) {
                                String sum = "" + (Integer.parseInt(p1) + 10 * Integer.parseInt(p2));
                                if (sum.length() != 4) {
                                    good = false;
                                }
                                if (good) {
                                    for (int i = 0; i < sum.length(); i++) {
                                        if (Arrays.binarySearch(pos, Character.getNumericValue(sum.charAt(i))) < 0) {
                                            good = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (good) {
                                count++;
                            }
                        }
                    }
                }
            }
        }

        pw.println(count);
        pw.close();
    }
}
