import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class CircularBarn {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter(new File("cbarn.out"));

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> rooms = new ArrayList<>();
        for (int i = 0; i < n; i++){
            rooms.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());
            for (int j = 0; j < num; j++) {
                rooms.get(i).add(0);
            }
        }
        br.close();

        for (int i = 0; i < n; i++){
            if (rooms.get(i).size() > 1){
                fix(rooms, i);
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++){
            int val = rooms.get(i).get(0);
            sum += val * val;
        }
        pw.println(sum);
        pw.close();
    }
    private static void fix (ArrayList<ArrayList<Integer>> arr, int room) {
        Collections.sort(arr.get(room));
        while (arr.get(room).size() > 1){
            arr.get((room + 1) % arr.size()).add(arr.get(room).get(0) + 1);
            arr.get(room).remove(0);
        }
        if (arr.get((room + 1) % arr.size()).size() > 1) {
            fix(arr, (room + 1) % arr.size());
        }
    }
}
