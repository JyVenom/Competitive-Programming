/*
ID: jerryya2
LANG: JAVA
TASK: shopping
*/

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class shopping {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("shopping.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));

        int s = Integer.parseInt(br.readLine());
        int[][] deals = new int[s + 5][6];
        int[] codes = new int[1000];
        int numCodes = 0;
        int[][][][][] min = new int[6][6][6][6][6];
        for (int[][][][] ints : min) {
            for (int j = 0; j < ints.length; j++) {
                for (int k = 0; k < min[j].length; k++) {
                    for (int l = 0; l < min[k].length; l++) {
                        Arrays.fill(ints[j][k][l], Integer.MAX_VALUE / 2);
                    }
                }
            }
        }
        min[0][0][0][0][0] = 0;
        for (int i = 5; i < deals.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tok = st.countTokens();
            int[] arr = new int[tok];
            for (int j = 0; j < tok; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            int[] deal = new int[6];
            for (int j = 1; j < arr.length - 1; j += 2) {
                if (codes[arr[j]] == 0) {
                    codes[arr[j]] = ++numCodes;
                }
                deal[codes[arr[j]]] += arr[j + 1];
            }
            deal[0] = arr[arr.length - 1];
            deals[i] = deal;
        }
        int b = Integer.parseInt(br.readLine());
        int[] amounts = new int[6];
        for (int i = 0; i < b; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tok = st.countTokens();
            int[] arr = new int[tok];
            for (int j = 0; j < tok; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            if (codes[arr[0]] == 0) {
                codes[arr[0]] = ++numCodes;
            }
            int c = codes[arr[0]];
            amounts[c] = arr[1];
            int[] deal = new int[6];
            deal[c] = 1;
            deal[0] = arr[2];
            deals[c - 1] = deal;
        }
        br.close();

        for (int[] d : deals) {
            for (int a1 = 0; a1 + d[1] <= amounts[1]; a1++) {
                for (int a2 = 0; a2 + d[2] <= amounts[2]; a2++) {
                    for (int a3 = 0; a3 + d[3] <= amounts[3]; a3++) {
                        for (int a4 = 0; a4 + d[4] <= amounts[4]; a4++) {
                            for (int a5 = 0; a5 + d[5] <= amounts[5]; a5++) {
                                int prev = min[a1][a2][a3][a4][a5];
                                int cur = min[a1 + d[1]][a2 + d[2]][a3 + d[3]][a4 + d[4]][a5 + d[5]];
                                if (prev + d[0] < cur) {
                                    min[a1 + d[1]][a2 + d[2]][a3 + d[3]][a4 + d[4]][a5 + d[5]] = prev + d[0];
                                }
                            }
                        }
                    }
                }
            }
        }

        pw.println(min[amounts[1]][amounts[2]][amounts[3]][amounts[4]][amounts[5]]);
        pw.close();
    }

//    private static void solve(int[] d, int[] amounts, int[][][][][] min, int at, int b) {
//        if (at <= b) {
//            for (int i = 0; i + d[at] < amounts[at]; i++) {
//
//            }
//        }
//        else {
//
//        }
//        for (int a1 = 0; a1 + d[1] <= amounts[1]; a1++) {
//            for (int a2 = 0; a2 + d[2] <= amounts[2]; a2++) {
//                for (int a3 = 0; a3 + d[3] <= amounts[3]; a3++) {
//                    for (int a4 = 0; a4 + d[4] <= amounts[4]; a4++) {
//                        for (int a5 = 0; a5 + d[5] <= amounts[5]; a5++) {
//                            int prev = min[a1][a2][a3][a4][a5];
//                            int cur = min[a1 + d[1]][a2 + d[2]][a3 + d[3]][a4 + d[4]][a5 + d[5]];
//                            if (prev + d[0] < cur) {
//                                min[a1 + d[1]][a2 + d[2]][a3 + d[3]][a4 + d[4]][a5 + d[5]] = prev + d[0];
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}