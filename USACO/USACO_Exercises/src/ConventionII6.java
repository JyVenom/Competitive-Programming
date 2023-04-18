import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ConventionII6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader (new FileReader("convention2.in"));
        PrintWriter out = new PrintWriter(new File("convention2.out")); //or what it calls for ("promote.out)

        int cows = Integer.parseInt(br.readLine());
        ArrayList<int[]> data = new ArrayList<>(cows);
        for (int i = 0; i < cows; i++) {
            data.add(new int[3]);
        }

        String line;
        for (int i = 0; i < cows; i++) {
            line = br.readLine();
            data.get(i)[0] = i + 1;
            data.get(i)[1] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            data.get(i)[2] = Integer.parseInt(line.substring(line.indexOf(' ') +  1));
        }
        data.sort(Comparator.comparingInt(a -> a[1]));

        int maxWait = 0;
        int time = data.get(0)[1] + data.get(0)[2];
        data.remove(0);
        int smallest;
        int[] current;
        int[] temp;
        for (int i = 0; i < cows - 1; i++) {
            smallest = 0;
            for (int j = 0; j < data.size(); j++) {
                temp = data.get(j);
                if (temp[1] < time) {
                    if (temp[0] < data.get(smallest)[0]) {
                        smallest = j;
                    }
                } else {
                    break;
                }
            }
            current = data.get(smallest);
            maxWait = Math.max(maxWait, time - current[1]);
            if (current[1] > time) {
                time = current[1] + current[2];
            } else {
                time += current[2];
            }
            data.remove(smallest);
        }
        out.println(maxWait);
        out.close();
    }
}
