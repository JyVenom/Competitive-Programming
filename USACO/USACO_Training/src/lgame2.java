/*
ID: jerryya2
LANG: JAVA
TASK: lgame
*/

//n + mo + (2^n)*log(m)
//3 <= n <= 7
//3 <= o <= 7
//m <= 40,000
//7+280,000+1957=281,964

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class lgame2 {
    private static final int[] values = new int[]{2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lgame.in"));
        BufferedReader dictBr = new BufferedReader(new FileReader("lgame.dict"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lgame.out")));

        String[] letters = br.readLine().split("");
        Arrays.sort(letters);
        ArrayList<String> letters2 = new ArrayList<>(Arrays.asList(letters));
        String line = dictBr.readLine();
        ArrayList<word> dict = new ArrayList<>();
//        int place = 0;
        while (!line.equals(".")) {
//            dict.add(new word(line, place));
            word word = new word(line);
            if (isPos(letters2, word.letters)) {
                dict.add(word);
            }
//            place++;
            line = dictBr.readLine();
        }

        dict.sort(Comparator.comparingInt(o -> o.value));
        ArrayList<word> dict2 = new ArrayList<>(dict);
        for (word w : dict2) {
            Arrays.sort(w.letters);
        }
        dict2.sort((o1, o2) -> {
            int o1length = o1.length;
            int o2length = o2.length;
            for (int i = 0; i < o1length; i++) {
                int diff = o1.letters[i].charAt(0) - o2.letters[i].charAt(0);
                if (diff != 0) {
                    return diff;
                }
            }
            return o1length -  o2length;
        });


        pw.close();
    }

    private static void findAns(ArrayList<pair> ans, ArrayList<String> temp, String[] letters, int end, int at) {
        if (at < end) {
            findAns(ans, temp, letters, end, at + 1);
            temp.add(letters[at]);
            findAns(ans, temp, letters, end, at + 1);
            temp.remove(temp.size() - 1);
        }
        else {
            if (temp.size() > 0) {
                temp.add(letters[at]);

            }
            else {

                temp.add(letters[at]);

            }
        }
    }

    private static boolean isPos(ArrayList<String> letters, String[] word) {
        ArrayList<String> letters2 = new ArrayList<>(letters);
        for (String letter : word) {
            int place = Collections.binarySearch(letters2, letter);
            if (place >= 0) {
                letters2.remove(place);
            }
            else {
                return false;
            }
        }
        return true;
    }

    private static int calcVal(String[] word) {
        int sum = 0;
        for (String letter : word) {
            sum += values[letter.charAt(0) - 'a'];
        }
        return sum;
    }

    private static class pair {
        word w1;
        word w2;

        private pair (word word) {
            w1 = word;
            w2 = null;
        }

        private pair (word word1, word word2) {
            w1 = word1;
            w2 = word2;
        }
    }

    private static class word {
        String[] letters;
        String word;
        int length;
//        int place;
        int value;

//        private word(String word, int place) {
        private word(String word) {
            this.word = word;
            letters = word.split("");
            length = word.length();
//            this.place = place;
            value = calcVal(letters);
        }
    }
}
