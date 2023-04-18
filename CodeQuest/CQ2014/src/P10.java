import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class P10 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Prob10.in.txt"));
        PrintWriter pw = new PrintWriter(System.out);

        ArrayList<char[]> data = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            data.add(line.toCharArray());
        }

        int sz = data.size() - 1;
        for (int i = 0; i < data.size(); i += 2) {
            pw.print("Start: " + data.get(i)[0] + data.get(i)[1] + data.get(i)[2] + ", End: ");
            int row = i, col = 3;
            while (data.get(row)[col] == '=') {
                if (row > 0) {
                    boolean done = data.get(row - 1)[col] == '|';
                    while (row > 0 && data.get(row - 1)[col] == '|') {
                        row -= 2;
                    }
                    if (done) {
                        col++;
                        continue;
                    }
                }
                if (row < sz) {
                    while (row < sz && data.get(row + 1)[col] == '|') {
                        row += 2;
                    }
                }
                col++;
            }
            pw.println("" + data.get(row)[col++] + data.get(row)[col++] + data.get(row)[col]);
        }

        pw.close();
    }
}
