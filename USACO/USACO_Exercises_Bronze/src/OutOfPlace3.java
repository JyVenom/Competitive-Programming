import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class OutOfPlace3 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("outofplace.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("outofplace.out")); //or what it calls for ("promote.out")

        int cows = sc.nextInt();
        ArrayList<Integer> currentOrder = new ArrayList<>(cows);

        int position = 0;
        boolean needsCheck = false;
        currentOrder.add(sc.nextInt());
        for (int i = 1; i < cows; i++){
            int temp = sc.nextInt();
            if (needsCheck){
                if (temp >= currentOrder.get(i - 1)){
                    position = i - 2;
                }else {
                    position = i - 1;
                }
                needsCheck = false;
            }
            else if (temp < currentOrder.get(i - 1)){
                needsCheck = true;
            }
            currentOrder.add(temp);
        }
        if (needsCheck){
            position = currentOrder.size() - 1;
        }

        boolean inOrder = true;
        int last = currentOrder.get(0);
        for (int i = 0; i < cows; i++){
            if (currentOrder.get(i) < last){
                inOrder = false;
                break;
            }
            last = currentOrder.get(i);
        }
        if (inOrder){
            out.println(0);
        }
        else {
            int count = 0;
            while (!inOrder){
                if (position == 0 || (position < currentOrder.size() - 1 && currentOrder.get(position) > currentOrder.get(position + 1))){ // go right
                    if (position < currentOrder.size() - 2){
                        if (currentOrder.get(position + 2).equals(currentOrder.get(position + 1))){
                            int temp = 0;
                            for (int i = position + 2; i < cows; i++){
                                if (currentOrder.get(i).equals(currentOrder.get(position + 1))){
                                    temp = i;
                                }else {
                                    break;
                                }
                            }
                            switchCows(currentOrder, position, temp);
                            position = temp;
                        }else {
                            switchCows(currentOrder, position, position + 1);
                            position++;
                        }
                    }else {
                        switchCows(currentOrder, position, position + 1);
                        position++;
                    }
                }else { //go left
                    if (position > 1){
                        if (currentOrder.get(position - 2).equals(currentOrder.get(position - 1))){
                            int temp = 0;
                            for (int i = position - 2; i >= 0; i--){
                                if (currentOrder.get(i).equals(currentOrder.get(position - 1))){
                                    temp = i;
                                }else {
                                    break;
                                }
                            }
                            switchCows(currentOrder, position, temp);
                            position = temp;
                        }else {
                            switchCows(currentOrder, position, position - 1);
                            position--;
                        }
                    }else {
                        switchCows(currentOrder, position, position - 1);
                        position--;
                    }
                }
                count++;
                inOrder = true;
                last = currentOrder.get(0);
                for (int i = 0; i < cows; i++){
                    if (currentOrder.get(i) < last){
                        inOrder = false;
                        break;
                    }
                    last = currentOrder.get(i);
                }
            }
            out.println(count);
        }
        out.close();
    }
    
    private static void switchCows (ArrayList<Integer> list, int a, int b){
        int temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }
}
