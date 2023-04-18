import java.io.*;
import java.util.Arrays;

public class humble5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("humble.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));

        String[] line = br.readLine().split(" ");
        int k = Integer.parseInt(line[0]);
        int n = Integer.parseInt(line[1]);

        line = br.readLine().split(" ");
        int[] primes = new int[k];
        for (int i = 0; i < k; i++) {
            primes[i] = Integer.parseInt(line[i]);
        }
        Arrays.sort(primes);


        long start = System.currentTimeMillis();
        int[] humbles = new int[n + 1];
        int[] next = new int[k];
        humbles[0] = 1;
        for (int i = 1; i <= n; i++) {
            int best = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                while (next[j] < i && primes[j] * humbles[next[j]] <= humbles[i - 1]) {
                    next[j]++;
                }
                best = Math.min(best, primes[j] * humbles[next[j]]);
            }
            humbles[i] = best;
        }
        pw.println(humbles[n]);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        long[] humble = new long[n + 1];
        int[] nxt = new int[k];
        humble[0] = 1;
        for (int i = 1; i <= n; i++) {
            long best = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                while (primes[j] * humble[nxt[j]] <= humble[i - 1]) {
                    nxt[j]++;
                }
                best = Math.min(best, primes[j] * humble[nxt[j]]);
            }
            humble[i] = best;
        }
        pw.println(humble[n]);
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        pw.println(nthSuperUglyNumber(n, primes));
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        pw.println(humbleAns(primes, n));
        System.out.println(System.currentTimeMillis() - start);

        pw.close();
    }

    private static int nthSuperUglyNumber(int n, int[] primes) {
        if (primes.length == 0 || n == 0)
            return 0;

        int[] uglies = new int[n];
        int[] counters = new int[primes.length];
        uglies[0] = 1;
        for (int i = 1; i < n; i++) {
            int nextMin = Integer.MAX_VALUE;
            // loop through the primes and find the next smallest one
            for (int j = 0; j < primes.length; j++)
                // we found a smaller value -- also don't add duplicates
                if (nextMin > uglies[counters[j]] * primes[j])
                    nextMin = uglies[counters[j]] * primes[j];
            uglies[i] = nextMin;
            // advance all matching counters to avoid duplicates
            for (int j = 0; j < primes.length; j++)
                if (uglies[counters[j]] * primes[j] == nextMin)
                    counters[j]++;
        }
        return uglies[n - 1];
    }

    public static int humbleAns(int[] nums, int nth) {

        int[] res = new int[nth +1];

        res[0] =1;

        int count =0;

        int start = 0;



        while(count <nth) {

            int current = res[count];

            int min = Integer.MAX_VALUE;



            while(start<res.length && res[start]* nums[nums.length-1] < current)

                start++;



            for(int i= start; i<=count; i++) {

                for(int j: nums) {

                    if(res[i]*j > res[count]) {

                        if(res[i]*j < min)

                            min = res[i]*j;

                        break;

                    }

                }

            }

            count++;

            res[count] = min;





        }





        return res[nth];





    }
}
