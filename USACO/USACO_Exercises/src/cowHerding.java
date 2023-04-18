import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class cowHerding {
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
        int minMoves = 0;
        int maxMoves = 0;
        boolean isInOrder = false;
        if ((a + b + c) == (((max + min) / 2) * 3)){
            isInOrder = true;
        }
        while (!isInOrder) {
            if (a == min) {
                if (b == max) {
                    if (b != c + 1) {
                        a = c + 1;
                        minMoves++;
                    } else {
                        a = b + 1;
                        minMoves++;
                        maxMoves = 1;
                        break;
                    }
                    if (b != a + 1){
                        b = a + 1;
                        minMoves++;
                        break;
                    }
                } else if (c == max) {
                    if (c != b + 1) {
                        a = b + 1;
                        minMoves++;
                    } else {
                        a = c + 1;
                        minMoves++;
                        maxMoves = 1;
                        break;
                    }
                    if (c != a + 1){
                        c = a + 1;
                        minMoves++;
                        break;
                    }
                }
            } else if (b == min) {
                if (a == max) {
                    if (a != c + 1) {
                        b = c + 1;
                        minMoves++;
                    } else {
                        b = a + 1;
                        minMoves++;
                        maxMoves = 1;
                        break;
                    }
                    if (a != b + 1){
                        a = b + 1;
                        minMoves++;
                        break;
                    }
                } else if (c == max) {
                    if (c != a + 1) {
                        b = a + 1;
                        minMoves++;
                    } else {
                        b = c + 1;
                        minMoves++;
                        maxMoves = 1;
                        break;
                    }
                    if (c != b + 1){
                        c = b + 1;
                        minMoves++;
                        break;
                    }
                }
            } else {
                if (a == max) {
                    if (a != b + 1) {
                        c = b + 1;
                        minMoves++;
                    } else {
                        c = a + 1;
                        minMoves++;
                        maxMoves = 1;
                        break;
                    }
                    if (a != c + 1){
                        a = c + 1;
                        minMoves++;
                        break;
                    }
                } else if (b == max) {
                    if (b != a + 1) {
                        c = a + 1;
                        minMoves++;
                    } else {
                        c = b + 1;
                        minMoves++;
                        maxMoves = 1;
                        break;
                    }
                    if (b != c + 1){
                        b = c + 1;
                        minMoves++;
                        break;
                    }
                }
            }
        }
        if (max == 0){
            maxMoves = 2;
        }
        out.println(minMoves);
        out.println(maxMoves);
        out.close();
    }
}
