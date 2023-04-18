/*
ID: jerryya2
LANG: JAVA
TASK: humble
*/

import java.io.*;
import java.util.Arrays;

public class humble4 {
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

        pw.println(humbleAns(primes, n));
        pw.close();
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
