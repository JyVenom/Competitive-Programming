import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WhyDidTheCowCrossTheRoad {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("crossroad.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("crossroad.out")); //or what it calls for ("promote.out")

        int observations = sc.nextInt();
        ArrayList<ArrayList<Integer>> data = new ArrayList<>(10); //outside ArrayList is for cow number, inside one for each cow are its location observations

        for (int i = 0; i < 10; i++){
            data.add(new ArrayList<>());
        }
        int cow;
        for (int i = 0; i < observations; i++){
            cow = sc.nextInt() - 1;
            data.get(cow).add(sc.nextInt());
        }

        int count = 0;
        int previousDifferent;
        for (int i = 0; i < 10; i++){
            if (data.get(i).size() > 0) {
                previousDifferent = data.get(i).get(0);
                for (int j = 0; j < data.get(i).size(); j++) {
                    if (data.get(i).get(j) == ((previousDifferent + 1) % 2)) {
                        count++;
                        previousDifferent = (previousDifferent + 1) % 2;
                    }
                }
            }
        }

        out.println(count);
        out.close(); //VERY IMPORTANT!!!
    }
}
