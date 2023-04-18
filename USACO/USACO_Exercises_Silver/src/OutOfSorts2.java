import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class OutOfSorts2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("sort.in"));
        PrintWriter out = new PrintWriter(new File("sort.out"));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        ArrayList<Integer> sorted = new ArrayList<>(N);
        int[] indexed = new int[N];

        for (int i = 0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
            sorted.add(A[i]);
        }

        Collections.sort(sorted);
        for (int i = 0; i < N; i++){
            indexed[i] = sorted.indexOf(A[i]);
        }

        int max = -1000000000;
        for (int i = 0; i < N; i++){
            max = Math.max(max, i - indexed[i]);
        }

        out.println(max + 1);
        out.close();
    }
}