import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class test {
    public static void main(String[] args) throws IOException {
        int n = 100000;
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.in")));
        pw.println(n + " " + n);
        pw.print((int)(Math.random() * (n - 1)) + 1);
        for (int i = 0; i < (n - 1); i++) {
            pw.print(" " + ((int)(Math.random() * (n - 1)) + 1));
        }
        pw.println();
        for (int i = 2; i <= n; i++) {
            pw.println(1 + " " + i);
        }
        for (int i = 0; i < n; i++) {
            pw.println((int)((Math.random() * (n - 1)) + 1) + " " + ((int)(Math.random() * (n - 1)) + 1) + " " + ((int)(Math.random() * (n - 1)) + 1));
        }
        pw.close();
    }
}
