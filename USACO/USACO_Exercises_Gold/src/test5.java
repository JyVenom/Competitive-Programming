import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class test5 {
    public static void main(String[] args) throws IOException {
        int n = 1000000;
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = (int) (Math.random() * 33333) + 33333;
            b[i] = 100000 - a[i];
        }
        int[] temp = new int[n];
        int t = 2500;
        long start, end, sum = 0;
        for (int j = 0; j < t; j++) {
            start = System.currentTimeMillis();
            for (int i = 0; i < n; i++) {
                temp[i] = a[i] + b[i];
            }
            end = System.currentTimeMillis();;
            sum += (end - start);
        }
        System.out.println(sum);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.in")));
        pw.println(Arrays.toString(temp));
        pw.close();
    }
}
