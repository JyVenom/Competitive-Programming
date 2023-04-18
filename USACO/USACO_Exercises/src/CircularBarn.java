import java.io.*;

public class CircularBarn {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));

        int n = Integer.parseInt(br.readLine());
        int[] r = new int[n];
        for (int i = 0; i < n; i++){
            r[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++){
            int thisTime = 0;
            int dist = 1;
            for (int j = i + 1; j < n; j++){
                thisTime += r[j] * dist;
                dist++;
            }
            for (int j = 0; j < i; j++){
                thisTime += r[j] * dist;
                dist++;
            }
            min = Math.min(min, thisTime);
        }

        pw.println(min);
        pw.close();
    }
}
