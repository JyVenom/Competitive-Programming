import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class SleepyCowSorting5 {
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

        boolean isInOrder = true;
        for (int i = 0; i < cows; i++){
            if (currentOrder.get(i) - 1 != i) {
                isInOrder = false;
                break;
            }
        }

        if (!isInOrder){
            int temp = currentOrder.get(currentOrder.size() - 1) + 1;
            for(int i = cows - 1; i >= 0; i--){
                if (currentOrder.get(i) == temp - 1){
                    temp -= 1;
                }
                else if (currentOrder.get(i) != temp - 1){
                    out.println(i + 1);
                    break;
                }
            }
        }
        else {
            out.println(0);
        }
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