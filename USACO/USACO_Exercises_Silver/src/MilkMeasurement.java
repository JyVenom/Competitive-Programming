import java.io.*;
import java.util.*;

public class MilkMeasurement {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
//        int g = Integer.parseInt(st.nextToken());
        change[] changes = new change[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            changes[i] = new change(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(changes, Comparator.comparingInt(o -> o.day));
        int max = 0;
        HashSet<Integer> prev = new HashSet<>();
        prev.add(0);
        HashMap<Integer, Integer> dif = new HashMap<>();
//        HashMap<Integer, Integer> map = new HashMap<>();
//        map.put(0, 1000000000);
        int numMax = 1000000000;
        int curMax = 0;
        int count = 0;
        for (change change : changes) {
            if (!dif.containsKey(change.cow)) {
                dif.put(change.cow, change.dif);
            }
            else {
                if (dif.get(change.cow) == max){
                    count++;
                    numMax--;
                    if (numMax == 1 && change.cow == curMax) {
                        prev.remove(max);
                        prev.add(dif.get(change.cow));
                        ArrayList<Integer> temp = new ArrayList<>(prev);
                        Collections.sort(temp);
                        max = temp.get(temp.size() - 1);
//                if (!map.containsKey(dif.get(change.cow))) {
//                    map.put(dif.get(change.cow), 1);
//                }
//                else {
//                    map.replace(dif.get(change.cow), map.get(dif.get(change.cow)) + 1);
//                }
                    }
                }
                dif.replace(change.cow, dif.get(change.cow) + change.dif);
            }

            if (numMax == 1 && change.cow == curMax) {
                count++;
                prev.remove(max);
                prev.add(dif.get(change.cow));
                ArrayList<Integer> temp = new ArrayList<>(prev);
                Collections.sort(temp);
                max = temp.get(temp.size() - 1);
//                if (!map.containsKey(dif.get(change.cow))) {
//                    map.put(dif.get(change.cow), 1);
//                }
//                else {
//                    map.replace(dif.get(change.cow), map.get(dif.get(change.cow)) + 1);
//                }
            }
            else {
                if (dif.get(change.cow) > max) {
                    max = dif.get(change.cow);
                    curMax = change.cow;
                    numMax = 0;
                }
                if (dif.get(change.cow) == max) {
                    count++;
                    numMax++;
                    prev.add(max);
                }
            }
        }

        pw.println(count);
        pw.close();
    }

    private static class change{
        int day, cow, dif;

        private change(int day, int cow, int dif) {
            this.day = day;
            this.cow = cow;
            this.dif = dif;
        }
    }
}
