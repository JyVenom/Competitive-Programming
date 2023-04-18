import java.io.*;
import java.util.StringTokenizer;

public class SecretCowCode {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder code = new StringBuilder(st.nextToken());
        long n = Long.parseLong(st.nextToken());

        while (code.length() < n) {
            code.append(code.substring(code.length() - 1)).append(code.substring(0, code.length() - 2));
        }

        pw.println(code.charAt((int) (n - 1)));
        pw.close();
    }
}
