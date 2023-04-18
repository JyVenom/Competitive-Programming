import java.io.*;
import java.util.ArrayList;

public class WheresBessie {
    public static String[] data;
    public static ArrayList<WheresBessie> PCLs = new ArrayList<>();
    public static boolean[][] visited = new boolean[20][20];
    private final int i1;
    private final int j1;
    private final int i2;
    private final int j2;

    public WheresBessie(int I1, int J1, int I2, int J2){
        i1 = I1;
        j1 = J1;
        i2 = I2;
        j2 = J2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("where.in"));
        PrintWriter out = new PrintWriter(new File("where.out"));

        int n = Integer.parseInt(br.readLine());
        data = new String[n];


        for (int i = 0; i < n; i++){
            data[i] = br.readLine();
        }

        for (int i1 = 0; i1 < n; i1++) {
            for (int j1 = 0; j1 < n; j1++) {
                for (int i2 = i1; i2 < n; i2++) {
                    for (int j2 = j1; j2 < n; j2++) {
                        if (isPlc(i1, j1, i2, j2)) {
                            WheresBessie pcl = new WheresBessie(i1, j1, i2, j2);
                            PCLs.add(pcl);
                        }
                    }
                }
            }
        }


//        for (int i = 0; i < n; i++){
//            String line = br.readLine();
//            for (int j = 0; j < n; j++){
//                data[i][j] = line.charAt(j);
//            }
//        }

//        for (int i = 0; i < PCLs.size(); i++){
//            char[][] temp = PCLs.get(i);
//            for (int j = 0; j < temp.length; j++){
//                for (int k = 0; k < temp[0].length; k++){
//                    out.print(temp[i][j]);
//                }
//                out.println();
//            }
//        }
        int answer = 0;
        for (int i = 0; i < PCLs.size(); i++) {
            if (pclMaximal(i)) {
                answer++;
            }
        }

        out.println(answer);
        out.close();
    }

    public static boolean isPlc(int i1, int j1, int i2, int j2) {
        int num_colors = 0;
        int[] color_count = new int[26];
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                visited[i][j] = false;
            }
        }
        for (int i = i1; i <= i2; i++) {
            for (int j = j1; j <= j2; j++) {
                if (!visited[i][j]) {
                    int c = data[i].charAt(j) - 'A';
                    if (color_count[c] == 0) {
                        num_colors++;
                    }
                    color_count[c]++;
                    visit(i, j, c, i1, j1, i2, j2);
                }
            }
        }
        if (num_colors != 2) {
            return false;
        }
        boolean found_one = false;
        boolean found_many = false;
        for (int i = 0; i < 26; i++) {
            if (color_count[i] == 1) {
                found_one = true;
            }
            if (color_count[i] > 1) {
                found_many = true;
            }
        }
        return found_one && found_many;
    }

    public static void visit(int i, int j, int c, int i1, int j1, int i2, int j2) {
        visited[i][j] = true;
        if (i > i1 && data[i - 1].charAt(j) - 'A' == c && !visited[i - 1][j]) {
            visit(i - 1, j, c, i1, j1, i2, j2);
        }
        if (i < i2 && data[i + 1].charAt(j) - 'A' == c && !visited[i + 1][j]) {
            visit(i + 1, j, c, i1, j1, i2, j2);
        }
        if (j > j1 && data[i].charAt(j - 1) - 'A' == c && !visited[i][j - 1]) {
            visit(i, j - 1, c, i1, j1, i2, j2);
        }
        if (j < j2 && data[i].charAt(j + 1) - 'A' == c && !visited[i][j + 1]) {
            visit(i, j + 1, c, i1, j1, i2, j2);
        }
    }

    public static boolean pclMaximal(int x) {
        for (int i = 0; i < PCLs.size(); i++) {
            if (i != x && pclInPcl(x, i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean pclInPcl(int x, int y) {
        return PCLs.get(x).i1 >= PCLs.get(y).i1 && PCLs.get(x).i2 <= PCLs.get(y).i2 && PCLs.get(x).j1 >= PCLs.get(y).j1 && PCLs.get(x).j2 <= PCLs.get(y).j2;
    }
}