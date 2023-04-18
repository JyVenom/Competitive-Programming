import java.util.Scanner;

public class PRI1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int w1 = sc.nextInt();
            int w2 = sc.nextInt();
            int years = 0;
            while (w1 <= w2) {
                years++;
                w1 *= 3;
                w2 *= 2;
            }
            System.out.println(years);
        }
    }
}
