import java.util.Scanner;

public class PRB4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++){
            int v = sc.nextInt();
            if (v % 2 == 0){
                System.out.println("EVEN");
            }
            else {
                System.out.println("ODD");
            }
        }
    }
}
