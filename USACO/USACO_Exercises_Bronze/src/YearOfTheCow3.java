import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class YearOfTheCow3 {
    private static final String[] all = new String[]{"Ox", "Tiger", "Rabbit", "Dragon", "Snake", "Horse", "Goat", "Monkey", "Rooster", "Dog", "Pig", "Rat"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        HashMap<String, relationship> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            st.nextToken();
            st.nextToken();
            String b = st.nextToken();
            String c = st.nextToken();
            st.nextToken();
            st.nextToken();
            String d = st.nextToken();
            map.put(a, new relationship(d, c, b.equals("next") ? 1 : -1));
        }

        pw.println(Math.abs(dfs(map, "Elsie")));
        pw.close();
    }

    private static int dfs(HashMap<String, relationship> map, String at) {
        if (at.equals("Bessie")) {
            return 0;
        }

        int tmp = dfs(map, map.get(at).from);
        if (map.get(at).type == -1) {
            int tmp2 = tmp - 1;
            while (!all[((tmp2 % 12) + 12) % 12].equals(map.get(at).year)) {
                tmp2--;
            }
            return tmp2;
        } else {
            int tmp2 = tmp + 1;
            while (!all[((tmp2 % 12) + 12) % 12].equals(map.get(at).year)) {
                tmp2++;
            }
            return tmp2;
        }
    }

    private static class relationship {
        String from, year;
        int type;

        public relationship(String from, String year, int type) {
            this.from = from;
            this.year = year;
            this.type = type;
        }
    }
}
