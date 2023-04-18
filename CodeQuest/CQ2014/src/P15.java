import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class P15 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Prob15.in.txt"));
        PrintWriter pw = new PrintWriter(System.out, true);

        StringBuilder sb = new StringBuilder();
        String line;
        int level = 0;
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String cur = st.nextToken();
                if (cur.startsWith(">")) {
                    sb.append(">").append("\n");
                    level += 4;
                    sb.append(cur.substring(1));
                } else if (cur.startsWith("?>")) {
                    sb.append(" ?>").append("\n");
                    sb.append(cur.substring(2));
                } else if (cur.startsWith("/>")) {
                    sb.append("/>").append("\n");
                    sb.append(cur.substring(2));
                } else if (cur.startsWith("</")) {
                    sb.append(cur);
                }
                else if (cur.charAt(0) == '<') {
                    sb.append(".".repeat(level));
                    sb.append(cur);
                } else {
                    if (sb.charAt(sb.length() - 1) == '<') {
                        sb.append(cur);
                    } else {
                        sb.append(" ").append(cur);
                    }
                }
            }
        }

        pw.print(sb);
        pw.close();
    }
}

