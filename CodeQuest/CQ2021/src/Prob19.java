import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Prob19 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int o = Integer.parseInt(st.nextToken());
            ArrayList<helper> all = new ArrayList<>(p + o);
            while (p-- > 0) {
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                all.add(new helper(Integer.parseInt(s.substring(0, 4)), Integer.parseInt(s.substring(5, 7)), Integer.parseInt(s.substring(8)), Integer.parseInt(st.nextToken()), false));
            }
            while (o-- > 0) {
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                all.add(new helper(Integer.parseInt(s.substring(0, 4)), Integer.parseInt(s.substring(5, 7)), Integer.parseInt(s.substring(8)), Integer.parseInt(st.nextToken()), true));
            }
            all.sort((o1, o2) -> {
                if (o1.y < o2.y) {
                    return -1;
                }
                if (o1.y == o2.y) {
                    if (o1.m < o2.m) {
                        return -1;
                    }
                    if (o1.m == o2.m) {
                        if (o1.d < o2.d) {
                            return -1;
                        }
                        if (o1.d == o2.d) {
                            if (o1.buy && !o2.buy) {
                                return -1;
                            } else if (!o1.buy && o2.buy) {
                                return 1;
                            } else {
                                return 0;
                            }
                        } else {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            });

            ArrayList<helper> all2 = new ArrayList<>();
            boolean good = true;
            loop:
            for (helper helper : all) {
                if (helper.buy) {
                    if (all2.size() == 0 || exp(all2.get(0).y, all2.get(0).m, all2.get(0).d, helper.y, helper.m, helper.d)) {
                        good = false;
                        break;
                    }
                    int tmp = helper.num;
                    while (tmp > 0) {
                        if (all2.size() == 0) {
                            good = false;
                            break loop;
                        }
                        if (all2.get(0).num > tmp) {
                            all2.get(0).num -= tmp;
                            tmp = 0;
                        } else {
                            tmp -= all2.get(0).num;
                            all2.remove(0);
                        }
                    }
                } else {
                    all2.add(new helper(helper.y, helper.m, helper.d, helper.num, false));
                }
            }
            if (all2.size() > 0) {
                good = false;
            }
            pw.println(good ? "OK" : "NOT OK");
        }

        pw.close();
    }

    private static boolean exp(int y, int m, int d, int y2, int m2, int d2) {
        return ChronoUnit.DAYS.between(LocalDate.of(y, m, d), LocalDate.of(y2, m2, d2)) > 28L;
    }

    private static class helper {
        int y, m, d, num;
        boolean buy;

        public helper(int y, int m, int d, int num, boolean buy) {
            this.y = y;
            this.m = m;
            this.d = d;
            this.num = num;
            this.buy = buy;
        }
    }
}
