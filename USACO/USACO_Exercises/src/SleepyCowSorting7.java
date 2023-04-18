import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SleepyCowSorting7 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("sleepy.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("sleepy.out")); //or what it calls for ("promote.out")
        int cows = Integer.parseInt(sc.nextLine());
        String line = sc.nextLine();
        int prevIndex = 0;
        ArrayList<Integer> currentOrder = new ArrayList<>(cows);
        for (int i = 0; i < cows - 1; i++){
            currentOrder.add(Integer.parseInt(line.substring(prevIndex, line.indexOf(' ', prevIndex))));
            prevIndex = line.indexOf(' ', prevIndex) + 1;
        }
        currentOrder.add(Integer.parseInt(line.substring(prevIndex)));

        int ans = currentOrder.size() - 1;
        for(int i = currentOrder.size() - 2; i >= 0; i--){
            if (currentOrder.get(i) < currentOrder.get(i + 1)){
                ans = i;
            }
            else {
                break;
            }
        }
        out.println(ans);
        out.close();
    }
}

/*
get current order of cows
if not already in order:
    if last cow is first in line,
        move last cow to end of line
    else:
        if first cow is first in line:

if already in order,
 */