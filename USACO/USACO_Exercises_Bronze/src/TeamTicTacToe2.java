import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class TeamTicTacToe2 {
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

        HashSet<Character> singleCows = new HashSet<>();
        HashSet<String> teamCows = new HashSet<>();
        ArrayList<HashSet<Character>> combinations = new ArrayList<>(8); //row1, row2, row3, col1, col2, col3, dia1, dia2
        combinations.add(new HashSet<>(1));
        combinations.add(new HashSet<>(1));
        combinations.add(new HashSet<>(1));
        combinations.add(new HashSet<>(1));
        combinations.add(new HashSet<>(1));
        combinations.add(new HashSet<>(1));
        combinations.add(new HashSet<>(1));
        combinations.add(new HashSet<>(1));

        for (int i = 0; i < 3; i++){
            combinations.get(0).add(data[0][i]);
            combinations.get(1).add(data[1][i]);
            combinations.get(2).add(data[2][i]);
            combinations.get(3).add(data[i][0]);
            combinations.get(4).add(data[i][1]);
            combinations.get(5).add(data[i][2]);
            combinations.get(6).add(data[i][i]);
            combinations.get(7).add(data[i][2-i]);
        }

        for (int i = 0; i < 8; i++){
            if (combinations.get(i).size() == 1){
                if (!singleCows.contains(combinations.get(i).toString().charAt(1))){
                    singleCows.add(combinations.get(i).toString().charAt(1));
                    singleCowWins++;
                }
            }
            else if (combinations.get(i).size() == 2){
                if (!teamCows.contains(combinations.get(i).toString())) {
                    teamCows.add(combinations.get(i).toString());
                    teamCowWins++;
                }
            }
        }

        out.println(singleCowWins);
        out.println(teamCowWins);
        out.close();
    }
}
