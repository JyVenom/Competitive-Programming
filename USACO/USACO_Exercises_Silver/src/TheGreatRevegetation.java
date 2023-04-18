import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class TheGreatRevegetation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));
        PrintWriter out = new PrintWriter(new File("revegetate.out"));

        String line = br.readLine();
        int N = Integer.parseInt(line.substring(0, line.indexOf(' ')));
        int M = Integer.parseInt(line.substring(line.indexOf(' ') + 1));
        int[][] data = new int[M][3]; //0 for same, 1 for different;
        int[] fields = new int[N + 1];

        int temp;
        for (int i = 0; i < M; i++){
            line = br.readLine();
            if (line.charAt(0) == 'S'){
                data[i][0] = 0;
            }
            else {
                data[i][0] = 1;
            }
            temp = line.indexOf(' ') + 1;
            data[i][1] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
            temp = line.indexOf(' ', temp) + 1;
            data[i][2] = Integer.parseInt(line.substring(temp));
        }
        Arrays.sort(data, Comparator.comparingInt(arr -> arr[0]));

        int f1;
        int f2;
        for (int i = 0; i < M; i++){
            f1 = data[i][1];
            f2 = data[i][2];
            if (data[i][0] == 0){
                if (fields[f1] == 0){
                    if (fields[f2] == 0){
                        fields[f2] = 0;
                    }
                    fields[f1] = fields[f2];
                }
                else if (fields[f2] == 0){
                    fields[f2] = fields[f1];
                }
            }
            else {
                if (fields[f1] == 0){

                }
            }
        }

        out.close();
    }
}
