import java.io.*;

public class window2 {
    int I;
    int top, bottom, left, right;
    private window2(int I, int x, int y, int X, int Y) {
        this.I = I;
        if (x < X) {
            left = x;
            right = X;
        }
        else {
            left = X;
            right = x;
        }

        if (y < Y) {
            top = Y;
            bottom = y;
        }
        else {
            top = y;
            bottom = Y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("milk4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk4.out")));

        String line = br.readLine();
        if (line.charAt(0) == 'w') {
            char I = line.charAt(2);
            int start = 4;
            int end = line.indexOf(',', start);
            int x = Integer.parseInt(line.substring(start, end));
            start = end + 1;
            end = line.indexOf(',', start);
            int y = Integer.parseInt(line.substring(start, end));
            start = end + 1;
            end = line.indexOf(',', start);
            int X = Integer.parseInt(line.substring(start, end));
            start = end + 1;
            end = line.indexOf(')', start);
            int Y = Integer.parseInt(line.substring(start, end));
            window2 window = new window2(I, x, y, X, Y);
        }
        else if (line.charAt(0) == 't') {

        }
        else if (line.charAt(0) == 'b') {

        }
        else if (line.charAt(0) == 'd') {

        }
        else if (line.charAt(0) == 'i') {

        }
    }

//    private static class windows {
//        int top, bottom, left, right;
//        public windows (int x, int y, int X, int Y) {
//            if (x < X) {
//                left = x;
//                right = X;
//            }
//            else {
//                left = X;
//                right = x;
//            }
//
//            if (y < Y) {
//                top = Y;
//                bottom = y;
//            }
//            else {
//                top = y;
//                bottom = Y;
//            }
//        }
//    }
}
