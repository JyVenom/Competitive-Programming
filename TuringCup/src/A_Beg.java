import java.io.*;

public class A_Beg {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int sz = 2 * n + 1;
        char[][] arr = new char[sz][sz];
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                arr[i][j] = ' ';
            }
        }
        for (int i = 0; i < sz; i++) {
            arr[i][i] = '+';
            arr[i][sz - i - 1] = '+';
        }
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                pw.print(arr[i][j]);
            }
            pw.println();
        }
        pw.println();

        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                arr[i][j] = ' ';
            }
        }
        for (int i = 0; i < n; i++) {
            arr[i][i] = '+';
            arr[i][sz - i - 1] = '+';
        }
        for (int i = n; i < sz; i++) {
            arr[i][n] = '+';
        }
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                pw.print(arr[i][j]);
            }
            pw.println();
        }
        pw.println();

        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                arr[i][j] = ' ';
            }
        }
        for (int i = 0; i < sz; i++) {
            arr[i][0] = '+';
            arr[i][sz - 1] = '+';
            arr[0][i] = '+';
            arr[sz - 1][i] = '+';
        }
        arr[0][sz - 1] = ' ';
        arr[sz - 1][sz - 1] = ' ';
        for (int i = 0; i < sz; i++) {
            for (int j = 0; j < sz; j++) {
                pw.print(arr[i][j]);
            }
            pw.println();
        }

        pw.close();
    }
}
