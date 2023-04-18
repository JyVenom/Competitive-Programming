import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Convention3 {
    private static int prevBuss = -1;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("convention.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("convention.out")); //or what it calls for ("promote.out)

        int cows = sc.nextInt();
        int buses = sc.nextInt();
        int size = sc.nextInt();
        int[][] times = new int[buses][size];

        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < cows; i++){
            data.add(sc.nextInt());
        }
        Collections.sort(data);

        int maxWait  = 0;
        for (int i = 1; i < data.size(); i++){
            maxWait = Math.max(maxWait, data.get(i) - data.get(i - 1));
        }

        ArrayList<Integer> tried = new ArrayList<>();
        int[] currentTimes = new int[2]; //min and max. More than or equal to min, less than or equal to max
        for (int gap = maxWait; gap > 1;){
            tried.add(gap);
            System.out.println(gap);
            currentTimes[0] = 1;
            currentTimes[1] = gap;
            int max = 0;
            prevBuss = -1;
            while (currentTimes[0] <= data.get(data.size() - 1)) {
                int start = prevBuss + 1;
                if (start == buses){
                    break;
                }
                boolean passed = false;
                for (int i = 0; i < cows; i++) {
                    if (data.get(i) >= currentTimes[0] && data.get(i) <= currentTimes[1]){
                        add(times, data.get(i), start);
                        max++;
                        passed = true;
                    }
                    else {
                        if (passed){
                            break;
                        }
                    }
                }
                currentTimes[0] += gap;
                currentTimes[1] += gap;
            }
            if (max < cows - 1){
                gap = next(tried, "Right");
            }
            else if (times[times.length - 1][0] == 0){
                gap = next(tried, "Left");
            }
            else if (max == cows - 1){
                int temp = 0;
                for (int i = 0; i < buses; i++){
                    for (int j = times[i].length - 1; j >= 0; j--){
                        if (times[i][j] != 0){
                            temp = Math.max(temp, times[i][j] - times[i][0]);
                            break;
                        }
                    }
                }
                out.println(temp);
                break;
            }
            times = new int[buses][size];
        }
        out.close();
    }

    private static void add (int[][] in, int elem, int start){
        for (int i = start; i < in.length; i++){
            for (int j = 0; j < in[0].length; j++){
                if (in[i][j] == 0){
                    in[i][j] = elem;
                    prevBuss = i;
                    i = in.length - 1;
                    break;
                }
            }
        }
    }

    private static int next (ArrayList<Integer> prev, String direction){
        int start = prev.get(prev.size() - 1);
        if (prev.size() == 1){
            return (int) (start * 1.5);
        }
        else if (direction.equals("Left")){
            int max = 0;
            for (Integer integer : prev) {
                if (integer < start) {
                    max = Math.max(max, integer);
                }
            }
            return (max + start) / 2;
        }
        else {
            int min = Integer.MAX_VALUE;
            for (Integer integer : prev) {
                if (integer > start) {
                    min = Math.min(min, integer);
                }
            }
            if (min == Integer.MAX_VALUE){
                return start * 2;
            }
            return (min + start) / 2;
        }
    }
}
