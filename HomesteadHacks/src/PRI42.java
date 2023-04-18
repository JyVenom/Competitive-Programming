import java.util.ArrayList;
import java.util.Scanner;

public class PRI42 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int i = 0; i < cases; i++) {
            int length = sc.nextInt();
            String line = sc.nextLine();
            ArrayList<Integer> number = new ArrayList<>(length);
            for (int j = 1; j <= length; j++){
                number.add(Character.getNumericValue(line.charAt(j)));
            }
            boolean tooShort = false;
            while (number.get(0) != 8){
                number.remove(0);
                length--;
                if (length < 11){
                    tooShort = true;
                    break;
                }
            }
            if (tooShort){
                System.out.println("NO");
            }
            else {
                System.out.println("YES");
            }
        }
    }
}
