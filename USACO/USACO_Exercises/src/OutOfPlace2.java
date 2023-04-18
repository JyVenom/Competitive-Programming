import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class OutOfPlace2 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("outofplace.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("outofplace.out")); //or what it calls for ("promote.out")

        int cows = sc.nextInt();
        ArrayList<Integer> currentOrder = new ArrayList<>(cows);

        int position = 0;
        boolean needsCheck = false;
        for (int i = 0; i < cows; i++){
            int temp = sc.nextInt();
            if (needsCheck){
                if (temp >= currentOrder.get(i - 1)){
                    position = 0;
                }else {
                    position = 1;
                }
            }
            if (temp < currentOrder.get(i - 1)){
                if (i > 1){
                    position = i;
                }else {
                    needsCheck = true;
                }
            }
            currentOrder.add(temp);
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
            last = currentOrder.get(0);
            for (int i = 0; i < cows; i++) {
                if (currentOrder.get(i) >= last) {
                    last = currentOrder.get(i);
                } else {
                    if (i == cows - 1) {
                        position = i;
                        break;
                    } else if (currentOrder.size() == 3) {
                        if (currentOrder.get(0) <= currentOrder.get(2)) {
                            position = 1;
                        } else {
                            position = 0;
                        }
                        break;
                    } else if (i == 1) {
                        if (currentOrder.get(i + 1) >= currentOrder.get(i)) {
                            position = 0;
                        } else {
                            position = 1;
                        }
                        break;
                    } else {
                        position = i;
                        break;
                    }
                }
            }
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
