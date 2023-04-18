import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MilkPails {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter(new File("pails.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        br.close();

//        int min = x * (m / x);
//        int diff = y - (x * (y / x));
//        int rem = m - min;
//        int max = min + diff * (rem / diff);
        int max = 0;
        for (int i = 0; i <= m / y; i++){
            for (int j = 0; j <= (m - i * y) / x; j++){
                max = Math.max(max, i * y + j * x);
            }
        }

        pw.println(max);
        pw.close();
    }
}
