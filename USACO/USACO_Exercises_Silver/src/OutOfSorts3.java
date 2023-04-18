import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class OutOfSorts3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sort.in"));
        PrintWriter out = new PrintWriter(new File("sort.out"));

        int N = Integer.parseInt(br.readLine());
        int[][] values = new int[N][2];

        for (int i = 0; i < N; i++){
            values[i][0] = Integer.parseInt(br.readLine());
            values[i][1] = i;
        }
        Arrays.sort(values, Comparator.comparingInt(arr -> arr[0]));

        int max = 0;
        for (int i = 0; i < N; i++){
            max = Math.max(max, values[i][1] - i);
        }

        out.println(max + 1);
        out.close();
    }
}