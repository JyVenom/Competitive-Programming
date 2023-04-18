import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FamilyTree {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("family.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("family.out")); //or what it calls for ("promote.out")

        String line = sc.nextLine();
        int cows = Integer.parseInt(line.substring(0,line.indexOf(' ')));
        String cow1 = line.substring(line.indexOf(' ') + 1, line.lastIndexOf(' '));
        String cow2 = line.substring(line.lastIndexOf(' ') + 1);

        ArrayList<String> mothers = new ArrayList<>(cows);
        ArrayList<String> daughters = new ArrayList<>(cows);
        ArrayList<String> cow1FamilyLine = new ArrayList<>(1);
        ArrayList<String> cow2FamilyLine = new ArrayList<>(1);
        cow1FamilyLine.add(cow1);
        cow2FamilyLine.add(cow2);

        for (int i = 0; i < cows; i++) {
            line = sc.nextLine();
            mothers.add(line.substring(0, line.indexOf(' ')));
            daughters.add(line.substring(line.indexOf(' ') + 1));
        }

        addMother(cow1FamilyLine, mothers, daughters, cow1);
//        addDaughter(cow1FamilyLine, mothers, daughters, cow1);
        addMother(cow2FamilyLine, mothers, daughters, cow2);
//        addDaughter(cow2FamilyLine, mothers, daughters, cow2);

        boolean ran = false;
        for (int i = 0; i < cows; i++){
            if (mothers.get(i).equals(cow1)){
                if (daughters.get(i).equals(cow2)){
                    out.println(cow1 + " is the mother of " + cow2);
                    ran = true;
                }
            }else if (mothers.get(i).equals(cow2)){
                if (daughters.get(i).equals(cow1)){
                    out.println(cow2 + " is the mother of " + cow1);
                    ran = true;
                }
            }
        }
        if (!ran) {
            int relationship = -1;
            int branches = 0;
            if (cow2FamilyLine.size() == Math.min(cow1FamilyLine.size(), cow2FamilyLine.size())) { //cow 2 is older
                for (int i = cow1FamilyLine.size() - 1; i >= 0; i--) {
                    if (cow2FamilyLine.contains(cow1FamilyLine.get(i))) {
                        relationship = (cow1FamilyLine.size() - 1) - i;
                        branches = cow2FamilyLine.indexOf(cow2) - cow2FamilyLine.indexOf(cow1FamilyLine.get(i));
                        break;
                    }
                }
            } else { //cow 1 is older
                for (int i = cow2FamilyLine.size() - 1; i >= 0; i--) {
                    if (cow1FamilyLine.contains(cow2FamilyLine.get(i))) {
                        relationship = (cow2FamilyLine.size() - 1) - i;
                        branches = cow1FamilyLine.indexOf(cow1) - cow1FamilyLine.indexOf(cow2FamilyLine.get(i));
                        break;
                    }
                }
            }

            if (cow1FamilyLine.size() > cow2FamilyLine.size()) { //cow2 is older
                String temp = cow1;
                cow1 = cow2;
                cow2 = temp;
            }
            if (branches > 1) {
                out.println("COUSINS");
            } else if (relationship == 1) {
                out.println("SIBLINGS");
            } else if (relationship == -1) {
                out.println("NOT RELATED");
            } else {
                if (relationship == 2) {
                    if (branches == 0) {
                        out.println(cow1 + " is the grand-mother of " + cow2);
                    }
                    if (branches == 1) {
                        out.println(cow1 + " is the aunt of " + cow2);
                    }
                }
                if (relationship > 2) {
                    out.print(cow1 + " is the ");
                    for (int i = 0; i < relationship - 2; i++) {
                        out.print("great-");
                    }
                    if (branches == 0) {
                        out.println("grand-mother of " + cow2);
                    }
                    if (branches == 1) {
                        out.println("aunt of " + cow2);
                    }
                }
            }
        }
        out.close();
    }

    private static void addMother (ArrayList<String> familyLine, ArrayList<String> mothers, ArrayList<String> daughters, String cow){
        if (daughters.contains(cow)){
            familyLine.add(familyLine.indexOf(cow), mothers.get(daughters.indexOf(cow)));
            addMother(familyLine, mothers,daughters,mothers.get(daughters.indexOf(cow)));
        }
    }

    private static void addDaughter (ArrayList<String> familyLine, ArrayList<String> mothers, ArrayList<String> daughters, String cow){
        if (mothers.contains(cow)){
            familyLine.add(familyLine.indexOf(cow) + 1, daughters.get(mothers.indexOf(cow)));
            addDaughter(familyLine, mothers,daughters,daughters.get(mothers.indexOf(cow)));
        }
    }
}
