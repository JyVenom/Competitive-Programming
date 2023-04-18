import java.util.HashSet;

public class test {
    public static void main(String[] args) {
        int n = 50;
        int len = 2;
        int reps = 100000;

        long sum = 0;
        for (int r = 0; r < reps; r++) {
            int cnt = 0;
            HashSet<Integer> set = new HashSet<>();
            while (set.size() < n) {
                int tmp = (int) (Math.random() * Math.pow(10, len));
                while (tmp < Math.pow(10, len - 1)) {
                    tmp = (int) (Math.random() * Math.pow(10, len));
                }
                set.add(tmp);
            }

            HashSet<Integer> set2 = new HashSet<>();
            for (int val : set) {
                int hc = hash(val);

                if (set2.contains(hc)) {
                    cnt++;
                } else {
                    set2.add(hc);
                }
            }

            sum += cnt;
        }
        sum /= reps;

        System.out.println(sum);
    }

    private static int hash(int val) {
        return val % 67;
    }
}
