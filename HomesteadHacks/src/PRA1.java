import java.util.ArrayList;
import java.util.Scanner;

public class PRA1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int length = sc.nextInt();
            int[] lights = new int[length];
            for (int j = 0; j < length; j++){
                lights[j] = sc.nextInt();
            }

            int count = 0;
            for (int j = 1; j < length - 1; j++){
                if (lights[j - 1] == 1 && lights[j] == 0 && lights[j + 1] == 1){
                    lights[j + 1] = 0;
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
