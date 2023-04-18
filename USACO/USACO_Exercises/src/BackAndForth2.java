import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BackAndForth2 {
    private static int[] possibleValues = new int[2000];
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("backforth.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("backforth.out")); //or what it calls for ("promote.out")

        ArrayList<Integer> barn1 = new ArrayList<>(10);
        ArrayList<Integer> barn2 = new ArrayList<>(10);
        int next;
        for (int i = 0; i < 10; i++){
            next = sc.nextInt();
            barn1.add(next);
        }
        for (int i = 0; i < 10; i++){
            next = sc.nextInt();
            barn2.add(next);
        }

        tuesday(barn1,barn2);

        int answer = 0;
        for (int i=0; i<2000; i++) {
            answer += possibleValues[i];
        }

        out.println(answer);
        out.close();
    }

    private static void tuesday(ArrayList<Integer> arr1, ArrayList<Integer> arr2){
        for (int i = 0; i < 10; i++){
            ArrayList<Integer> temp1 = arr1;
            ArrayList<Integer> temp2 = arr2;
            int temp = arr1.get(i);
            arr1 = new ArrayList<>(10);
            for (int j = 0; j < 10; j++){
                if (j != i){
                    arr1.add(temp1.get(j));
                }
            }
            arr2.add(temp);
            wednesday(1000 - temp,arr1,arr2);
            arr1 = temp1;
            arr2 = temp2;
        }
    }
    private static void wednesday(int milk, ArrayList<Integer> arr1, ArrayList<Integer> arr2){
        for (int i = 0; i < 10; i++){
            ArrayList<Integer> temp1 = arr1;
            ArrayList<Integer> temp2 = arr2;
            int temp = arr2.get(i);
            arr2 = new ArrayList<>(10);
            for (int j = 0; j < 10; j++){
                if (j != i){
                    arr2.add(temp2.get(j));
                }
            }
            arr1.add(temp);
            thursday(milk + temp,arr1,arr2);
            arr1 = temp1;
            arr2 = temp2;
        }
    }
    private static void thursday(int milk, ArrayList<Integer> arr1, ArrayList<Integer> arr2){
        for (int i = 0; i < 10; i++){
            ArrayList<Integer> temp1 = arr1;
            ArrayList<Integer> temp2 = arr2;
            int temp = arr1.get(i);
            arr1 = new ArrayList<>(10);
            for (int j = 0; j < 10; j++){
                if (j != i){
                    arr1.add(temp1.get(j));
                }
            }
            arr2.add(temp);
            friday(milk - temp,arr2);
            arr1 = temp1;
            arr2 = temp2;
        }
    }
    private static void friday(int milk, ArrayList<Integer> arr2){
        for (int i = 0; i < 10; i++){
            int temp = arr2.get(i);
            possibleValues[milk + temp - 1] = 1;
        }
    }
}
