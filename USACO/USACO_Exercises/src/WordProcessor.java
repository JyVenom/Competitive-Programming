import java.io.*;
import java.util.StringTokenizer;

public class WordProcessor {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("word.in"));
        PrintWriter out = new PrintWriter(new File("word.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String[] words = new String[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            words[i] = st.nextToken();
        }

        int count = 0;
        for (int i = 0; i < n; i++){
            if (count + words[i].length() > k){
                out.println();
                count = 0;
            }
            if (count == 0) {
                out.print(words[i]);
            }
            else {
                out.print(" " + words[i]);
            }
            count += words[i].length();
        }

        out.println();
        out.close();
    }
}
