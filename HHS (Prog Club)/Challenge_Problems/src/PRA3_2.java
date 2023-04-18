import java.io.*;
import java.util.ArrayList;

public class PRA3_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));

        String code = br.readLine();
        ArrayList<Integer> list = new ArrayList<>();
        boolean good = true;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '(') {
                list.add(0);
            }
            else if (code.charAt(i) == '{') {
                list.add(1);
            }
            else if (code.charAt(i) == '[') {
                list.add(2);
            }
            else if (code.charAt(i) == ')') {
                int last = list.size() - 1;
                if (list.get(last) != 0) {
                    good = false;
                    break;
                }
                else {
                    list.remove(last);
                }
            }
            else if (code.charAt(i) == '}') {
                int last = list.size() - 1;
                if (list.get(last) != 1) {
                    good = false;
                    break;
                }
                else {
                    list.remove(last);
                }
            }
            else if (code.charAt(i) == ']') {
                int last = list.size() - 1;
                if (list.get(last) != 2) {
                    good = false;
                    break;
                }
                else {
                    list.remove(last);
                }
            }
        }
        pw.println(good ? "YES" : "NO");
        pw.close();
    }
}
