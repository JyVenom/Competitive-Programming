import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GuessTheAnimal8 {
    private int occurrences;
    private String characteristic;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("guess.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("guess.out")); //or what it calls for ("promote.out")

        int numAnimals = Integer.parseInt(sc.nextLine());
        String[][] data = new String[numAnimals][102];
        String line;
        //Fills in data with given information
        for (int i = 0; i < numAnimals; i++) {
            line = sc.nextLine();
            data[i][0] = line.substring(0, line.indexOf(' '));
            int temp = line.indexOf(' ', line.indexOf(' ') + 1);
            data[i][1] = line.substring(line.indexOf(' ') + 1, temp);
            for (int j = 0; j < Integer.parseInt(data[i][1]); j++) {
                temp = line.indexOf(' ', temp) + 1;
                if (j == (Integer.parseInt(data[i][1]) - 1)) {
                    data[i][j + 2] = line.substring(temp);
                } else {
                    data[i][j + 2] = line.substring(temp, line.indexOf(' ', temp));
                }
            }
        }

        int largest = 0;
        for (int i = 0; i < numAnimals; i++){
            for (int j = i + 1; j < numAnimals; j++){
                largest = Math.max(largest, compare(data[i],data[j]));
            }
        }
        out.println(largest + 1);
        out.close();
    }
    private static int compare (String[] arr1, String[] arr2){
        int count = 0;
        String[] temp = new String[100];
        System.arraycopy(arr1,2,temp,0,100);
        String[] temp2 = new String[100];
        System.arraycopy(arr2,2,temp2,0,100);

        for (int i = 0; i < 100; i++){ //for every element in temp2
            for (int j = 0; j < 100; j++) { //if temp contains
                if (temp[j] == null){
                    break;
                }
                if (temp[j].equals(temp2[i])) {
                    count++;
                    break;
                }
            }
        }
        return count;
    }
}