import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class TheBucketList {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("blist.in")); //or what it calls for ("promote.in")
        PrintWriter out = new PrintWriter(new File("blist.out")); //or what it calls for ("promote.out")

        int cows = Integer.parseInt(sc.nextLine());
        int[][] data = new int[cows][4];
        String line;
        for (int i = 0; i < cows; i++){ //get data
            line = sc.nextLine();
            data[i][0] = Integer.parseInt(line.substring(0,line.indexOf(' ')));
            data[i][1] = Integer.parseInt(line.substring(line.indexOf(' ') + 1, line.lastIndexOf(' ')));
            data[i][2] = Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1));
            data[i][3] = 0; //extra column i added for how many "reserved" for each cow so far
        }

        Arrays.sort(data, Comparator.comparingInt(o -> o[0])); //sort data based on column 1 using lambda

        int bucketsUsed = 0;
        for (int i = 0; i < cows; i++){ //go through each cow
            if (data[i][2] - data[i][3] > 0) { //if not enough "reserved"
                bucketsUsed += data[i][2] - data[i][3]; //use the necessary buckets
                data[i][3] = data[i][2];
            }
            int spareBuckets = data[i][2];
            if (spareBuckets > 0) {
                for (int j = i + 1; j < cows; j++) { //"reserve" these buckets on cows who go after
                    if (data[j][0] > data[i][1]) { //if cow goes after current cow
                        if (data[j][2] - data[j][3] > 0) { //if not enough buckets reserved
                            int bucketsToReuse = Math.min(spareBuckets, data[j][2] - data[j][3]);
                            data[j][3] += bucketsToReuse;
                            spareBuckets -= bucketsToReuse;
                            if (spareBuckets == 0) { //if used up all spare buckets, no need to keep going
                                break;
                            }
                        }
                    }
                }
            }
        }

        out.println(bucketsUsed);
        out.close();
    }
}
