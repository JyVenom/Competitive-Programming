import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CowGymnastics {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("gymnastics.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("gymnastics.out")); //or what it calls for ("promote.out)

        int k = sc.nextInt();
        int n = sc.nextInt();
        ArrayList<ArrayList<Integer>> data = new ArrayList<>(k);
        for (int i = 0; i < k; i++){
            data.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++){
            for (int j = 0; j < n; j++){
                data.get(i).add(sc.nextInt());
            }
        }

        int count = 0;
        for (int c1 = 1; c1 <= n; c1++){
            for (int c2 = c1 + 1; c2 <= n; c2++){
                boolean good = true;
                if (data.get(0).indexOf(c1) > data.get(0).indexOf(c2)){ //if c1 preforms worse than c2
                    for (int i = 0; i < k; i++){
                        if (data.get(i).indexOf(c2) > data.get(i).indexOf(c1)){ //if c2 ever preforms worse than c1
                            good = false;
                            break;
                        }
                    }
                }
                else { //if c2 preforms worse than c1
                    for (int i = 0; i < k; i++){
                        if (data.get(i).indexOf(c1) > data.get(i).indexOf(c2)){ //if c1 ever preforms worse than c2
                            good = false;
                            break;
                        }
                    }
                }
                if (good){
                    count++;
                }
            }
        }

        out.println(count);
        out.close();
    }
}
