import java.util.Scanner;

public class PRI3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int numCoins = sc.nextInt();
            int[] coins = new int[101];
            for (int j = 0; j < numCoins; j++){
                coins[sc.nextInt()]++;
            }
            int max = 0;
            for (int j = 1; j <= 100; j++){
                max = Math.max(max, coins[j]);
            }
            System.out.println(max);
        }
    }
}
