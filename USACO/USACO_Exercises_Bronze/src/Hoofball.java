import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Hoofball {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("hoofball.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("hoofball.out")); //or what it calls for ("promote.out")

        int cows = sc.nextInt();
        int[] positions = new int[cows];
        ArrayList<Integer> covered = new ArrayList<>(cows);
        ArrayList<Integer> remaining = new ArrayList<>(cows);
        int balls = 0;
        for (int i = 0; i < cows; i++){
            positions[i] = sc.nextInt();
            remaining.add(positions[i]);
        }
        Arrays.sort(positions);
        Collections.sort(remaining);
        int biggestGap = 0;
        int currentPos = 0;
        for (int i = remaining.size() - 1; i > 0; i--) {
            if (remaining.get(i) - remaining.get(i - 1) > biggestGap) {
                biggestGap = remaining.get(i) - remaining.get(i - 1);
                currentPos = i;
            }
        }

        if (currentPos <= positions.length/2){
            currentPos -= 1;
        }
        balls++;
        covered.add(positions[currentPos]);
        remaining.remove((Integer) positions[currentPos]);
        if (currentPos == 0){
            currentPos = 1;
            covered.add(positions[1]);
            remaining.remove((Integer) positions[1]);
        }
        if (currentPos == positions.length - 1){
            currentPos -= 1;
            covered.add(positions[currentPos]);
            remaining.remove((Integer) positions[currentPos]);
        }
        while (covered.size() != positions.length){
            boolean done = false;
            while (true) {
                if (positions[currentPos + 1] - positions[currentPos] < positions[currentPos] - positions[currentPos - 1]) {
                    currentPos += 1;
                    if (covered.contains(positions[currentPos])) {
                        break;
                    }
                    covered.add(positions[currentPos]);
                    remaining.remove((Integer) positions[currentPos]);
                    if (remaining.size() == 0){
                        done = true;
                        break;
                    }
                } else {
                    currentPos -= 1;
                    if (covered.contains(positions[currentPos])) {
                        break;
                    }
                    covered.add(positions[currentPos]);
                    remaining.remove((Integer) positions[currentPos]);
                    if (remaining.size() == 0){
                        done = true;
                        break;
                    }
                }
            }
            if (done){
                break;
            }
            if (remaining.size() == 1){
                balls++;
                break;
            }
            else {
                biggestGap = 0;
                currentPos = 0;
                for (int i = remaining.size() - 1; i > 0; i--) {
                    if (remaining.get(i) - remaining.get(i - 1) > biggestGap) {
                        biggestGap = remaining.get(i) - remaining.get(i - 1);
                        currentPos = i;
                    }
                }
                balls++;
                covered.add(positions[currentPos]);
                remaining.remove((Integer) positions[currentPos]);
                if (remaining.size() == 1){
                    break;
                }
            }
        }
        out.println(balls);
        out.close();
    }
}
