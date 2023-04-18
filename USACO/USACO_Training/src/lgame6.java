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
import java.util.HashMap;
import java.util.HashSet;

public class lgame6 {
    private static final int[] letters = new int[256];
    private static final int[] scores = new int[256];
    private static final HashMap<Key, Word> wordMap = new HashMap<>();
    private static final HashSet<String> max_set = new HashSet<>();
    private static final ArrayList<String> word_list = new ArrayList<>();
    private static byte[] dict_bytes;
    private static byte[] sort_bytes;
    private static byte[] inputs;
    private static int min_word_size = Integer.MAX_VALUE;
    private static int max_score = 0;
    private static Key[] keys;
    private static boolean[] mark;

    private static void walk_word(int start, int word_index) {
        Key key = keys[word_index];
        for (; start < inputs.length; start++) {
            if (!mark[start]) {
                break;
            }
        }
        if (start >= inputs.length) {
            walk_words(key.input_off + 1, word_index + 1);
            return;
        }
        if (key.length() >= min_word_size) {
            walk_words(key.input_off + 1, word_index + 1);
        }
        for (int i = start; i < inputs.length; i++) {
            if (!mark[i]) {
                mark[i] = true;
                key.add(inputs[i]);
                walk_word(i + 1, word_index);
                key.end--;
                mark[i] = false;
            }
        }
    }

    private static int get_scores(int words) {
        int score = 0;
        for (int i = 0; i < words; i++) {
            score += get_score(keys[i]);
        }
        return score;
    }

    private static void walk_words(int start, int word_index) {
        Key key = keys[word_index];
        for (; start < inputs.length; start++) {
            if (!mark[start]) {
                break;
            }
        }
        if (start >= inputs.length + 1 - min_word_size) {
            int score = get_scores(word_index);
            //dump_keys(word_index,score);   
            if (score >= max_score) {
                if (score > max_score) {
                    max_score = score;
                    max_set.clear();
                }
                if (score != 0) {
                    word_list.clear();
                    build_result(word_list, 0, word_index + 1);
                }
            }
            return;
        }
        key.reset(start);
        walk_word(start, word_index);
    }

    private static int get_score(Key k) {
        k.refreshKey();
        Word w = wordMap.get(k);
        if (w == null)
            return 0;
        return w.score;
    }

    private static void build_result(ArrayList<String> list, int index, int words) {
        if (index >= words) {
            if (list.size() == 0) {
                return;
            }
            Object[] ar = list.toArray();
            Arrays.sort(ar);
            StringBuilder sb = new StringBuilder();
            sb.append(ar[0]);
            for (int i = 1; i < ar.length; i++) {
                sb.append(" ");
                sb.append(ar[i]);
            }
            max_set.add(sb.toString());
            return;
        }
        Word word = wordMap.get(keys[index]);
        if (word == null) {
            build_result(list, index + 1, words);
        }
        else {
            for (int i = 0; i < word.words.size(); i++) {
                String text = word.words.get(i).toString();
                list.add(text);
                build_result(list, index + 1, words);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lgame.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lgame.out")));

        String score_string = "q7w6e1r2t2y5u4i1o3p5a2s1d4f6g5h5j7k6l3z7x7c4v6b5n2m5";
        for (int i = 0; i < score_string.length(); i += 2) {
            char c = score_string.charAt(i);
            char c1 = score_string.charAt(i + 1);
            scores[c] = c1 - '0';
        }
        String line = br.readLine();
        inputs = line.getBytes();
        Arrays.sort(inputs);
        for (int i = 0; i < line.length(); i++)
            letters[line.charAt(i)]++;

        File file = new File("lgame.dict");
        int len = (int) file.length() - 1;
        dict_bytes = new byte[len];
        FileInputStream in = new FileInputStream(file);
        new DataInputStream(in).readFully(dict_bytes);
        in.close();
        sort_bytes = new byte[len];
        System.arraycopy(dict_bytes, 0, sort_bytes, 0, len);
        for (int i = 0; i < len; i++) {
            for (; i < len; i++) {
                if (!is_letter(i)) {
                    break;
                }
            }
            if (i >= len)
                break;
            int off = i;
            for (; i < len; i++) {
                if (is_letter(i)) {
                    break;
                }
            }

            init_word(off, i);
        }

        keys = new Key[inputs.length + 1];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new Key();
        }
        mark = new boolean[inputs.length];
        walk_words(0, 0);
        pw.println(max_score);
        Object[] ar = max_set.toArray();
        Arrays.sort(ar);
        for (Object o : ar) {
            pw.println(o);
        }
        pw.close();
    }

    private static int get_score(int start, int end) {
        int i;
        int s = 0;
        for (i = start; i < end; i++) {
            int c = dict_bytes[i];
            if (--letters[c] < 0) {
                break;
            }
            s += scores[c];
        }
        if (i == end) {
            i--;
        }
        else {
            s = 0;
        }
        for (; i >= start; i--) {
            letters[dict_bytes[i]]++;
        }
        return s;
    }

    private static void init_word(int start, int end) {
        int score = get_score(start, end);
        if (score == 0) {
            return;
        }
        for (int i = start; i < end; i++)
            for (int j = i; j > start && sort_bytes[j - 1] > sort_bytes[j]; j--) {
                byte b = sort_bytes[j];
                sort_bytes[j] = sort_bytes[j - 1];
                sort_bytes[j - 1] = b;
            }
        Word key = new Word(sort_bytes, start, end, score);
        Word word = wordMap.computeIfAbsent(key, k -> key);
        word.add_word(start, end);
        if (min_word_size > word.length()) {
            min_word_size = word.length();
        }
    }

    private static boolean is_letter(int off) {
        byte b = dict_bytes[off];
        return b < 'a' || b > 'z';
    }

    private static class Key {
        byte[] bytes;
        int start, end;
        int input_off;
        int h;

        private Key(byte[] bytes, int start, int end) {
            this.bytes = bytes;
            this.start = start;
            this.end = end;
        }

        private Key() {
            this(new byte[10], 0, 0);
        }

        private void add(byte b) {
            bytes[end++] = b;
        }

        private void reset(int off) {
            this.h = 0;
            this.end = 0;
            this.input_off = off;
        }

        void refreshKey() {
            h = end - start;
            for (int i = start; i < end; i++)
                h = h * 31 + bytes[i];
        }

        int length() {
            return end - start;
        }

        private int get(int off) {
            return bytes[off + start];
        }

        public boolean equals(Key k) {
            if (this.length() != k.length())
                return false;
            for (int i = this.length() - 1; i >= 0; i--)
                if (this.get(i) != k.get(i))
                    return false;
            return true;
        }

        public int hashCode() {
            return h;
        }

        public String toString() {
            return new String(bytes, start, end - start);
        }
    }

    static class Word extends Key {
        int score;
        ArrayList<Key> words = new ArrayList<>();

        Word(byte[] sort_bytes, int start, int end, int score) {
            super(sort_bytes, start, end);
            this.score = score;
            this.refreshKey();
        }

        void add_word(int start, int end) {
            Key w = new Key(dict_bytes, start, end);
            words.add(w);
        }
    }
}   

 