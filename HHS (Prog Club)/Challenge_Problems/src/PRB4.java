import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PRB4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        ArrayList<person> people = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            people.add(new person(st.nextToken(), Integer.parseInt(st.nextToken())));
        }

        people.sort((o1, o2) -> {
            if (o1.score != o2.score) {
                return o2.score - o1.score;
            } else {
                return o1.name.compareTo(o2.name);
            }
        });

        for (person p : people) {
            pw.println(p.name + " " + p.score);
        }
        pw.close();
    }

    private static class person {
        String name;
        int score;

        public person(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }
}
