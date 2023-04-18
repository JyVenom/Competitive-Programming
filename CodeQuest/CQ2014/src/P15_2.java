import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class P15_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Prob15.in.txt"));
        PrintWriter pw = new PrintWriter(System.out, true);

        String line;
        int level = 0;
        boolean addedSpace = false;
        while ((line = br.readLine())!= null) {
            StringTokenizer st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String cur = st.nextToken();
                 if (cur.startsWith(">")) {
                    pw.println(">");
                     level += 4;
                     for (int i = 0; i < level; i++) {
                         pw.print(".");
                     }
                    pw.print(cur.substring(1));
                }
                else if (cur.startsWith("?>")) {
                    pw.println(" ?>");
                    pw.print(cur.substring(2));
                }
                else if (cur.startsWith("/>")){
                    pw.println("/>");
                    pw.print(cur.substring(2));
                }
                else if (cur.charAt(0) == '<'){
                    for (int i = 0; i < level; i++) {
                        pw.print(".");
                    }
                    pw.print(cur);
                }
                else {
                    pw.print(" " + cur);
                }
            }
        }

        pw.close();
    }
}

