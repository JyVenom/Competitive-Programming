import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ConventionII {
    private static ArrayList<Integer> done = new ArrayList<>();
    private static int[] arrival;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("convention2.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("convention2.out")); //or what it calls for ("promote.out)

        int cows = sc.nextInt();
        arrival = new int[cows];
        int[] times = new int[cows];
        int[][] actual = new int[cows][2];

        for (int i = 0; i < cows; i++){
            arrival[i] = sc.nextInt();
            times[i] = sc.nextInt();
        }

        int first = 0;
        for (int i = 0; i < arrival.length; i++){
            if (arrival[i] < arrival[first]){
                first = i;
            }
        }
        done.add(first);
        actual[0][0] = arrival[first];
        actual[0][1] = actual[0][0] + times[first];

        int maxWait = 0;
        for (int i = 1; i < cows; i++){
            int next = findSmallest(actual[i - 1]);
            if (arrival[next] < actual[i - 1][1]){
                actual[i][0] = actual[i - 1][1];
                maxWait = Math.max(maxWait, actual[i][0] - arrival[next]);
            }
            else {
                actual[i][0] = arrival[next];
            }
            actual[i][1] = actual[i][0] + times[next];
        }

        out.println(maxWait);
        out.close();
    }

    private static int findSmallest (int[] data){ //returns index of cow who first comes after start times. If multiple, returns the one with highest seniority
        int end = data[1];
        ArrayList<Integer> possible = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arrival.length; i++){
            if (done.contains(i)){
                continue;
            }
            min = i;
            if (arrival[i] < end){
                possible.add(i);
            }
        }
        if (possible.size() > 0){
            min = possible.get(0);
        }
        done.add(min);
        return min;
    }
}
