import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class MountainView {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));
        PrintWriter out = new PrintWriter(new File("mountains.out"));

        int N = Integer.parseInt(br.readLine());
        int[][] data = new int[N][2];

        String line;
        for (int i = 0; i < N; i++) {
            line = br.readLine();
            data[i][0] = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            data[i][1] = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        }
        Arrays.sort(data, Comparator.comparingInt(arr -> arr[1]));

        int count = 0;
        boolean needToBreak = false;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++){
                if (Math.abs(data[j][0] - data[i][0]) <= Math.abs(data[j][1] - data[i][1])){
                    needToBreak = true;
                    break;
                }
            }
            if (needToBreak){
                needToBreak = false;
            }
            else {
                count++;
            }
        }

        out.println(count);
        out.close();
    }
}
