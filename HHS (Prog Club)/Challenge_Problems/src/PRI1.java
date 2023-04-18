import java.util.Scanner;

public class PRI1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        String[] moves = {"hoof", "scissors", "paper"};
        int n = Integer.parseInt(sc.nextLine());
        int[] elsie = new int[n];
        for (int i = 0; i < n; i++) {
            String temp = sc.nextLine();
            if (temp.equals("hoof")) {
                elsie[i] = 0;
            } else if (temp.equals("paper")) {
                elsie[i] = 1;
            } else if (temp.equals("scissors")) {
                elsie[i] = 2;
            }
        }

        for (int i = 0; i < n; i++) {
            int cur = elsie[i];
            if (cur == 0) {
                System.out.println("paper");
            } else if (cur == 1) {
                System.out.println("scissors");
            } else if (cur == 2) {
                System.out.println("hoof");
            }
        }
    }
}
