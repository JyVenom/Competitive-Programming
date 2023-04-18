import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class LemonadeLine2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));
        PrintWriter out = new PrintWriter(new File("lemonade.out"));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> waitTimes = new ArrayList<>(n);

        int temp = 0;
        String line = br.readLine();
        for (int i = 0; i < n - 1; i++){
            waitTimes.add(Integer.parseInt(line.substring(temp, line.indexOf(' ', temp))));
            temp = line.indexOf(' ', temp) + 1;
        }
        waitTimes.add(Integer.parseInt(line.substring(temp)));
        Collections.sort(waitTimes);
        Collections.reverse(waitTimes);

        int cowsInLine = 0;
        for (int i = 0; i < n; i++){
            if (waitTimes.get(i) < cowsInLine){
                break;
            }
            cowsInLine++;
        }

        out.println(cowsInLine);
        out.close();
    }
}
