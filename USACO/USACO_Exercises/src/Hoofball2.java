import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Hoofball2 {
    private static int cows;
    private static int[] data;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("hoofball.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("hoofball.out")); //or what it calls for ("promote.out")

        cows = sc.nextInt();
        int[] numPassedFrom = new int[cows];
        data = new int[cows];
        for (int i = 0; i < cows; i++){
            data[i] = sc.nextInt();
        }
        for (int i = 0; i < cows; i++) {
            numPassedFrom[passTo(i)]++;
        }
        int answer = 0;
        for (int i = 0; i < cows; i++) {
            if (numPassedFrom[i] == 0){
                answer++;  // +1 for every "source" cow, to whom nobody passes
            }
            if (i < passTo(i) && passTo(passTo(i))==i && numPassedFrom[i]==1 && numPassedFrom[passTo(i)]==1) {
                answer++;  // +1 for every pair passing to each-other
            }
        }
        out.println(answer);
        out.close();
    }
    
    private static int passTo (int i){ //returns the cow that cow i will pass to
        int leftNumber=-1, leftDistance = 1000;
        int rightNumber=-1, rightDistance = 1000;

                                                                                                  
        for (int j = 0; j < cows; j++){ //find closest on the left
            if (data[j] < data[i] && data[i]-data[j] < leftDistance) { 
                leftNumber = j; 
                leftDistance = data[i]-data[j]; 
            }
        }
        for (int j = 0; j < cows; j++) {
            if (data[j] > data[i] && data[j] - data[i] < rightDistance) {
                rightNumber = j;
                rightDistance = data[j] - data[i];
            }
        }

        if (leftDistance <= rightDistance) {
            return leftNumber;
        }
        return rightNumber;
    }
}
