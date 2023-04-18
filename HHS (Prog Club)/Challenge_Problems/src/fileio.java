import java.io.*;
import java.util.StringTokenizer;

public class fileio {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fileio.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fileio.out")));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Integer.parseInt(st.nextToken());
        }

        pw.println(sum);
        pw.close();
    }
}
