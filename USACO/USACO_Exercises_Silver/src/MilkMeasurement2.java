import java.io.*;
import java.util.*;

public class MilkMeasurement2 {
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
        HashMap<Integer, Integer> rate = new HashMap<>();
        rate.put(0, 1000000000);
        int count = 0;
        for (change change : changes) {
            if (!dif.containsKey(change.cow)) {
                rate.replace(0, rate.get(0) - 1);
                dif.put(change.cow, change.dif);
                prev.add(dif.get(change.cow));

                if (dif.get(change.cow) > max) {
                    count++;
                    max = dif.get(change.cow);
                    prev.add(max);
                    if (!rate.containsKey(max)) {
                        rate.put(max, 1);
                    }
                    else {
                        rate.replace(max, 1);
                    }
                }
                else if (dif.get(change.cow) == max) {
                    count++;
                    rate.replace(max, rate.get(max) + 1);
                }
                else if (max == 0) {
                    count++;
                    if (!rate.containsKey(dif.get(change.cow))) {
                        rate.put(dif.get(change.cow), 1);
                    }
                    else {
                        rate.replace(dif.get(change.cow), rate.get(dif.get(change.cow)) + 1);
                    }
                    if (rate.get(0) == 0) {
                        prev.remove(0);
                        ArrayList<Integer> temp = new ArrayList<>(prev);
                        Collections.sort(temp);
                        max = temp.get(temp.size() - 1);
                    }
                }
                else {
                    if (!rate.containsKey(dif.get(change.cow))) {
                        rate.put(dif.get(change.cow), 1);
                    }
                    else {
                        rate.replace(dif.get(change.cow), dif.get(change.cow) + 1);
                    }
                }
            }
            else {
                rate.replace(dif.get(change.cow), rate.get(dif.get(change.cow)) - 1);
                if (dif.get(change.cow) == max) {
                    count++;
                    if (rate.get(dif.get(change.cow)) == 0) {
                        prev.remove(dif.get(change.cow));
                        dif.replace(change.cow, dif.get(change.cow) + change.dif);
                        prev.add(dif.get(change.cow));
                        ArrayList<Integer> temp = new ArrayList<>(prev);
                        Collections.sort(temp);
                        max = temp.get(temp.size() - 1);
                    }
                    else {
                        dif.replace(change.cow, dif.get(change.cow) + change.dif);
                        prev.add(dif.get(change.cow));
                    }
                    continue;
                }
                dif.replace(change.cow, dif.get(change.cow) + change.dif);
                prev.add(dif.get(change.cow));

                if (dif.get(change.cow) > max) {
                    count++;
                    max = dif.get(change.cow);
                    prev.add(max);
                    if (!rate.containsKey(max)) {
                        rate.put(max, 1);
                    }
                    else {
                        rate.replace(max, 1);
                    }
                }
                else if (dif.get(change.cow) == max) {
                    count++;
                    rate.replace(max, rate.get(max) + 1);
                }
                else {
                    if (!rate.containsKey(dif.get(change.cow))) {
                        rate.put(dif.get(change.cow), 1);
                    }
                    else {
                        rate.replace(dif.get(change.cow), dif.get(change.cow) + 1);
                    }
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



