import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class File_Extensions {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> extensions = new HashMap<>();
        ArrayDeque<String> order = new ArrayDeque<>();
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), ".");

            st.nextToken();
            String ext = st.nextToken();
            if (extensions.containsKey(ext)) {
                extensions.replace(ext, extensions.get(ext) + 1);
            } else {
                extensions.put(ext, 1);
                order.addLast(ext);
            }
        }

        while (!order.isEmpty()) {
            String tmp = order.removeFirst();
            if (!order.isEmpty()) {
                pw.println(tmp + " " + extensions.get(tmp));
            } else {
                pw.print(tmp + " " + extensions.get(tmp));
            }
        }
        pw.close();
    }
}
