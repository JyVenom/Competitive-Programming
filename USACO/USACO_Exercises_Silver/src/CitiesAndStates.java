import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class CitiesAndStates {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        HashMap<Integer, HashMap<Integer, HashMap<Integer, HashMap<Integer, Integer>>>> map = new HashMap<>();
        int[][] pairs = new int[n][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String city = st.nextToken();
            String state = st.nextToken();
            int a = city.charAt(0) - 65;
            int b = city.charAt(1) - 65;
            int c = state.charAt(0) - 65;
            int d = state.charAt(1) - 65;
            pairs[i][0] = a;
            pairs[i][1] = b;
            pairs[i][2] = c;
            pairs[i][3] = d;

            if (!map.containsKey(a)) {
                map.put(a, new HashMap<>());
            }
            if (!map.get(a).containsKey(b)) {
                map.get(a).put(b, new HashMap<>());
            }
            if (!map.get(a).get(b).containsKey(c)) {
                map.get(a).get(b).put(c, new HashMap<>());
            }
            if (!map.get(a).get(b).get(c).containsKey(d)) {
                map.get(a).get(b).get(c).put(d, 1);
            }
            else {
                map.get(a).get(b).get(c).replace(d, map.get(a).get(b).get(c).get(d) + 1);
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!(pairs[i][0] == pairs[i][2] && pairs[i][1] == pairs[i][3])) {
                if (map.containsKey(pairs[i][2])) {
                    if (map.get(pairs[i][2]).containsKey(pairs[i][3])) {
                        if (map.get(pairs[i][2]).get(pairs[i][3]).containsKey(pairs[i][0])) {
                            if (map.get(pairs[i][2]).get(pairs[i][3]).get(pairs[i][0]).containsKey(pairs[i][1])) {
                                count += map.get(pairs[i][2]).get(pairs[i][3]).get(pairs[i][0]).get(pairs[i][1]);
                            }
                        }
                    }
                }
            }
        }

        pw.println(count / 2);
        pw.close();
    }
}
