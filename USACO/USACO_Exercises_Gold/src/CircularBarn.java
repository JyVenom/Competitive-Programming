import java.io.*;

public class CircularBarn {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));

        int n = Integer.parseInt(br.readLine());
        int[] wait = new int[n];
        for (int i = 0; i < n; i++) {
            wait[i] = Integer.parseInt(br.readLine());
        }

        int count = 0;
        int start = -1;
        for (int i = 0; i < n; i++) {
            if (wait[i] > 1) {
                while (wait[i] > 1) {
                    if (start == -1) {
                        start = (i + 1) % n;
                    }
                    for (int j = start; ; j = (j + 1) % n) {
                        if (wait[j] == 0) {
                            count += (j + n - i) % n;
                            wait[j] = 1;
                            wait[i]--;
                            start = j + 1;
                            break;
                        }
                    }
                }
            }
        }

        pw.println(count);
        pw.close();
    }
}
