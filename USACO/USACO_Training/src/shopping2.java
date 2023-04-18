/*
ID: jerryya2
LANG: JAVA
TASK: shopping
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class shopping2 {
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

        int min = 0;
        if (s > 0) {
            min = solve(amt, deals, new int[1000], 0, s, prices, Integer.MAX_VALUE, 0, tng);
        }
        else {
            for (int tmp : tng) {
                min += amt[tmp] * prices[tmp];
            }
        }

        pw.println(min);
        pw.close();
    }

    private static int solve(int[] amt, ArrayList<Deal> deals, int[] cur, int on, int s, int[] prices, int min, int cost, ArrayList<Integer> tng) {
        int[] copy = cur.clone();
        int costCopy = cost;
        while (!over(amt, copy, tng)) {
            if (on < s  - 1) {
                min = solve(amt, deals, copy, on + 1, s, prices, min, costCopy, tng);
            }
            else {
                int costFinal = costCopy;
                for (int tmp : tng) {
                    if (amt[tmp] - copy[tmp] > 0) {
                        costFinal += (amt[tmp] - copy[tmp]) * prices[tmp];
                    }
                }

                if (costFinal < min) {
                    min = costFinal;
                }
            }

            Deal d = deals.get(on);
            ArrayList<int[]> items = d.getItems();
            for (int[] item : items) {
                copy[item[0]] += item[1];
            }
            costCopy += d.getPrice();
        }

        return min;
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
        
        private void setPrice(int price) {
            p = price;
        }
        
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
    }
}
