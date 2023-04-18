import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class P10 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("prob10.in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        int a = 0, b = 0;
        String line;
        while ((line = br.readLine()) != null) {
            int p = Integer.parseInt(line);

            if (a == 0 && b == 0) {
                pw.println("Game start");
            }

            if (p == 1) {
                a++;
            } else {
                b++;
            }

            if (a - b >= 2 && a >= 4) {
                pw.println("Game Player 1");
                a = b = 0;
            } else if (b - a >= 2 && b >= 4) {
                pw.println("Game Player 2");
                a = b = 0;
            } else if (a >= 3 && a == b) {
                pw.println("deuce");
            } else if (b >= 3 && a > b) {
                pw.println("Advantage Player 1");
            } else if (a >= 3 && b > a) {
                pw.println("Advantage Player 2");
            } else {
                if (a == 0) {
                    if (b == 1) {
                        pw.println("love-15");
                    } else if (b == 2) {
                        pw.println("love-30");
                    } else {
                        pw.println("love-40");
                    }
                } else if (a == 1) {
                    if (b == 0) {
                        pw.println("15-love");
                    } else if (b == 1) {
                        pw.println("15-all");
                    } else if (b == 2) {
                        pw.println("15-30");
                    } else {
                        pw.println("15-40");
                    }
                } else if (a == 2) {
                    if (b == 0) {
                        pw.println("30-love");
                    } else if (b == 1) {
                        pw.println("30-15");
                    } else if (b == 2) {
                        pw.println("30-all");
                    } else {
                        pw.println("30-40");
                    }
                } else {
                    if (b == 0) {
                        pw.println("40-love");
                    } else if (b == 1) {
                        pw.println("40-15");
                    } else if (b == 2) {
                        pw.println("40-30");
                    }
                }
            }
        }

        pw.close();
    }
}
