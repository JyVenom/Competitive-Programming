public class snailTest {
    public static void main(String[] args) {
        int n = 120;
        int[][] data = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < Math.min(26, n); j++) {
                if (Math.random() < 0.1) {
                    System.out.print((char)('A' + j) + "" + (i + 1));
                    System.out.println();
                    data[i][j] = 1;
                }
            }
        }


        for (int i = 0; i < n; i++) {
            System.out.print(data[i][0]);
            for (int j = 1; j < n; j++) {
                System.out.print(" " + data[i][j]);
            }
            System.out.println();
        }
    }
}
