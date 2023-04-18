import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class cowHerding2 {
    public static void main(String[] args)  throws IOException {
        Scanner sc = new Scanner(new File("herding.in"));
        PrintWriter out = new PrintWriter(new File("herding.out"));

        int a;
        int b;
        int c;
        String pos = sc.nextLine();
        a = Integer.parseInt(pos.substring(0, pos.indexOf(' ')));
        b = Integer.parseInt(pos.substring(pos.indexOf(' ') + 1, pos.indexOf(' ',pos.indexOf(' ') + 1)));
        c = Integer.parseInt(pos.substring(pos.lastIndexOf(' ') + 1));

        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.max(b, c));
        int middle;
//        boolean mia = false;
//        boolean mib = false;
//        boolean mic = false;
        if (max == a){
            if (min == b){
                middle = c;
//                mic = true;
            }
            else {
                middle = b;
//                mib = true;
            }
        }
        else if (max == b){
            if (min == a){
                middle = c;
//                mic = true;
            }
            else {
                middle = a;
//                mia = true;
            }
        }
        else {
            if (min == a){
                middle = b;
//                mib = true;
            }
            else {
                middle = a;
//                mia = true;
            }
        }
        int minMoves = 0;
        int maxMoves = 0;
        boolean isInOrder = false;
        boolean isAveraged = (double)(a + b + c) == (((double)(max + min) / 2) * 3);
        if (isAveraged){
            isInOrder = true;
        }
        if (!isInOrder) {
            if ((max == middle + 2) || (middle == min + 2)) {
                minMoves = 1;
                if (middle - min > max - middle){
                    maxMoves = middle - min - 1;
                }
                else if (middle - min <= max - middle){
                    maxMoves = max - middle - 1;
                }
                else {
                    maxMoves = 1;
                }
            } else if (max > middle + 2) {
                minMoves = 2;
                if (middle - min > max - middle){
                    maxMoves = middle - min - 1;
                }
                else {
                    maxMoves = max - middle - 1;
                }
            } else {
                if (middle > min + 2) {
                    maxMoves = middle - min - 1;
                    minMoves = 2;
                } else {
                    maxMoves = 1;
                    minMoves = 1;
                }
            }
        }
        if (isInOrder){
            out.println(0);
            out.println(0);
        }
        else {
            out.println(minMoves);
            out.println(maxMoves);
        }
        out.close();
    }
}
