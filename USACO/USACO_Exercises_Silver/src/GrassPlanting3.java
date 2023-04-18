import java.io.*;

public class GrassPlanting3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("planting.in"));
        PrintWriter out = new PrintWriter(new File("planting.out"));

        int fields = Integer.parseInt(br.readLine());
        int[] data = new int[fields];

        String line;
        int i1;
        int i2;
        for (int i = 0; i < fields - 1; i++){
            line = br.readLine();
            i1 = Integer.parseInt(line.substring(0, line.indexOf(' ')));
            i2 = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
            data[i1 - 1]++;
            data[i2 - 1]++;
        }

        int max = 0;
        for (int i = 0; i < fields; i++){
            if (data[i] > max){
                max = data[i];
            }
        }

        out.println(max + 1);
        out.close();
    }
}