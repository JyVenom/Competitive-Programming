import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

public class FairPhotography3 {
    public static void main(String[] args) throws Exception {
        int problem = 1;
        long start = System.currentTimeMillis();
//        String problem = "fairphoto";
        BufferedReader br = new BufferedReader(new FileReader(problem + ".in"));
        PrintWriter out = new PrintWriter(new File("fairphoto.out"));

        int n = Integer.parseInt(br.readLine());
        int[][] data = new int[n][2];
        int w = 0;
        int s = 0;

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            data[i][0] = Integer.parseInt(line[0]);
            data[i][1] = line[1].charAt(0) == 'W' ? 1 : -1;
//            "S" = 83 -1
//            "W" = 87 1
            if (line[1].equals("W")){
                w++;
            }
            else {
                s++;
            }
        }
        Arrays.sort(data, Comparator.comparingInt(arr -> arr[0]));

        if (w >= s){
            if (n % 2 == 0){
                out.println(data[n - 1][0] - data[0][0]);
            }
            else {
                int leftDist = data[1][0] - data[0][0];
                int rightDist = data[n - 1][0] - data[n - 2][0];
                if (leftDist < rightDist) {
                    out.println(data[n - 1][0] - data[1][0]);
                }
                else {
                    out.println(data[n - 2][0] - data[0][0]);
                }
            }
        }
        else {
            int[] val = new int[n + 1];
            int[] first = new int[2 * n];
            Arrays.fill(first, n + 1);
            first[n] = 0;
            for (int i = 1; i <= n; i++){
                val[i] = val[i - 1] + data[i - 1][1];
                if (first[val[i] + n] == n + 1){
                    first[val[i] + n] = i;
                }
            }
            for (int i = 1; i < 2 * n; i++){
                first[i] = Math.min(first[i], first[i - 1]);
            }

            int max = 0;
            for (int i = 0; i < n; i++) {
                //If max range is valid (even number of cows (so can have same number of each after painting/changing if needed), with num whites >= num spotted), update
                if ((i - first[val[i + 1] + n]) % 2 == 1)
                    max = Math.max(max, data[i][0] - data[first[val[i + 1] + n]][0]);

                //If not (either more spotted than whites or odd number of cows (so cant have equal amounts of each), see if we can remove the last cow
                else if (first[val[i + 1] + n] + 2 <= n && val[i + 1] - val[first[val[i + 1] + n] + 2] >= 0)
                    max = Math.max(max, data[i][0] - data[first[val[i + 1] + n] + 1][0]);
            }
            out.println(max);
        }
        out.close();

        long end = System.currentTimeMillis();
        System.out.println(end - start + " Milliseconds");
        BufferedReader ans = new BufferedReader(new FileReader(problem + ".out"));
        BufferedReader my = new BufferedReader(new FileReader("fairphoto.out"));
        int temp1 = Integer.parseInt(ans.readLine());
        int temp2 = Integer.parseInt(my.readLine());
        if (temp1 == temp2){
            System.out.println("Correct");
        }
        else {
            System.out.println("Wrong");
            System.out.println(temp1);
            System.out.println(temp2);
        }
    }
}
