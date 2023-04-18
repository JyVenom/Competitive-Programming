import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TamingTheHerd3 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("taming.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("taming.out")); //or what it calls for ("promote.out")

        int days = sc.nextInt(); //number of days tracked by FJ
        ArrayList<Integer> data = new ArrayList<>(days); //an ArrayList containing the log entries of each day recorded by FJ

        for (int i = 0; i < days; i++){ //filling data with given information
            data.add(sc.nextInt());
        }
        data.set(0,0); //we are given this information (the first morning there was a breakout aka. 0). This step is just a precaution in case in the given information it is -1 (unknown/unrecorded)

        boolean notHaveError = true;
        for (int i = data.size() - 1; i >= 0; i--){ //try to find the location of the last known entry/value
            if (data.get(i) > 0) {
                int currentValue = data.get(i);
                boolean needToBreak = false;
                for (int j = i - 1; j >= 0; j--){ //to fill in as many as possible wile still staying "correct" (only going up to 0 as we are going backwards, filling in missing values)
                    currentValue--;
                    if (data.get(j) == -1 || data.get(i) == currentValue) {
                        data.set(j, currentValue);
                    }else {
                        needToBreak = true;
                        notHaveError = false;
                        out.println(-1);
                        break;
                    }
                    if (currentValue == 0){
                        break;
                    }
                }
                if (needToBreak){
                    break;
                }
            }
        }

        if (notHaveError) {
            boolean good = true;
            int current = 0;
            for (int i = 0; i < data.size(); i++) {
                if (data.get(i) == 0 || data.get(i) == -1) {
                    current = data.get(i);
                }
                if (data.get(i) != current) {
                    good = false;
                    break;
                }
                current++;
            }
            if (good) {
                int numZeros = 0;
                int numMissing = 0;
                for (Integer datum : data) {
                    if (datum == 0) {
                        numZeros++;
                    } else if (datum == -1) {
                        numMissing++;
                    }
                }
                out.println(numZeros + " " + (numZeros + numMissing));
            } else {
                out.println(-1);
            }
        }
        out.close(); //VERY IMPORTANT!!!
    }
}
