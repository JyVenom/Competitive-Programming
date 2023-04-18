/*
ID: jerryya2
LANG: JAVA
TASK: shopping
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class shopping4 {
    private static int[] used;
    private static double sum2 = 0;
    private static int count2 = 0;
    private static int count3 = 0;
    private static int count4 = 0;
    private static int count5 = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shopping.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));

        int s = Integer.parseInt(br.readLine());
        used = new int[s];
        ArrayList<Deal> deals = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            Deal deal = new Deal();
            for (int j = 0; j < n; j++) {
                int c = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                deal.addItem(c, k);
            }
            int p = Integer.parseInt(st.nextToken());
            deal.setPrice(p);
            deals.add(deal);
        }
        int b = Integer.parseInt(br.readLine());
        int[] prices = new int[1000];
        int[] amt = new int[1000];
        ArrayList<Integer> tng = new ArrayList<>();
        for (int i = 0; i < b; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            tng.add(c);
            amt[c] = k;
            prices[c] = p;
        }
        Collections.sort(tng);


        for (int i = 0; i < deals.size(); i++) {
            Deal d = deals.get(i);
            ArrayList<int[]> items = d.getItems();
            for (int[] item : items) {
                if (Collections.binarySearch(tng, item[0]) < 0 || item[1] > amt[item[0]]) {
                    deals.remove(i);
                    i--;
                    break;
                }
            }
        }
        for (Deal d : deals) {
            ArrayList<int[]> items = d.getItems();
            int cost = 0;
            for (int[] item : items) {
                cost += prices[item[0]] * item[1];
            }
            cost -= d.getPrice();
            d.setSave(cost);
        }
        deals.sort((o1, o2) -> (o2.getSave() - o1.getSave()));
        int min = 0;
        if (s > 0) {
            min = solve(amt, deals, new int[1000], 0, s, prices, Integer.MAX_VALUE, 0, tng);
        } else {
            for (int tmp : tng) {
                min += amt[tmp] * prices[tmp];
            }
        }

        System.out.println(sum2 / count2);
        System.out.println((double) count3 / count4);
        System.out.println(count5);
        pw.println(min);
        pw.close();
    }

    private static int solve(int[] amt, ArrayList<Deal> deals, int[] cur, int on, int s, int[] prices, int min, int cost, ArrayList<Integer> tng) {
        int[] copy = cur.clone();
        int costCopy = cost;
        while (under(amt, copy, tng)) {
            if (on < s - 1) {
                min = solve(amt, deals, copy, on + 1, s, prices, min, costCopy, tng);
                Deal d = deals.get(on);
                ArrayList<int[]> items = d.getItems();
                count5++;
                for (int[] item : items) {
                    count5++;
                    copy[item[0]] += item[1];
                }
                count5++;
                costCopy += d.getPrice();
                used[on]++;
            } else {
                Deal d = deals.get(on);
                ArrayList<int[]> items = d.getItems();
                count5++;
                while (under(amt, copy, tng)) {
                    count5++;
                    for (int[] item : items) {
                        count5++;
                        copy[item[0]] += item[1];
                    }
                    count5++;
                    costCopy += d.getPrice();
                    used[on]++;
                }
                count5++;
                for (int[] item : items) {
                    count5++;
                    copy[item[0]] -= item[1];
                }
                count5++;
                costCopy -= d.getPrice();
                used[on]--;

                count5++;
                int costFinal = costCopy;
                for (int tmp : tng) {
                    count5++;
                    if (amt[tmp] - copy[tmp] > 0) {
                        costFinal += (amt[tmp] - copy[tmp]) * prices[tmp];
                    }
                }

                int sum = 0;
                int count = 0;
                count5++;
                for (int i : used) {
                    count5++;
                    if (i > 0) {
                        sum += i;
                        count++;
                        count3++;
                    }
                }
                sum2 += (double) sum / count;
                count2++;
                count4++;

                count5++;
                if (costFinal < min) {
                    min = costFinal;
                }
                break;
            }
        }

        used[on] = 0;
        return min;
    }

    private static boolean under(int[] amt, int[] cur, ArrayList<Integer> tng) {
        for (int tmp : tng) {
            count5++;
            if (cur[tmp] > amt[tmp]) {
                return false;
            }
        }
        return true;
    }

    private static class Deal {
        ArrayList<int[]> items = new ArrayList<>();
        int p;
        int s;

        private void addItem(int code, int quantity) {
            int[] item = new int[2];
            item[0] = code;
            item[1] = quantity;
            items.add(item);
        }

        private ArrayList<int[]> getItems() {
            return items;
        }

        private int getPrice() {
            return p;
        }

        private void setPrice(int price) {
            p = price;
        }

        private int getSave() {
            return s;
        }

        private void setSave(int save) {
            s = save;
        }
    }
}
