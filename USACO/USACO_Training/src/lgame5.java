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

public class lgame5 {
    private static final int[] values = new int[]{2, 5, 4, 4, 1, 6, 5, 5, 1, 7, 6, 3, 5, 2, 3, 5, 7, 2, 1, 2, 4, 6, 6, 7, 5, 7};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lgame.in"));
        BufferedReader dictBr = new BufferedReader(new FileReader("lgame.dict"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lgame.out")));

        String[] letters = br.readLine().split("");
        Arrays.sort(letters);
        long start = System.currentTimeMillis(); //jerry
        ArrayList<String> letters2 = new ArrayList<>(Arrays.asList(letters));
        String line = dictBr.readLine();
        ArrayList<word> dict = new ArrayList<>();
        while (!line.equals(".")) {
            word word = new word(line);
            if (isPos(letters2, word.letters)) {
                dict.add(word);
            }
            line = dictBr.readLine();
        }
        System.out.println(System.currentTimeMillis() - start); //jerry

        start = System.currentTimeMillis(); //jerry
        ArrayList<word> dict2 = new ArrayList<>(dict);
        for (word w : dict2) {
            Arrays.sort(w.letters);
        }
        dict2.sort(lgame5::compare);
        System.out.println(System.currentTimeMillis() - start); //jerry
        dict.sort(Comparator.comparingInt(o -> o.value));
        Collections.reverse(dict);
        ArrayList<pair> ans = new ArrayList<>();
        //Single word answers
        int maxScore = dict.get(0).value;
        ans.add(new pair(dict.get(0)));
        for (int i = 1; i < dict.size(); i++) {
            word cur = dict.get(i);
            if (cur.value == dict.get(i - 1).value) {
                ans.add(new pair(cur));
            }
        }
        //Dual word answers
        findAns(ans, dict2, new ArrayList<>(), letters, maxScore, letters.length - 1, 0);

        pw.println(maxScore);
        for (pair pair : ans) {
            pw.print(pair.w1);
            if (pair.w2 != null) {
                pw.print(" " + pair.w2);
            }
            pw.println();
        }
        pw.close();
    }

    private static void findAns(ArrayList<pair> ans, ArrayList<word> dict, ArrayList<String> temp, String[] letters, int max, int end, int at) {
        if (at < end) {
            findAns(ans, dict, temp, letters, max, end, at + 1);
            temp.add(letters[at]);
            findAns(ans, dict, temp, letters, max, end, at + 1);
            temp.remove(temp.size() - 1);
        } else {
            if (temp.size() == 5) {
                temp.add(letters[at]);
            }
            if (temp.size() >= 6) {
                findAnsHelper(ans, dict, temp, new ArrayList<>(), new ArrayList<>(), max, temp.size() - 1, 0);
                temp.add(letters[at]);
                findAnsHelper(ans, dict, temp, new ArrayList<>(), new ArrayList<>(), max, temp.size() - 1, 0);

            }
        }
    }

    private static void findAnsHelper(ArrayList<pair> ans, ArrayList<word> dict, ArrayList<String> temp, ArrayList<String> word1, ArrayList<String> word2, int max, int end, int at) {
        if (at < end) {
            String cur = temp.get(at);
            word1.add(cur);
            findAnsHelper(ans, dict, temp, word1, word2, max, end, at + 1);
            word1.remove(word1.size() - 1);

            word2.add(cur);
            findAnsHelper(ans, dict, temp, word1, word2, max, end, at + 1);
            word2.remove(word2.size() - 1);
        } else {
            String cur = temp.get(at);
            word1.add(cur);
            if (word1.size() >= 3 && word2.size() >= 3) {
                StringBuilder s1 = new StringBuilder();
                StringBuilder s2 = new StringBuilder();
                for (String s : word1) {
                    s1.append(s);
                }
                for (String s : word2) {
                    s2.append(s);
                }
                word w1 = new word(s1.toString());
                word w2 = new word(s2.toString());
                int val1 = 0;
                int val2 = 0;
                ArrayList<Integer> ans1 = binSearch(dict, w1, dict.size() - 1);
                ArrayList<Integer> ans2 = binSearch(dict, w2, dict.size() - 1);
                if (ans1.size() > 0 && ans2.size() > 0) {
                    for (int a : ans1) {
                        for (int b : ans2) {
                            ans.add(new pair(new word(dict.get(a).word), new word(dict.get(b).word)));
                        }
                    }
                }
                int sum = val1 + val2;
                if (sum == max) {
                    ans.add(new pair(new word(word1.toString()), new word(word2.toString())));
                }
            }
            word1.remove(word1.size() - 1);

            word2.add(cur);
            if (word1.size() >= 3 && word2.size() >= 3) {
                StringBuilder s1 = new StringBuilder();
                StringBuilder s2 = new StringBuilder();
                for (String s : word1) {
                    s1.append(s);
                }
                for (String s : word2) {
                    s2.append(s);
                }
                word w1 = new word(s1.toString());
                word w2 = new word(s2.toString());
                int val1 = 0;
                int val2 = 0;
                ArrayList<Integer> ans1 = binSearch(dict, w1, dict.size() - 1);
                ArrayList<Integer> ans2 = binSearch(dict, w2, dict.size() - 1);
                if (ans1.size() > 0 && ans2.size() > 0) {
                    for (int a : ans1) {
                        for (int b : ans2) {
                            ans.add(new pair(new word(dict.get(a).word), new word(dict.get(b).word)));
                        }
                    }
                }
                int sum = val1 + val2;
                if (sum == max) {
                    ans.add(new pair(new word(word1.toString()), new word(word2.toString())));
                }
            }
            word2.remove(word2.size() - 1);
        }
    }

    private static boolean isPos(ArrayList<String> letters, String[] word) {
        ArrayList<String> letters2 = new ArrayList<>(letters);
        for (String letter : word) {
            int place = Collections.binarySearch(letters2, letter);
            if (place >= 0) {
                letters2.remove(place);
            } else {
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

    private static int compare(word o1, word o2) {
        int o1length = o1.length;
        int o2length = o2.length;
        int min = Math.min(o1length, o2length);
        for (int i = 0; i < min; i++) {
            int diff = o1.letters[i].charAt(0) - o2.letters[i].charAt(0);
            if (diff != 0) {
                return diff;
            }
        }
        return o1length - o2length;
    }

    private static ArrayList<Integer> binSearch(ArrayList<word> arr, word key, int high) {
        ArrayList<Integer> ans = new ArrayList<>();
        int low = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            word cur = arr.get(mid);
            int val = compare(cur, key);
            if (val < 0) {
                low = mid + 1;
            } else if (val > 0) {
                high = mid - 1;
            } else {
                for (int i = mid; i > 0; i--) {
                    cur = arr.get(i);
                    if (compare(cur, key) == 0) {
                        ans.add(i);
                    } else {
                        break;
                    }
                }
                for (int i = mid + 1; i < arr.size(); i++) {
                    cur = arr.get(i);
                    if (compare(cur, key) == 0) {
                        ans.add(i);
                    } else {
                        break;
                    }
                }
                break;
            }
        }
        return ans;
    }

    private static class pair {
        word w1;
        word w2;

        private pair(word word) {
            w1 = word;
            w2 = null;
        }

        private pair(word word1, word word2) {
            w1 = word1;
            w2 = word2;
        }
    }

    private static class word {
        String[] letters;
        String word;
        int length;
        int value;

        private word(String word) {
            this.word = word;
            letters = word.split("");
            length = word.length();
            value = calcVal(letters);
        }
    }
}
