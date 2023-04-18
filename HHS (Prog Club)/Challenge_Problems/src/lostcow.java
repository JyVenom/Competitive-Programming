import java.io.*;
import java.util.StringTokenizer;

public class lostcow {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lostcow.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lostcow.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int sum = 0;
        if (y < x) {
            int dif = x - y;
            int i = 2;
            for (; i < dif; i *= 4) {
                sum += (2 * i) + (i / 4);
            }
            sum += i + dif + (i / 4);
        }
        else {
            int dif = y - x;
            sum = 1;
            int i = 4;
            for (; i < dif; i *= 4) {
                sum += (2 * i) + (i / 4);
            }
            sum += i + dif + (i / 4);
        }

        pw.println(sum);
        pw.close();
    }
}
