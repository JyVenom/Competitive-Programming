import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PRB2a {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());

        int sum = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num;
            min = Math.min(min, num);
        }

        pw.println(sum - min);
        pw.close();
    }
}
