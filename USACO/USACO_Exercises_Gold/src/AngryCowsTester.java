import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class AngryCowsTester {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("angry.in"));

        int n = Integer.parseInt(br.readLine());
        int[] hay = new int[n];
        for (int i = 0; i < n; i++) {
            hay[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(hay);
        Scanner sc = new Scanner(System.in);
        System.out.println("location: ");
        int loc = sc.nextInt();
        System.out.println("power: ");
        int pow = sc.nextInt();
        int max = loc + pow;
        int pow2 = pow;
        int init;
        int temp = Arrays.binarySearch(hay, loc);
        if (temp >= 0) {
            init = temp;
        }
        else {
            init = Math.abs(temp + 1);
        }
        for (int i = init; i < n; i++) {
            if (hay[i] > max) {
                max = hay[i - 1] + --pow2;
                if (hay[i] > max) {
                    System.out.println(false);
                    break;
                }
            }
        }
        if (temp < 0) {
            init = Math.abs(temp + 1) - 1;
        }
        max = loc - pow;
        pow2 = pow;
        for (int i = init; i >= 0; i--) {
            if (hay[i] < max) {
                max = hay[i + 1] - --pow2;
                if (hay[i] < max) {
                    System.out.println(false);
                    break;
                }
            }
        }
    }
}
