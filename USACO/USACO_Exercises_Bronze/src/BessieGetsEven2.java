import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BessieGetsEven2 {
    private static int[][] num = new int[256][2];
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("geteven.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("geteven.out")); //or what it calls for ("promote.out)

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++){
            String line = sc.nextLine();
            char letter = line.charAt(0);
            int value = Integer.parseInt(line.substring(2));
            if (is_even(value)){
                num[letter][0]++; //num evens for char letter
            }
            else {
                num[letter][1]++; //num odds for char letter
            }
        }

        int result = 0;

        for (int b = 0; b < 2; b++) {
            for (int e = 0; e < 2; e++) {
                for (int s = 0; s < 2; s++) {
                    for (int i = 0; i < 2; i++) {
                        for (int g = 0; g < 2; g++) {
                            for (int o = 0; o < 2; o++) {
                                for (int m = 0; m < 2; m++) {
                                    if (is_even((b + e + s + s + i + e) * (g + o + e + s) * (m + o + o))) {
                                        result += num['B'][b] * num['E'][e] * num['S'][s] * num['I'][i] * num['G'][g] * num['O'][o] * num['M'][m];
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        out.println(result);
        out.close();
    }

    private static boolean is_even(int x)
    {
        return x % 2 == 0;
    }
}