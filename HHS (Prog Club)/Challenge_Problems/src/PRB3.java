import java.io.*;
import java.util.StringTokenizer;

public class PRB3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int sum = a + b + c;
        int max = Math.max(a, Math.max(b, c));

        pw.println(Math.max(0, max - (sum - max) + 1));
        pw.close();
    }
}
