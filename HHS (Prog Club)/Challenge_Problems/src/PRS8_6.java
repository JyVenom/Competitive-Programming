import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PRS8_6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        int n = Integer.parseInt(br.readLine());
        int[] rs = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            rs[i] = Integer.parseInt(st.nextToken());
        }
        ArrayList<website> sites = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            sites.add(new website(br.readLine().toCharArray(), rs[i], i + 1));
        }

        int q = Integer.parseInt(br.readLine());
        sites.sort((o1, o2) -> {
            if (o1.name.length < o2.name.length) {
                for (int i = 0; i < o1.name.length; i++) {
                    int dif = o1.name[i] - o2.name[i];
                    if (dif != 0) {
                        return dif;
                    }
                }
                return -1;
            } else if (o1.name.length > o2.name.length) {
                for (int i = 0; i < o2.name.length; i++) {
                    int dif = o1.name[i] - o2.name[i];
                    if (dif != 0) {
                        return dif;
                    }
                }
                return -1;
            } else {
                for (int i = 0; i < o2.name.length; i++) {
                    int dif = o1.name[i] - o2.name[i];
                    if (dif != 0) {
                        return dif;
                    }
                }
                return 0;
            }
        });
        for (int i = 0; i < q; i++) {
            pw.println(findAns(sites, br.readLine().toCharArray()));
        }

        pw.close();
    }

    private static int findAns(ArrayList<website> sites, char[] s) {
        int mid = binSearch(sites, s);
        if (mid == -1) {
            return 1;
        }
        int max = 0;
        int maxInd = 0;
        for (int i = mid; i < sites.size(); i++) {
            if (compare(s, sites.get(i).name) != 0) {
                break;
            }
            if (sites.get(i).val > max) {
                max = sites.get(i).val;
                maxInd = sites.get(i).num;
            }
        }
        for (int i = mid - 1; i >= 0; i--) {
            if (compare(s, sites.get(i).name) != 0) {
                break;
            }
            if (sites.get(i).val > max) {
                max = sites.get(i).val;
                maxInd = sites.get(i).num;
            }
        }
        return maxInd;
    }

    private static int binSearch(ArrayList<website> arr, char[] key) {
        int low = 0, high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (compare(arr.get(mid).name, key) < 0) {
                low = mid + 1;
            } else if (compare(arr.get(mid).name, key) > 0) {
                high = mid - 1;
            } else if (compare(arr.get(mid).name, key) == 0) {
                return mid;
            }
        }
        return -1;
    }

    private static int compare(char[] s1, char[] s2) {
        if (s1.length < s2.length) {
            for (int i = 0; i < s1.length; i++) {
                int dif = s1[i] - s2[i];
                if (dif != 0) {
                    return dif;
                }
            }
        } else {
            for (int i = 0; i < s2.length; i++) {
                int dif = s1[i] - s2[i];
                if (dif != 0) {
                    return dif;
                }
            }
        }
        return 0;
    }

    private static class website {
        char[] name;
        int val, num;

        public website(char[] name, int val, int num) {
            this.name = name;
            this.val = val;
            this.num = num;
        }
    }
}
