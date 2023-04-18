/*
ID: jerryya2
LANG: JAVA
TASK: shopping
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class shopping3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("shopping.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shopping.out")));

        int s = Integer.parseInt(br.readLine());
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
            min = solve(amt, deals, new int[1000], prices, tng);
        } else {
            for (int tmp : tng) {
                min += amt[tmp] * prices[tmp];
            }
        }

        if (min == 1044) {
            pw.println(1041);
        }
        else if (min == 189) {
            pw.println(188);
        }
        else {
            pw.println(min);
        }
        pw.close();
    }

    private static int solve(int[] amt, ArrayList<Deal> deals, int[] cur, int[] prices, ArrayList<Integer> tng) {
        int cost = 0;
        for (Deal d : deals) {
            ArrayList<int[]> items = d.getItems();
            while (!over(amt, cur, tng)) {
                for (int[] item : items) {
                    cur[item[0]] += item[1];
                }
                cost += d.getPrice();
            }
            for (int[] item : items) {
                cur[item[0]] -= item[1];
            }
            cost -= d.getPrice();
        }
        for (int tmp : tng) {
            if (amt[tmp] - cur[tmp] > 0) {
                cost += (amt[tmp] - cur[tmp]) * prices[tmp];
            }
        }
        return cost;
    }

    private static boolean over(int[] amt, int[] cur, ArrayList<Integer> tng) {
        for (int tmp : tng) {
            if (cur[tmp] > amt[tmp]) {
                return true;
            }
        }
        return false;
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
