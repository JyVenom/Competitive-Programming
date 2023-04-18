import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GuessTheAnimal6 {
    private int occurrences;
    private String characteristic;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("guess.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("guess.out")); //or what it calls for ("promote.out")

        int numAnimals = Integer.parseInt(sc.nextLine());
        String[][] data = new String[numAnimals][102];
        String line;
        //Fills in data with given information
        for (int i = 0; i < numAnimals; i++){
            line = sc.nextLine();
            data[i][0] = line.substring(0, line.indexOf(' '));
            int temp = line.indexOf(' ', line.indexOf(' ') + 1);
            data[i][1] = line.substring(line.indexOf(' ') + 1, temp);
            for (int j = 0; j < Integer.parseInt(data[i][1]); j ++){
                temp = line.indexOf(' ', temp) + 1;
                if (j == (Integer.parseInt(data[i][1]) - 1)){
                    data[i][j + 2] = line.substring(temp);
                }
                else {
                    data[i][j + 2] = line.substring(temp, line.indexOf(' ', temp));
                }
            }
        }

        //Makes a list of all characteristics
        ArrayList<GuessTheAnimal6> characteristicsList = new ArrayList<>();
        boolean has;
        //First two for loops are for the x and y for data, third is too see if already have
        for (int i = 0; i < numAnimals; i++){
            for (int j = 2; j <= (1 + Integer.parseInt(data[i][1])); j++){
                has = false;
                for (GuessTheAnimal6 guessTheAnimal : characteristicsList) {
                    if (guessTheAnimal.characteristic.equals(data[i][j])) {
                        guessTheAnimal.setOccurrences(guessTheAnimal.occurrences + 1);
                        has = true;
                    }
                }
                if (!has){
                    GuessTheAnimal6 temp = new GuessTheAnimal6(1, data[i][j]);
                    characteristicsList.add(temp);
                }
            }
        }
        String[][] temp = new String[characteristicsList.size()][2];
        for (int i = 0; i < characteristicsList.size(); i++){
            temp[i][0] = "" + characteristicsList.get(i).occurrences;
            temp[i][1] = characteristicsList.get(i).characteristic;
        }
        //Sort characteristics in the list based on occurrence
        Arrays.sort(temp, (first1, second) -> Integer.valueOf(second[0]).compareTo(
                Integer.valueOf(first1[0])
        ));
        characteristicsList = new ArrayList<>();
        for (String[] strings : temp) {
            characteristicsList.add(new GuessTheAnimal6(Integer.parseInt(strings[0]), strings[1]));
        }
//
//        String[][] characteristics = new String[characteristicsList.size()][2];
//        for (int i = 0; i < characteristicsList.size(); i++){
//            characteristics[i][0] = "" + characteristicsList.get(i).occurrences;
//            characteristics[i][1] = characteristicsList.get(i).characteristic;
//        }

        int finalCount = 0;

//        int firstOther = 0;
//        for (int i = 0; i < characteristicsList.size(); i++){
//            if (characteristicsList.get(i).occurrences == characteristicsList.get(0).occurrences - 1){
//                firstOther = i - 1;
//                break;
//            }
//        }

        for (int i = 0; i < data.length - 1; i++) {
            //go through characteristicsList, crossing off each characteristic in order, until only one animal is left
            String[][] finalList = new String[numAnimals][102];
            System.arraycopy(data, 0, finalList, 0, data.length);
            ArrayList<GuessTheAnimal6> characteristicsListTemp = new ArrayList<>(characteristicsList);
            GuessTheAnimal6 guessTheAnimal6 = characteristicsList.get(i);
//            characteristicsList.remove(i);
            characteristicsList.add(0,guessTheAnimal6);

            int count = 0;
            //First loop to go through characteristicsList, second to go through finalList to remove n/a animals
            for (GuessTheAnimal6 guessTheAnimal : characteristicsList) {
                String[][] temp2 = new String[numAnimals][102];
                for (int j = 0; j < numAnimals; j++) {
                    boolean contains = false;
                    for (int k = 2; k < 102; k++) {
                        if (finalList[j][k] != null) {
                            if (finalList[j][k].equals(guessTheAnimal.characteristic)) {
                                contains = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (contains) {
                        for (int k = 0; k < finalList.length; k++) {
                            if (temp2[k][0] == null) {
                                temp2[k] = finalList[j];
//                                haveToBreak = true;
                                break;
                            }
                        }
                    }
                }
                if (temp2[0][0] != null) {
                    count++;
                    finalList = new String[numAnimals][102];
                    System.arraycopy(temp2, 0, finalList, 0, temp2.length);
                }
                if (finalList[0][0] != null && finalList[1][0] == null) {
                    if (count > finalCount) {
                        finalCount = count;
                    }
                    break;
                }

//                //Makes a list of all characteristics
//                characteristicsList = new ArrayList<>();
//                boolean has2;
//                //First two for loops are for the x and y for finalList, third is too see if already have
//                boolean haveBreak = false;
//                for (int b = 0; b < numAnimals; b++){
//                    if (haveBreak){
//                        break;
//                    }
//                    for (int c = 2; c <= (1 + Integer.parseInt(finalList[b][1])); c++){
//                        has2 = false;
//                        for (GuessTheAnimal3 guessTheAnimal2 : characteristicsList) {
//                            if (guessTheAnimal2.characteristic != null) {
//                                if (guessTheAnimal2.characteristic.equals(finalList[b][c])) {
//                                    guessTheAnimal2.setOccurrences(guessTheAnimal2.occurrences + 1);
//                                    has2 = true;
//                                }
//                            }
//                            else {
//                                break;
//                            }
//                        }
//                        if (!has2){
//                            GuessTheAnimal3 temp3 = new GuessTheAnimal3(1, finalList[b][c]);
//                            characteristicsList.add(temp3);
//                        }
//                        if (finalList[b + 1][1] == null) {
//                            if (c == 1 + Integer.parseInt(finalList[b][1])){
//                                haveBreak = true;
//                                break;
//                            }
//                        }
//                    }
//                }
//                String[][] temp4 = new String[characteristicsList.size()][2];
//                for (int b = 0; b < characteristicsList.size(); b++){
//                    temp4[b][0] = "" + characteristicsList.get(b).occurrences;
//                    temp4[b][1] = characteristicsList.get(b).characteristic;
//                }
//                //Sort characteristics in the list based on occurrence
//                Arrays.sort(temp4, (first1, second) -> Integer.valueOf(second[0]).compareTo(
//                        Integer.valueOf(first1[0])
//                ));
//                characteristicsList = new ArrayList<>();
//                for (String[] strings : temp4) {
//                    characteristicsList.add(new GuessTheAnimal3(Integer.parseInt(strings[0]), strings[1]));
//                }
            }
            characteristicsList = characteristicsListTemp;
        }
        out.println(finalCount);
        out.close();
    }

    private GuessTheAnimal6(int o, String c){
        occurrences = o;
        characteristic = c;
    }

    private void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }
//
//    private boolean isSorted (ArrayList<GuessTheAnimal> list){
//        if (list.size() == 1){
//            return true;
//        }
//        return (list.get(list.size() - 1).occurrences > list.get(list.size() - 2).occurrences) && isSorted((ArrayList<GuessTheAnimal>) list.subList(0, list.size() - 2));
//    }
}

/*
make a list of all characteristics sorted by occurrence, from most common to least
repeat these steps, removing the first element in list after running each time, until list is empty:
    go through list, crossing off each characteristic in order, until only one animal is left
    compare number of crosses to a final number of crosses,
        if number of crosses is larger than final number of crosses, set final number of crosses to number of crosses
output final number of crosses
*/