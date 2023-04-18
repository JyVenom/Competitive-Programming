import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BerryPicking2 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
        PrintWriter out = new PrintWriter(new File("berries.out"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Integer[] b = new Integer[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(b, Collections.reverseOrder());

        Integer[] baskets = Arrays.copyOfRange(b, 0, k);
        while (true){
            boolean pos = false;
            for (int i = 0; i < k / 2; i++) {
                int smallest = baskets[k - 1];
                int cur = baskets[i];
                int middle = baskets[k / 2];
                if ((cur - smallest - 1) >= middle) {
                    int remove = 1;
                    while (cur - smallest - remove >= middle && smallest + remove <= cur - smallest - remove){
                        remove++;
                    }
                    remove--;
                    baskets[i] -= (smallest + remove);
                    baskets[k - 1] += remove;
                    Arrays.sort(baskets, Collections.reverseOrder());
                    pos = true;
                    break;
                }
            }
            if (!pos){
                break;
            }
        }

        int sum = 0;
        for (int i = k / 2; i < k; i++){
            sum += baskets[i];
        }
        out.println(sum);
        out.close();
    }
}
