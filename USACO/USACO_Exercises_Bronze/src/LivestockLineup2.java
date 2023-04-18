import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class LivestockLineup2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("lineup.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("lineup.out")); //or what it calls for ("promote.out)

        int n = Integer.parseInt(sc.nextLine());
        String[][] constraints = new String[n][2];
        ArrayList<ArrayList<String>> groupConstraints = new ArrayList<>();
        ArrayList<String> finalOrder = new ArrayList<>(8);
        finalOrder.add("Beatrice");
        finalOrder.add("Belinda");
        finalOrder.add("Bella");
        finalOrder.add("Bessie");
        finalOrder.add("Betsy");
        finalOrder.add("Blue");
        finalOrder.add("Buttercup");
        finalOrder.add("Sue");

        for (int i = 0; i < n; i++){
            String line = sc.nextLine();
            String c1 = line.substring(0, line.indexOf(' '));
            String c2 = line.substring(line.lastIndexOf(' ') + 1);
            if (before(c1, c2)){
                constraints[i][0] = c1;
                constraints[i][1] = c2;
            }
            else {
                constraints[i][0] = c2;
                constraints[i][1] = c1;
            }
            finalOrder.remove(constraints[i][0]);
            finalOrder.remove(constraints[i][1]);
        }

        ArrayList<Integer> skip = new ArrayList<>();
        for (int i = 0; i < n; i++){
            String cow1 = constraints[i][0];
            String cow2 = constraints[i][1];
            for (int j = i + 1; j < n; j++){
                if (constraints[j][0].equals(cow1)){
                    ArrayList<String> temp = new ArrayList<>(2);
                    temp.add(cow2);
                    temp.add(constraints[j][1]);
                    Collections.sort(temp);
                    temp.add(1, cow1);
                    groupConstraints.add(temp);
                    skip.add(i);
                    skip.add(j);
                }
                else if (constraints[j][1].equals(cow1)){
                    ArrayList<String> temp = new ArrayList<>(2);
                    temp.add(cow2);
                    temp.add(constraints[j][0]);
                    Collections.sort(temp);
                    temp.add(1, cow1);
                    groupConstraints.add(temp);
                    skip.add(i);
                    skip.add(j);
                }
                else if (constraints[j][0].equals(cow2)){
                    ArrayList<String> temp = new ArrayList<>(2);
                    temp.add(cow1);
                    temp.add(constraints[j][1]);
                    Collections.sort(temp);
                    temp.add(1, cow2);
                    groupConstraints.add(temp);
                    skip.add(i);
                    skip.add(j);
                }
                else if (constraints[j][1].equals(cow2)){
                    ArrayList<String> temp = new ArrayList<>(2);
                    temp.add(cow1);
                    temp.add(constraints[j][0]);
                    Collections.sort(temp);
                    temp.add(1, cow2);
                    groupConstraints.add(temp);
                    skip.add(i);
                    skip.add(j);
                }
            }
        }

        for (int i = 0; i < groupConstraints.size(); i++){
            boolean good = false;
            for (int j = 0; j < finalOrder.size(); j++){
                if (before(groupConstraints.get(i).get(0),finalOrder.get(j))){
                    good = true;
                    for (int k = 0; k < 3; k++){
                        finalOrder.add(j + k, groupConstraints.get(i).get(k));
                    }
                    break;
                }
            }
            if (!good){
                for (int k = 0; k < 3; k++){
                    finalOrder.add(groupConstraints.get(i).get(k));
                }
            }
        }

        for (int i = 0; i < constraints.length; i++){
            if (skip.contains(i)){
                continue;
            }
            boolean good = false;
            for (int j = 0; j < finalOrder.size(); j++){
                if (before(constraints[i][0], finalOrder.get(i))){
                    good = true;
                    for (int k = 0; k < 2; k++){
                        finalOrder.add(j + k, constraints[i][k]);
                    }
                    break;
                }
            }
            if (!good){
                for (int k = 0; k < 2; k++){
                    finalOrder.add(constraints[i][k]);
                }
            }
        }
        for (int i = 0; i < finalOrder.size(); i++){
            out.println(finalOrder.get(i));
        }
        out.close();
    }

    private static boolean before (String c1, String c2){ //if c1 comes before c2
        ArrayList<String> temp = new ArrayList<>(2);
        temp.add(c1);
        temp.add(c2);
        Collections.sort(temp);
        return temp.get(0).equals(c1);
    }
}
