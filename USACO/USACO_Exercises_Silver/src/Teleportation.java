import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Teleportation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("teleport.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));

        int n = Integer.parseInt(br.readLine());

        long cur = 0;
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            int dif = Math.abs(b - a) - Math.abs(a);

            cur += Math.abs(b - a);
            if (dif > 0) {
                list.add(new int[]{b - dif, -1});
                list.add(new int[]{b, 2});
                list.add(new int[]{b + dif, -1});
            }
        }
        br.close();
        list.sort(Comparator.comparingInt(a -> a[1]));
        list.sort(Comparator.comparingInt(a -> a[0]));
        ArrayList<int[]> res = new ArrayList<>();
        int temp = 0;
        while (temp < list.size()) {
            int j = temp;
            int sum = 0;
            while (j < list.size() && list.get(j)[0] == list.get(temp)[0]) {
                sum += list.get(j)[1];
                j++;
            }

            res.add(new int[]{list.get(temp)[0], sum});

            temp = j;
        }
        list = res;

        long dif = list.get(0)[1];
        long min = cur;
        for (int i = 1; i < list.size(); i++) {
            int xDist = list.get(i)[0] - list.get(i - 1)[0];
            cur = cur + dif * xDist;
            min = Math.min(min, cur);
            dif += list.get(i)[1];
        }

        pw.print(min);
        pw.close();
    }
}