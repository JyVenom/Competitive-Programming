import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class temp {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String line;
        ArrayList<String> data = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);

            while (st.hasMoreTokens()) {
                data.add(st.nextToken());
            }
        }

        pw.close();
    }
}
