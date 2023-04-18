import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class RadioContact {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("radio.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] f = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String line = br.readLine();
        int[] fPath = new int[n];
        for (int i = 0; i < n; i++) {
            char c = line.charAt(i);
            if (c == 'E') {
                fPath[i] = 1;
            }
            else if (c == 'S') {
                fPath[i] = 2;
            }
            else if (c == 'W') {
                fPath[i] = 3;
            }
        }
        line = br.readLine();
        int[] bPath = new int[m];
        for (int i = 0; i < m; i++) {
            char c = line.charAt(i);
            if (c == 'E') {
                bPath[i] = 1;
            }
            else if (c == 'S') {
                bPath[i] = 2;
            }
            else if (c == 'W') {
                bPath[i] = 3;
            }
        }

        int sum = 0;
        int at = 0;
        if (n < m) {
            for (int i = 0; i < m; i++) {
                if (bPath[i] == 0) {
                    b[0]++;
                } else if (bPath[i] == 1) {
                    b[1]++;
                } else if (bPath[i] == 2) {
                    b[0]--;
                } else {
                    b[1]--;
                }

                int temp1 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                if (at < n) {
                    int move = fPath[at];
                    int temp2;
                    if (move == 0) {
                        f[0]++;
                        temp2 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                        f[0]--;
                    } else if (move == 1) {
                        f[1]++;
                        temp2 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                        f[1]--;
                    } else if (move == 2) {
                        f[0]--;
                        temp2 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                        f[0]++;
                    } else {
                        f[1]--;
                        temp2 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                        f[1]++;
                    }

                    if (temp1 > temp2) {
                        at++;
                        if (move == 0) {
                            f[0]++;
                        }
                        else if (move == 1) {
                            f[1]++;
                        }
                        else if (move == 2) {
                            f[0]--;
                        }
                        else {
                            f[1]--;
                        }
                        sum += temp2;
                    }
                    else {
                        sum += temp1;
                    }
                }
                else {
                    sum += temp1;
                }

            }
        }
        else {
            for (int i = 0; i < n; i++) {
                if (fPath[i] == 0) {
                    f[0]++;
                }
                else if (fPath[i] == 1) {
                    f[1]++;
                }
                else if (fPath[i] == 2) {
                    f[0]--;
                }
                else {
                    f[1]--;
                }

                int temp1 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                if (at < m) {
                    int move = bPath[at];
                    int temp2;
                    if (move == 0) {
                        b[0]++;
                        temp2 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                        b[0]--;
                    } else if (move == 1) {
                        b[1]++;
                        temp2 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                        b[1]--;
                    } else if (move == 2) {
                        b[0]--;
                        temp2 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                        b[0]++;
                    } else {
                        b[1]--;
                        temp2 = ((b[0] - f[0]) * (b[0] - f[0])) + ((b[1] - f[1]) * (b[1] - f[1]));
                        b[1]++;
                    }

                    if (temp1 > temp2) {
                        at++;
                        if (move == 0) {
                            b[0]++;
                        } else if (move == 1) {
                            b[1]++;
                        } else if (move == 2) {
                            b[0]--;
                        } else {
                            b[1]--;
                        }
                        sum += temp2;
                    } else {
                        sum += temp1;
                    }
                }
                else {
                    sum += temp1;
                }
            }
        }

        pw.println(sum);
        pw.close();
    }
}
