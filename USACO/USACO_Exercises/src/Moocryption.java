import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Moocryption {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("moocrypt.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("moocrypt.out")); //or what it calls for ("promote.out

        String line = sc.nextLine();
        int rows = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int cols = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        char[][] data = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            line = sc.nextLine();
            for (int j = 0; j < cols; j++) {
                data[i][j] = line.charAt(j);
            }
        }

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int max = 0;
        int count;
        for (int i = 0; i < 26; i++){
            if (i == 12){
                continue;
            }
            for (int j = 0; j < 26; j++){
                if (j == i || j == 14){
                    continue;
                }
                count = findNumMoos(data, alphabet.charAt(i), alphabet.charAt(j));
                max = Math.max(max, count);
            }
        }

        out.println(max);
        out.close();
    }

    private static int findNumMoos (char[][] data, char m, char o){
        int count = 0;
        count += numDia1(data, m, o);
        count += numDia2(data, m, o);
        count += numHor(data, m, o);
        count += numVer(data, m, o);
        return count;
    }

    private static int numDia1 (char[][] data, char m, char o){
        int count = 0;
        for (int i = 0; i < data.length - 2; i++){
            for (int j = 0; j < data[0].length - 2; j++){
                if (data[i][j] == m && data[i + 1][j + 1] == o && data[i + 2][j + 2] == o){
                    count++;
                }
                if (data[i][j] == o && data[i + 1][j + 1] == o && data[i + 2][j + 2] == m){
                    count++;
                }
            }
        }
        return count;
    }

    private static int numDia2 (char[][] data, char m, char o){
        int count = 0;
        for (int i = 0; i < data.length - 2; i++){
            for (int j = 2; j < data[0].length; j++){
                if (data[i][j] == m && data[i + 1][j - 1] == o && data[i + 2][j - 2] == o){
                    count++;
                }
                if (data[i][j] == o && data[i + 1][j - 1] == o && data[i + 2][j - 2] == m){
                    count++;
                }
            }
        }
        return count;
    }

    private static int numHor (char[][] data, char m, char o){
        int count = 0;
        for (char[] datum : data) {
            for (int j = 0; j < data[0].length - 2; j++) {
                if (datum[j] == m && datum[j + 1] == o && datum[j + 2] == o) {
                    count++;
                }
                if (datum[j] == o && datum[j + 1] == o && datum[j + 2] == m) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int numVer (char[][] data, char m, char o){
        int count = 0;
        for (int i = 0; i < data.length - 2; i++){
            for (int j = 0; j < data[0].length; j++){
                if (data[i][j] == m && data[i + 1][j] == o && data[i + 2][j] == o){
                    count++;
                }
                if (data[i][j] == o && data[i + 1][j] == o && data[i + 2][j] == m){
                    count++;
                }
            }
        }
        return count;
    }
}
