import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DiamondCollector {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("diamond.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("diamond.out")); //or what it calls for ("promote.out")
        int num = sc.nextInt();
        int dist = sc.nextInt();
        ArrayList<Integer> data = new ArrayList<>(num);
        ArrayList<Integer> sizes = new ArrayList<>();

        for (int i = 0; i < num; i++){
            int value = sc.nextInt();
            data.add(value);
            if (!sizes.contains(value)){
                sizes.add(value);
            }
        }
        Collections.sort(data);
        Collections.sort(sizes);

        int max = Integer.MIN_VALUE;
        for (int base : sizes) {
            int count = 0;
            int maxNum = base;
            for (int j = data.indexOf(base); j < data.size(); j++) {
                if (Math.abs(base - data.get(j)) <= dist) {
                    count++;
                    maxNum = data.get(j);
                } else {
                    break;
                }
            }
            for (int j = data.indexOf(base) - 1; j >= 0; j--) {
                if (Math.abs(maxNum - data.get(j)) <= dist && Math.abs(base - data.get(j)) <= dist) {
                    count++;
                } else {
                    break;
                }
            }
            max = Math.max(max, count);

            count = 0;
            int minNum = base;
            for (int j = data.indexOf(base) - 1; j >= 0; j--) {
                if (Math.abs(base - data.get(j)) <= dist) {
                    count++;
                    minNum = data.get(j);
                } else {
                    break;
                }
            }
            for (int j = data.indexOf(base); j < data.size(); j++) {
                if (Math.abs(minNum - data.get(j)) <= dist && Math.abs(base - data.get(j)) <= dist) {
                    count++;
                } else {
                    break;
                }
            }
            max = Math.max(max, count);
        }

        out.println(max);
        out.close();
    }
}
