/*
ID: jerryya2
LANG: JAVA
TASK: prefix
*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class prefix {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("prefix.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));

        String line = br.readLine();
        StringTokenizer st;
        ArrayList<String> p = new ArrayList<>();
        while (!line.equals(".")) {
            st = new StringTokenizer(line);
            int size = st.countTokens();
            for (int i = 0; i < size; i++) {
                p.add(st.nextToken());
            }
            line = br.readLine();
        }
        line = br.readLine();
        StringBuilder tmpS = new StringBuilder();
        while (line != null) {
            tmpS.append(line);
            line = br.readLine();
        }
        String s = tmpS.toString();
        br.close();

        int sLen = s.length();
        boolean[] visited = new boolean[sLen + 1];
        visited[0] = true;
        int max = 0;
        int len = visited.length;
        for (int i = 0; i < len; i++) {
            if (visited[i]) {
                for (String value : p) {
                    int vLen = value.length();
                    int ivLen = i + vLen;
                    if (ivLen <= sLen && value.equals(s.substring(i, ivLen))) {
                        visited[ivLen] = true;
                        max = Math.max(max, ivLen);
                    }
                }
            }
        }

        pw.println(max);
        pw.close();
    }
}