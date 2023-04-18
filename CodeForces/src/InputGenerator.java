import java.io.PrintWriter;

public class InputGenerator {
    public static void main(String[] args) {
        PrintWriter pw = new PrintWriter(System.out);

        int n = 1000;

        pw.println(n + " " + n);
        for (int i = 0; i < n; i++) {
            pw.print(1 + " ");
        }
        for (int i = 0; i < n; i++) {
            pw.println(1);
        }

        pw.close();
    }
}
