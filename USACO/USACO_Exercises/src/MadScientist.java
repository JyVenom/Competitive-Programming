import java.io.*;

public class MadScientist {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("breedflip.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("breedflip.out")));

        int n = Integer.parseInt(br.readLine());
        String orig = br.readLine();
        String got = br.readLine();
        br.close();

        int count = 0;
        int start = 0;
        while (start < n) {
            if (got.charAt(start) != orig.charAt(start)){
                int end = start;
                for (int i = start + 1; i < n; i++) {
                    if (got.charAt(i) != orig.charAt(i)){
                        end = i;
                    }
                    else {
                        break;
                    }
                }
                count++;
                start = end;
            }
            start++;
        }

        pw.println(count);
        pw.close();
    }
}
