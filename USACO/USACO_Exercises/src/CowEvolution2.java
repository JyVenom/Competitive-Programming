import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CowEvolution2 {
    private int occurrences;
    private String characteristic;
    private CowEvolution2(int x, String j){
        occurrences = x;
        characteristic = j;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("evolution.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("evolution.out")); //or what it calls for ("evolution.out")

//        adds the given elements (evolution.in) to an x by 25 2d String array where x is the number of sub-populations (the first line of evolution.in)
        int lines = Integer.parseInt(sc.nextLine());
        boolean good = true;
        String[][] evolution_traits = new String[lines][25];
        String temp_line;
        String temp_word;
        int prev_index = 1;
        CowEvolution2[] sortedList = new CowEvolution2[25];
        for (int i = 0; i < lines; i++) {
            temp_line = sc.nextLine();
            int i1 = Integer.parseInt(String.valueOf(temp_line.charAt(0)));
            for (int x = 0; x < i1; x++) {
                if (x != i1 - 1) {
                    temp_word = temp_line.substring(prev_index + 1, temp_line.indexOf(" ", prev_index + 1));
                } else {
                    temp_word = temp_line.substring(prev_index + 1);
                }
                boolean hasAdded = false;
                for (int j = 0; j < 25; j++){
                    if (sortedList[j] != null && sortedList[j].characteristic.equals(temp_word)){
                        sortedList[j].occurrences++;
                        hasAdded = true;
                        break;
                    }
                }
                if (!hasAdded){
                    for (int j = 0; j < 25; j++){
                        if (sortedList[j] == null){
                            sortedList[j] = new CowEvolution2(1, temp_word);
                            break;
                        }
                    }
                }
                prev_index = temp_line.indexOf(" ", prev_index + 1);
                evolution_traits[i][x] = temp_word;
            }
            prev_index = 1;
        }
        
//        sort sortedList based on number of occurrences, from most to least
        int largest_Line = 0;
        for (int i = 0; i < 25; i++){
            largest_Line = i;
            for (int j = i; j < 25; j++){
                if (sortedList[j] != null && sortedList[largest_Line] != null && sortedList[j].occurrences > sortedList[largest_Line].occurrences){
                    largest_Line = j;
                }
            }
            CowEvolution2 temp = sortedList[i];
            sortedList[i] = sortedList[largest_Line];
            sortedList[largest_Line] = temp;
        }

//        make a new list with every characteristic of every line that has whatever characteristic is currently being looped, but with only one of each characteristic (no repeats)
        for (CowEvolution2 cowEvolution2 : sortedList) {
            if (cowEvolution2 != null) {
                String currentCharacteristic = cowEvolution2.characteristic;
                String[] combinedList = new String[25];
                for (String[] evolutionTrait : evolution_traits) {
                    for (int k = 0; k < evolution_traits[0].length; k++) {
                        if (evolutionTrait[k] != null && evolutionTrait[k].equals(currentCharacteristic)) {
                            for (int x = 0; x < evolution_traits[0].length; x++) {
                                if (evolutionTrait[x] != null) {
                                    boolean hasAdded = false;
                                    for (String s : combinedList) {
                                        if (evolutionTrait[x] != null && s != null && s.equals(evolutionTrait[x])) {
                                            hasAdded = true;
                                            break;
                                        }
                                    }
                                    if (!hasAdded) {
                                        for (int y = 0; y < combinedList.length; y++) {
                                            if (combinedList[y] == null) {
                                                combinedList[y] = evolutionTrait[x];
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                boolean notHaveCurrentCharacteristic;
                boolean haveCombinedListElement;
                boolean havePrinted = false;
                for (String[] evolution_trait : evolution_traits) {
                    notHaveCurrentCharacteristic = true;
                    haveCombinedListElement = false;
                    for (String value : evolution_trait) {
                        for (String s : combinedList) {
                            if (value != null && value.equals(currentCharacteristic)) {
                                notHaveCurrentCharacteristic = false;
                            }
                            if (value != null && value.equals(s)) {
                                haveCombinedListElement = true;
                            }
                        }
                    }
                    if (!havePrinted && notHaveCurrentCharacteristic && haveCombinedListElement) {
                        out.println("no");
                        good = false;
                        havePrinted = true;
                        break;
                    }
                }
                if (good) {
                    out.println("yes");
                }
                out.close();
                break;
            }
        }
    }
}
