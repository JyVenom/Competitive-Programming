/*
ID: jerryya2
LANG: JAVA
TASK: schlnet
*/

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class schlnet2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("schlnet.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("schlnet.out")));

        int N = Integer.parseInt(br.readLine());
        List<List<Integer>> receive = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp = Integer.parseInt(st.nextToken()) - 1;
            while (temp != -1) {
                list.add(temp);
                temp = Integer.parseInt(st.nextToken()) - 1;
            }
            receive.add(list);
        }

        List<HashSet<Integer>> all = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            all.add(new HashSet<>());
        }
        for (int i = 0; i < N; i++) {
            boolean[] processed = new boolean[N];
            helper(all.get(i), receive, processed, i);
        }
        all.sort((o1, o2) -> o2.size() - o1.size());

        pw.close();
    }

    private static void helper(HashSet<Integer> list, List<List<Integer>> receive, boolean[] processed, int at) {
        if (processed[at]) {
            return;
        }

        processed[at] = true;
        list.add(at);
        for (int i = 0; i < receive.get(at).size(); i++) {
            helper(list, receive, processed, receive.get(at).get(i));
        }
    }
}
