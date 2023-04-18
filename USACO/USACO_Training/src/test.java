public class test {
    public static void main(String[] args) {
        double count = 0;
        for (int j = 0; j < 100000; j++) {
            int[] dice = new int[6];
            for (int i = 0; i < 6; i++) {
                dice[i] = (int) (Math.round(Math.random()));
            }
            if (dice[0] == dice[1] && dice[1] == dice[3] && dice[3] == dice[5]) {
                count++;
            } else if (dice[0] == dice[2] && dice[2] == dice[4] && dice[4] == dice[5]) {
                count++;
            } else if (dice[1] == dice[2] && dice[2] == dice[3] && dice[3] == dice[4]) {
                count++;
            }
        }
        System.out.println(count / 100000.0);
    }
}
