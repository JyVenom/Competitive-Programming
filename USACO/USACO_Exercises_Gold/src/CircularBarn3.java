import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class CircularBarn3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));

        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<cow>> at = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            ArrayList<cow> room = new ArrayList<>(num);
            for (int j = 0; j < num; j++) {
                room.add(new cow());
            }
            at.add(room);
        }

        for (int i = 0; i < n; i++) {
            if (at.get(i).size() > 1) {
                while (at.get(i).size() > 1) {
                    int cur = i;
                    while (at.get(cur).size() > 1) {
                        int next = (cur + 1) % n;
                        at.get(cur).get(0).walk();
                        at.get(next).add(at.get(cur).get(0));
                        at.get(next).sort(Comparator.comparingLong(o -> o.walked));
                        at.get(cur).remove(0);
                        cur = next;
                    }
                }
            }
        }
        long sum = 0;
        for (ArrayList<cow> room : at) {
            sum += room.get(0).walked * room.get(0).walked;
        }

        pw.println(sum);
        pw.close();
    }

    private static class cow {
        long walked;

        private cow() {
            walked = 0;
        }

        private void walk() {
            walked++;
        }
    }
}
