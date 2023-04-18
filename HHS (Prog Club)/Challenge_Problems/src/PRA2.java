import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PRA2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int B = b - 1;
        int C = c - 1;
        int washes = 0;
        int remB = b;
        int remC = c;
        int numB = 0;
        int numC = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());

            if (cur == 0) {
                if (remB == 0 ) {
                    if (numB > 0) {
                        if (remC > 0) {
                            remC--;
                            numB--;
                            numC++;
                        }
                        else {
                            remB = B;
                            remC = c;
                            washes++;
                            numB = 0;
                            numC = 0;
                        }
                    }
                    else {
                        remB = B;
                        remC = c;
                        washes++;
                        numB = 0;
                        numC = 0;
                    }
                }
                else {
                    remB--;
                }
            }
            else if (cur == 1) {
                if (remC == 0 ){
                    if (numC > 0) {
                        if (remB > 0) {
                            remB--;
                            numC--;
                            numB++;
                        }
                        else {
                            remC = C;
                            remB = b;
                            washes++;
                            numB = 0;
                            numC = 0;
                        }
                    }
                    else {
                        remC = C;
                        remB = b;
                        washes++;
                        numB = 0;
                        numC = 0;
                    }
                }
                else {
                    remC--;
                }
            }
            else if (cur == 2) {
                if (remB == 0 && remC == 0) {
                    remB = b;
                    remC = c;
                    washes++;
                    numB = 0;
                    numC = 0;
                }
                if (remB > remC) {
                    remB--;
                    numB++;
                }
                else {
                    remC--;
                    numC++;
                }
            }
        }

        pw.println(washes);
        pw.close();
    }
}
