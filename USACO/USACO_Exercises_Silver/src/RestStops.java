import java.io.*;

public class RestStops {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("reststops.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));

        String[] line = br.readLine().split(" ");
        int l = Integer.parseInt(line[0]);
        int n = Integer.parseInt(line[1]);
        int f = Integer.parseInt(line[2]);
        int b = Integer.parseInt(line[3]);

        int[] stops = new int[l + 1];
        for (int i = 0; i < n; i++) {
            line = br.readLine().split(" ");
            stops[Integer.parseInt(line[0])] = Integer.parseInt(line[1]);
        }
        int max = findMax(f, b, l, stops, 0, 0, 0, 0, 0, 0);

        pw.println(max);
        pw.close();
    }

    private static int findMax(int f, int b, int l, int[] stops, int tasty, int max, int tf, int tb, int df, int db) {
        if (db == l) {
            if (tasty > max) {
                max = tasty;
            }
        } else if (stops[db] > 0) {
            int dif = tf - tb;
            for (int i = 1; i <= dif; i++) {
                tb += i;
                tasty += i * stops[db];
                int db2 = db;
                int tb2 = tb;
                int df2 = df;
                int tf2 = tf;
                do {
                    db2++;
                    tb2 += b;
                    df2++;
                    tf2 += f;
                } while (db2 < l && stops[db2] == 0);
                max = findMax(f, b, l, stops, tasty, max, tf2, tb2, df2, db2);
                tb -= i;
                tasty -= i * stops[db];
            }

        } else {
            while (stops[db] == 0) {
                db++;
                tb += b;
                df++;
                tf += f;
            }
            max = findMax(f, b, l, stops, tasty, max, tf, tb, df, db);
        }
        return max;
    }
}
