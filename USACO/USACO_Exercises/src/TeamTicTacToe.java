import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class TeamTicTacToe {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("tttt.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("tttt.out")); //or what it calls for ("promote.out")

        Character[][] data = new Character[3][3];
        for (int i = 0; i < 3; i++){
            String line = sc.nextLine();
            data[i][0] = line.charAt(0);
            data[i][1] = line.charAt(1);
            data[i][2] = line.charAt(2);
        }

        int singleCowWins = 0;
        int teamCowWins = 0;

        HashSet<Character> row1 = new HashSet<>(1);
        HashSet<Character> row2 = new HashSet<>(1);
        HashSet<Character> row3 = new HashSet<>(1);
        HashSet<Character> col1 = new HashSet<>(1);
        HashSet<Character> col2 = new HashSet<>(1);
        HashSet<Character> col3 = new HashSet<>(1);
        HashSet<Character> dia1 = new HashSet<>(1);
        HashSet<Character> dia2 = new HashSet<>(1);

        for (int i = 0; i < 3; i++){
            row1.add(data[0][i]);
            row2.add(data[1][i]);
            row3.add(data[2][i]);
            col1.add(data[i][0]);
            col2.add(data[i][1]);
            col3.add(data[i][2]);
            dia1.add(data[i][i]);
            dia2.add(data[i][2-i]);
        }

        if (row1.size() == 1){
            singleCowWins++;

        }
        if (row1.size() == 2){
            teamCowWins++;
        }
        if (row2.size() == 1){
            singleCowWins++;
        }
        if (row2.size() == 2){
            teamCowWins++;
        }
        if (row3.size() == 1){
            singleCowWins++;
        }
        if (row3.size() == 2){
            teamCowWins++;
        }
        if (col1.size() == 1){
            singleCowWins++;
        }
        if (col1.size() == 2){
            teamCowWins++;
        }
        if (col2.size() == 1){
            singleCowWins++;
        }
        if (col2.size() == 2){
            teamCowWins++;
        }
        if (col3.size() == 1){
            singleCowWins++;
        }
        if (col3.size() == 2){
            teamCowWins++;
        }
        if (dia1.size() == 1){
            singleCowWins++;
        }
        if (dia1.size() == 2){
            teamCowWins++;
        }
        if (dia2.size() == 1){
            singleCowWins++;
        }
        if (dia2.size() == 2){
            teamCowWins++;
        }

        out.println(singleCowWins);
        out.println(teamCowWins);
        out.close();
    }
}
