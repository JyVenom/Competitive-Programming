import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Convention8 {
    private static int cows;
    private static int buses;
    private static int size;
    private static int[] times;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("convention.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("convention.out")); //or what it calls for ("promote.out)

        cows = sc.nextInt();
        buses = sc.nextInt();
        size = sc.nextInt();
        times = new int[cows];
        for (int i = 0; i < cows; i++){
            times[i] = sc.nextInt();
        }
        Arrays.sort(times);
        int maxWait = 0;
        for (int i = 1; i < times.length; i++) {
            maxWait = Math.max(maxWait, times[i] - times[i - 1]);
        }
        maxWait *= size;
        if (maxWait < 0) {
            maxWait = Integer.MAX_VALUE / 2;
        }
        int minWait = 1000000000;
        for (int i = 1; i < times.length; i++) {
            minWait = Math.min(minWait, times[i] - times[i - 1]);
        }
        minWait *= size;
        out.println(binarySearch(minWait, maxWait));
        out.close();
    }

    private static int binarySearch(int min, int max) {
        if (min == max) {
            return min;
        }
        if (min + 1 == max) { //corner case. will infinitely alternate between min and max
            if (possible(min)) {
                return min;
            }
            return max;
        }

//        other/else/main/not corner case
        int mid = (min + max) / 2;
        if (possible(mid)) {
            return binarySearch(min, mid);
        }
        else {
            return binarySearch(mid, max);
        }
    }

    private static boolean possible(int wait)
    {
        int wagons = 1;
        int firstArrival = times[0];
        int firstIndex = 0;
        for(int i = 1; i < cows; i++) {
            if (times[i] - firstArrival > wait || i + 1 - firstIndex > size) {
                wagons += 1;
                firstArrival = times[i];
                firstIndex = i;
            }
        }
        return (wagons <= buses);
    }
}
