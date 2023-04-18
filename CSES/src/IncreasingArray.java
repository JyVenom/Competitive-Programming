import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IncreasingArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        nums[0] = Integer.parseInt(st.nextToken());
        long count = 0;
        for (int i = 1; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if (nums[i] < nums[i - 1]) {
                count += nums[i - 1] - nums[i];
                nums[i] = nums[i - 1];
            }
        }

        pw.println(count);
        pw.close();
    }
}
