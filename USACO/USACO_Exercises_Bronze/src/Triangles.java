import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Triangles {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("triangles.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));

        int n = Integer.parseInt(br.readLine());
        int[][] posts = new int[n][2];
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            posts[i][0] = Integer.parseInt(st.nextToken());
            posts[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(posts, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(posts, Comparator.comparingInt(arr -> arr[0]));

        int max = 0;
        for (int i = 0; i < n; i++){
            int x = posts[i][0];
            int y = posts[i][1];
            int minx = minX(posts, y);
            int maxX = maxX(posts, y);
            int minY = minY(posts, x);
            int maxY = maxY(posts, x);
            int t1 = (maxX - x) * (maxY - y);
            int t2 = (maxX - x) * (y - minY);
            int t3 = (x - minx) * (y - minY);
            int t4 = (x - minx) * (maxY - y);
            max = Math.max(max, Math.max(Math.max(t1, t2), Math.max(t3, t4)));
        }

        pw.println(max);
        pw.close();
    }

    private static int maxY (int[][] data, int x) {
        int max = 0;
        for (int i = 0; i < data.length; i++){
            if (data[i][0] == x){
                max = Math.max(max, data[i][1]);
            }
        }
        return max;
    }

    private static int maxX (int[][] data, int y) {
        int max = 0;
        for (int i = 0; i < data.length; i++){
            if (data[i][1] == y){
                max = Math.max(max, data[i][0]);
            }
        }
        return max;
    }

    private static int minY (int[][] data, int x) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < data.length; i++){
            if (data[i][0] == x){
                min = Math.min(min, data[i][1]);
            }
        }
        return min;
    }

    private static int minX (int[][] data, int y) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < data.length; i++){
            if (data[i][1] == y){
                min = Math.min(min, data[i][0]);
            }
        }
        return min;
    }
}
