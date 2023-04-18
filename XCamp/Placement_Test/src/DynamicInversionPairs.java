import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class DynamicInversionPairs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
//        BufferedReader br = new BufferedReader(new FileReader("out.txt"));
//        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("out2.txt")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        ArrayList<HashSet<Integer>> count = new ArrayList<>();
        ArrayList<HashSet<Integer>> count2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            count.add(new HashSet<>());
            count2.add(new HashSet<>());
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    sum++;
                    count.get(i).add(j);
                    count2.get(j).add(i);
                }
            }
        }
        while (m-- > 0) {
            pw.println(sum);

            int cur = Integer.parseInt(br.readLine()) - 1;

            sum -= count.get(cur).size();
            sum -= count2.get(cur).size();

            for (int tmp : count2.get(cur)) {
                count.get(tmp).remove(cur);
            }
            for (int tmp : count.get(cur)) {
                count2.get(tmp).remove(cur);
            }
        }

        pw.close();
    }
}
