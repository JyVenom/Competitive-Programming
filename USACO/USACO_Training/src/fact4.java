/*
ID: jerryya2
LANG: JAVA
TASK: fact4
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class fact4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fact4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));

        int n = Integer.parseInt(br.readLine());

        ArrayList<int[]> primes = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            int num = i;
            int prime = 2;
            while (num > 1) {
                if (num % prime == 0) {
                    int ind = binarySearch(primes, 0, primes.size() - 1, prime);
                    if (ind < 0) {
                        int[] newPrime = new int[2];
                        newPrime[0] = prime;
                        newPrime[1] = 1;
                        primes.add(newPrime);
                        primes.sort(Comparator.comparingInt(a -> a[0]));
                    } else {
                        primes.get(ind)[1]++;
                    }

                    num = num / prime;
                } else if (prime == 2) {
                    prime++;
                } else {
                    prime += 2;
                }
            }
        }
        int pSize = primes.size();
        int ind = binarySearch(primes, 0, pSize - 1, 5);
        if (ind != -1) {
            int val = primes.get(ind)[1];
            primes.remove(ind);
            ind = binarySearch(primes, 0, pSize - 1, 2);
            primes.get(ind)[1] -= val;
        }
        int prod = 1;
        for (int[] ints : primes) {
            int prime = ints[0];
            int amt = ints[1];
            for (int j = 0; j < amt; j++) {
                prod = (prod * prime) % 10;
            }
        }

        pw.println(prod);
        pw.close();
    }

    private static int binarySearch(ArrayList<int[]> arr, int first, int last, int key) {
        if (last >= first) {
            int mid = first + (last - first) / 2;
            if (arr.get(mid)[0] == key) {
                return mid;
            }
            if (arr.get(mid)[0] > key) {
                return binarySearch(arr, first, mid - 1, key);
            } else {
                return binarySearch(arr, mid + 1, last, key);
            }
        }
        return -1;
    }
}
