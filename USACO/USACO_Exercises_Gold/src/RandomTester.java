import java.io.*;

public class RandomTester {
    public static void main(String[] args) throws IOException {
        String file = "checklist";


        int n = 3;
        int A = 2;
        int B = 1;
        long a;
        long b;

        main:
        while (true) {
            for (int i = 0; i < 10; i++) {
                PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file + ".in")));

                RandomGenerator.main(new String[]{"" + n});
                BufferedReader br2 = new BufferedReader(new FileReader("random.out"));
                pw.println(A + " " + B);
                for (int j = 0; j < n; j++) {
                    pw.println(br2.readLine());
                }
                pw.close();
                CowChecklist6.main(null);
                BufferedReader br = new BufferedReader(new FileReader(file + ".out"));
                a = Long.parseLong(br.readLine());
                br.close();
                CowChecklist7.main(null);
                br = new BufferedReader(new FileReader(file + ".out"));
                b = Long.parseLong(br.readLine());
                br.close();
                if (a != b) {
                    break main;
                }
            }

            n++;
            A = (int) (Math.random() * (n - 3)) + 2;
            B = n - A;
        }
    }
}
