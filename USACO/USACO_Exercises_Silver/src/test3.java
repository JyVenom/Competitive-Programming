import java.io.*;

public class test3 {
    public static void main (String [] args) throws IOException {
        int n = 1000000;
        PrintWriter pw1 = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        PrintWriter pw2 = new PrintWriter(System.out);
        OutputStream os = new BufferedOutputStream(System.out);
        StringBuilder sb = new StringBuilder();

        System.out.println();

        long start, pw1t, pw2t, ost;
        for (int i = 0; i < n * 2; i++) {
            System.out.println(i);
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            os.write(i);
            os.write('\n');
        }
        os.close();
        pw1t = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            pw2.println(i);
//        }
//        pw2.close();
//        pw2t = System.currentTimeMillis();
//        for (int i = 0; i < n; i++) {
//            pw1.println(i);
//        }
//        pw1.close();
//        ost = System.currentTimeMillis();
        System.out.println(pw1t - start);
//        System.out.println(pw2t - pw1t);
//        System.out.println(ost - pw2t);
    }
}