import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CoinChange2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        int[] coins = {5, 7, 8, 9};
//        int[] coins = {3, 5, 11};
//        int[] coins = {5, 7, 15, 14, 19};
        int[] coins = {19, 23, 55, 66};
//        int sum = 49; 39 + 10, 33 + 11 + 5, 27 + 22, 24 + 20, 18 + 11 + 20, 12 + 22 + 15, 9 + 40, 6 + 33 + 10, 3 + 11 + 30, 44 + 5;
//        int sum = 49;
        int sum = 11789;

        int[][] data = new int[coins.length + 1][sum + 1];
        Arrays.fill(data[0], Integer.MAX_VALUE / 2);
        data[0][0] = 0;
        for (int i = 1; i <= coins.length; i++) {
            int I = i - 1;
            System.arraycopy(data[I], 0, data[i], 0, coins[I]);
            for (int j = coins[I]; j <= sum; j++) {
                data[i][j] = Math.min(data[I][j], data[i][j - coins[I]] + 1);
            }
        }

        System.out.println(data[coins.length][sum]);

        int[][] data2 = new int[coins.length + 1][sum + 1];
//        for (int i = 0; i <= coins.length; i++) {
//            data2[i][0] = 1;
//        }
        data2[0][0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            int I = i - 1;
            if (coins[I] >= 0) System.arraycopy(data2[i - 1], 0, data2[i], 0, coins[I]);
            for (int j = coins[I]; j <= sum; j++) {
                data2[i][j] = data2[i][j - coins[I]] + data2[i - 1][j];
            }
        }
        System.out.println(data2[coins.length][sum]);

        int[][] data3 = new int[coins.length + 1][sum + 1];
        Arrays.fill(data3[0], -1);
        data3[0][0] = 0;
        for (int i = 0; i < coins.length / 2; i++) {
            int temp = coins[i];
            coins[i] = coins[coins.length - i - 1];
            coins[coins.length - i - 1] = temp;
        }
        for (int i = 1; i <= coins.length; i++) {
            int I = i - 1;
            data3[i] = data3[I].clone();
            for (int j = coins[I]; j <= sum; j++) {
                if (data3[i][j - coins[I]] != -1) {
                    data3[i][j] = Math.max(data3[i][j], data3[i][j - coins[I]] + 1);
                }
            }
        }
        System.out.println(data3[coins.length][sum]);

        ArrayList<ArrayList<ArrayList<Integer>>> data4 = new ArrayList<>();
        for (int i = 0; i <= coins.length; i++) {
            ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
            for (int j = 0; j <= sum; j++) {
                temp.add(new ArrayList<>());
            }
            data4.add(temp);
        }
        int[][] data5 = new int[coins.length + 1][sum + 1];
        Arrays.fill(data5[0], -1);
        data5[0][0] = 0;
        for (int i = 1; i <= coins.length; i++) {
            int I = i - 1;
            data5[i] = data5[I].clone();
            ArrayList<ArrayList<Integer>> copy = new ArrayList<>(data4.get(I));
            data4.set(i, copy);
            for (int j = coins[I]; j <= sum; j++) {
                if (data5[i][j - coins[I]] != -1) {
                    int temp = data5[i][j - coins[I]] + 1;
                    if (temp > data5[i][j]) {
                        data5[i][j] = temp;
                        ArrayList<Integer> copy2 = new ArrayList<>(data4.get(i).get(j - coins[I]));
                        data4.get(i).set(j, copy2);
                        data4.get(i).get(j).add(coins[I]);
                    }
                }
            }
        }
        ArrayList<Integer> ans = new ArrayList<>(data4.get(coins.length).get(sum));
        Collections.sort(ans);
        int count = 1;
        System.out.print(ans.get(0) + " ");
        for (int i = 1; i < ans.size(); i++) {
            if (ans.get(i).equals(ans.get(i - 1))) {
                count++;
            }
            else {
                System.out.println(count);
                count = 1;
                System.out.print(ans.get(i) + " ");
            }
        }
        System.out.println(count);
//        System.out.println(ans);
        System.out.println(System.currentTimeMillis() - start);

        //min # of coins
        //max # of coins
        //total unique solution
        //min type of coins
    }
}
