import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class RentalService {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("rental.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("rental.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        cow[] cows = new cow[n];
        for (int i = 0; i < n; i++) {
            cows[i] = new cow(Integer.parseInt(br.readLine()), i);
        }
        int[][] stores = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            stores[i][0] = Integer.parseInt(st.nextToken());
            stores[i][1] = Integer.parseInt(st.nextToken());
        }
        int[] rent = new int[r];
        for (int i = 0; i < r; i++) {
            rent[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(cows, new Comparator<cow>() {
            @Override
            public int compare(cow o1, cow o2) {
                return o2.milk - o1.milk;
            }
        });
        Arrays.sort(stores, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        Arrays.sort(rent);
        int[] rented = new int[r];
        Arrays.fill(rented, -1);
        for (int i = r - 1, i1 = n - 1; i >= 0; i--, i1--) {
            rented[i] = i1;
        }
        long sum = 0;
        int start = 0;
        if (rented[0] > 0) {
            for (int i = 0; i < rented[0]; i++) {
                int rem = cows[i].milk;
                for (int j = start; j < m; j++) {
                    if (rem > stores[j][0]) {
                        sum += (long) stores[j][1] * (long) stores[j][0];
                        rem -= stores[j][0];
                        stores[j][0] = 0;
                        start++;
                    }
                    else {
                        sum += (long) stores[j][1] * (long) rem;
                        stores[j][0] -= rem;
                        if (stores[j][0] == 0) {
                            start++;
                        }
                        break;
                    }
                }
            }
        }
        for (int i = Math.max(0, r - n); i < r; i++) {
            int cur = rented[i];
            if (cur == -1) {
                continue;
            }

            long temp = 0;
            int rem = cows[cur].milk;
            for (int j = start; j < m; j++) {
                if (rem > stores[j][0]) {
                    temp += (long) stores[j][1] * (long) stores[j][0];
                    rem -= stores[j][0];
                    stores[j][0] = 0;
                    start++;
                }
                else {
                    temp += (long) stores[j][1] * (long) rem;
                    stores[j][0] -= rem;
                    if (stores[j][0] == 0) {
                        start++;
                    }
                    break;
                }
            }
            if (temp > rent[i]) {
                sum += temp;
            }
            else {
                for (int j = i; j < r; j++) {
                    sum += rent[j];
                }
                break;
            }
        }

        pw.println(sum);
        pw.close();
    }

    private static class cow {
        int milk, cow;

        private  cow (int milk, int cow) {
            this.milk = milk;
            this.cow =  cow;
        }
    }
}
