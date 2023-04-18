import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MilkingOrder {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("milkorder.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("milkorder.out")); //or what it calls for ("promote.out")

        int[] numCows = new int[3]; //the first line. Contains 3 ints representing N, M, and K
        numCows[0] = sc.nextInt(); //N
        numCows[1] = sc.nextInt(); //M
        numCows[2] = sc.nextInt(); //K
        int[] orderedCows = new int[numCows[1]]; //order of cows who insist on going in a specific order
        int[][] specificCows = new int[numCows[2]][2]; //cows who insist on going on a specific place and what place that is
        ArrayList<Integer> arrangement = new ArrayList<>(numCows[0]); //arrangement/order of cows currently

        for (int i = 0; i < numCows[1]; i++){ //fills in orderedCows with given data
            orderedCows[i] = sc.nextInt();
        }
        for (int i = 0; i < numCows[2]; i++){ //fills in specificCows with given data
            specificCows[i][0] = sc.nextInt();
            specificCows[i][1] = sc.nextInt();
        }
        for (int i = 0; i < numCows[0]; i++){ //fills in arrangement with 0s so can set elements with a variable (otherwise size = 0 and all is null)
            arrangement.add(0);
        }
        for (int[] specificCow : specificCows) { //gives specific cows a spot ("reserves" a spot in arrangement)
            arrangement.set(specificCow[1] - 1, specificCow[0]);
        }
        boolean contains = false;
        for (int i = 0; i < orderedCows.length; i++){
            if (orderedCows[i] == 1){
                contains = true;
                break;
            }
        }
        if (!contains) {
            if (!arrangement.contains(orderedCows[orderedCows.length - 1])) {
                for (int i = arrangement.size() - 1; i >= 0; i--) { //puts in last element in orderCows into arrangement so below loop will be initialized/set to go/work
                    if (arrangement.get(i) == 0) {
                        arrangement.set(i, orderedCows[orderedCows.length - 1]);
                        break;
                    }
                }
            }
            for (int i = orderedCows.length - 2; i >= 0; i--) { //-2 because 1. last cow is already accounted for above 2. otherwise will throw index out of bounds
                if (arrangement.indexOf(orderedCows[i]) == -1) {
                    for (int j = arrangement.indexOf(orderedCows[i + 1]) - 1; j >= 0; j--) {
                        if (arrangement.get(j) == 0) {
                            arrangement.set(j, orderedCows[i]);
                            break;
                        }
                    }
                }
            }
        }else {
            if (!arrangement.contains(orderedCows[0])) {
                for (int i = 0; i < arrangement.size(); i++) { //puts in first element in orderCows into arrangement so below loop will be initialized/set to go/work
                    if (arrangement.get(i) == 0) {
                        arrangement.set(i, orderedCows[0]);
                        break;
                    }
                }
            }
            for (int i = 1; i < orderedCows.length; i++) { //1 because first cow is already accounted for above
                if (arrangement.indexOf(orderedCows[i]) == -1) { //not yet set (if they chose specific order)
                    for (int j = arrangement.indexOf(orderedCows[i - 1]) + 1; j < arrangement.size(); j++) { //look at every spot after the previous one (because must be in specified order) for an empty one
                        if (arrangement.get(j) == 0) {
                            arrangement.set(j, orderedCows[i]);
                            break;
                        }
                    }
                }
            }
        }

        if (arrangement.contains(1)){
            out.println(arrangement.indexOf(1) + 1);
        }
        else {
            out.println(arrangement.indexOf(0) + 1);
        }
        out.close();
    }
}

/*
go through specificCows and put them in correct spot
then go through orderedCows backwards and fill in as far back as possible:
    do the last one separately,
    loop through the ones before,
        if already placed, go to next one,
        otherwise, place on last empty spot before the cow that goes after
output the index of 0
 */