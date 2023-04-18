import java.util.Scanner;

public class PRI2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int students = sc.nextInt();
            int[] coins = new int[students];
            int max = 0;
            int cost = 0;
            for (int j = 0; j < students; j++){
                coins[j] = sc.nextInt();
                max = Math.max(max, coins[j]);
            }
            for (int j = 0; j < students; j++){
                cost += (max - coins[j]);
            }
            System.out.println(cost);
        }
    }
}
