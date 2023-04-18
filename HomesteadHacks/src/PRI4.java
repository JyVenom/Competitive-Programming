import java.io.IOException;
import java.util.Scanner;

public class PRI4 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int k = 0;k < n;k ++) {
            int nums = sc.nextInt();
            String numbe2r = sc.nextLine();
            String number = sc.nextLine();
            Boolean yes = false;
            int index = 0;
            if(number.charAt(0) == '8') {
                System.out.println("YES");
            }else {
                for(int i = nums;i > 11;i --) {
                    if(number.charAt(index) == '8') {
                        yes = true;
                        break;
                    }
                    index ++;
                }
                if(yes) {
                    System.out.println("YES");
                }else {
                    System.out.println("NO");
                }
            }
        }
    }

}
