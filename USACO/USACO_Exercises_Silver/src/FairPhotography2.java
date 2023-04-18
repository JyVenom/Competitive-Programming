import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;

public class FairPhotography2 {
    public static void main(String[] args) throws Exception {
        int num = 6;
//        String num = "fairphoto";
        BufferedReader br = new BufferedReader(new FileReader(num + ".in"));
        PrintWriter out = new PrintWriter(new File("fairphoto.out"));

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        int w = 0;
        int s = 0;

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            ArrayList<Integer> cow = new ArrayList<>();
            cow.add(Integer.parseInt(line[0]));
            cow.add((int) line[1].charAt(0));
//            "S" = 83
//            "W" = 87
            data.add(cow);
            if (line[1].equals("W")){
                w++;
            }
            else {
                s++;
            }
        }
        data.sort(Comparator.comparing(a -> a.get(0)));

        if (w == s ){
            out.println(data.get(data.size() - 1).get(0) - data.get(0).get(0));
        }
        else if (w > s){
            if (n % 2 == 0){
                out.println(data.get(data.size() - 1).get(0) - data.get(0).get(0));
            }
            else {
                int leftDist = data.get(1).get(0) - data.get(0).get(0);
                int rightDist = data.get(data.size() - 1).get(0) - data.get(data.size() - 2).get(0);
                if (leftDist < rightDist) {
                    out.println(data.get(data.size() - 1).get(0) - data.get(1).get(0));
                }
                else {
                    out.println(data.get(data.size() - 2).get(0) - data.get(0).get(0));
                }
            }
        }
        else {
            while (w < s){
                int leftDist = data.get(1).get(0) - data.get(0).get(0);
                int rightDist = data.get(data.size() - 1).get(0) - data.get(data.size() - 2).get(0);
                if (leftDist < rightDist){
                    if (data.get(0).get(1) == 83){
                        data.remove(0);
                        s--;
                    }
                    else if (data.get(data.size() - 1).get(1) == 83){
                        data.remove(data.size() - 1);
                        s--;
                    }
                    else {
                        int dist1 = findDist(data, 0);
                        int dist2 = findDist(data, 1);
                        if (dist1 < dist2) {
                            data.remove(0);
                        }
                        else {
                            data.remove(data.size() - 1);
                        }
                        w--;
                    }
                }
                else {
                    if (data.get(data.size() - 1).get(1) == 83){
                        data.remove(data.size() - 1);
                        s--;
                    }
                    else if (data.get(0).get(1) == 83){
                        data.remove(0);
                        s--;
                    }
                    else {
                        int dist1 = findDist(data, 0);
                        int dist2 = findDist(data, 1);
                        if (dist1 < dist2) {
                            data.remove(0);
                        }
                        else {
                            data.remove(data.size() - 1);
                        }
                        w--;
                    }
                }
            }
            out.println(data.get(data.size() - 1).get(0) - data.get(0).get(0));
        }
        out.close();

        BufferedReader ans = new BufferedReader(new FileReader(num + ".out"));
        BufferedReader my = new BufferedReader(new FileReader("fairphoto.out"));
        int temp1 = Integer.parseInt(ans.readLine());
        int temp2 = Integer.parseInt(my.readLine());
        if (temp1 == temp2){
            System.out.println("same");
        }
        else {
            System.out.println("diff, larger = " + (temp2 > temp1));
            System.out.println(temp1);
            System.out.println(temp2);
        }
    }

    private static int findDist (ArrayList<ArrayList<Integer>> data, int type) { //0 for from start, 1 for from end
        int count = 0;
        if (type == 0){
            for (int i = 0; i < data.size(); i++){
                if (data.get(i).get(1) == 87){
                    count--;
                }
                else if (data.get(i).get(1) == 83){
                    count++;
                }
                if (count == 1){
                    return data.get(i).get(0) - data.get(0).get(0);
                }
            }
        }
        else {
            for (int i = data.size() - 1; i >= 0; i--){
                if (data.get(i).get(1) == 87){
                    count--;
                }
                else if (data.get(i).get(1) == 83){
                    count++;
                }
                if (count == 1){
                    return data.get(data.size() - 1).get(0) - data.get(i).get(0);
                }
            }
        }
        return count;
    }
}
