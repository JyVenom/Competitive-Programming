import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Meetings {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("meetings.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Double>> cows = new ArrayList<>();
//        int[][] cows = new int[n][3];
        int total = 0;
        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            ArrayList<Double> cow = new ArrayList<>();
            cow.add(Double.parseDouble(st.nextToken())); //weight
            cow.add(0, Double.parseDouble(st.nextToken())); //location
            cow.add(Double.parseDouble(st.nextToken())); //direction
            cows.add(cow);
            total+= cows.get(i).get(1); //update total weight
        }
        cows.sort(Comparator.comparing(a -> a.get(0)));

        int numSwitches = 0;
        int weight = 0;
        while (weight < total / 2){
            for (int i = 0; i < cows.size(); i++){
                cows.get(i).set(0, cows.get(i).get(0) + cows.get(i).get(2) / 2);
                if (cows.get(i).get(0) == 0 || cows.get(i).get(0) == l){
                    weight += cows.get(i).get(1);
                    cows.remove(i);
                }
            }
            cows.sort(Comparator.comparing(a -> a.get(0)));
            for (int i = 0; i < cows.size() - 1; i++){
                if (cows.get(i).get(0).equals(cows.get(i + 1).get(0))){
                    cows.get(i).set(2, -cows.get(i).get(2));
                    cows.get(i + 1).set(2, -cows.get(i + 1).get(2));
                    numSwitches++;
                }
            }
        }

        pw.println(numSwitches);
        pw.close();
    }
}
