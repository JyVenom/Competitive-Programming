import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class IcyPerimeter {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
        PrintWriter out = new PrintWriter(new File("perimeter.out"));

        int N = Integer.parseInt(br.readLine());
        boolean[][] data = new boolean[N][N];
        boolean[][] covered = new boolean[N][N];
        ArrayList<int[]> blobs = new ArrayList<>(1);

        String line;
        for (int i = 0; i < N; i++){
            line = br.readLine();
            for (int j = 0; j < N; j++){
                data[i][j] = (line.charAt(j) == '#');
            }
        }

        int[] blob;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (!covered[i][j]){
                    blob = findBlob(data, covered, i, j);
                    blobs.add(blob);
                }
            }
        }
        blobs.sort(Comparator.comparingInt(a->a[0]));

        ArrayList<int[]> same = new ArrayList<>();
        same.add(blobs.get(blobs.size() - 1));
        int area = blobs.get(blobs.size() - 1)[0];
        for (int i = blobs.size() - 2; i >= 0; i--){
            if (blobs.get(i)[0] == area){
                same.add(blobs.get(i));
            }
        }
        same.sort(Comparator.comparingInt(a->a[1]));

        out.println(same.get(0)[0] + " " + same.get(0)[1]);
        out.close();
    }

    private static int[] findBlob (boolean[][] data, boolean[][] covered, int i, int j){
        if (!data[i][j] || covered[i][j]){
            return new int[2];
        }

        int area = 0;
        int perimeter = 0;
        if (data[i][j]){
            covered[i][j] = true;
            area++;
            if (i == data.length - 1){
                perimeter++;
            }
            else if (!data[i + 1][j]){
                perimeter++;
            }
            if (i == 0){
                perimeter++;
            }
            else if (!data[i - 1][j]){
                perimeter++;
            }
            if (j == data[0].length - 1) {
                perimeter++;
            }
            else if (!data[i][j + 1]){
                perimeter++;
            }
            if (j == 0) {
                perimeter++;
            }
            else if (!data[i][j - 1]){
                perimeter++;
            }
        }
        int[] temp = new int[2];
        if (i != data.length - 1) {
            temp = findBlob(data, covered, i + 1, j);
            area += temp[0];
            perimeter += temp[1];
        }if (i != 0){
            temp = findBlob(data, covered, i - 1, j);
            area += temp[0];
            perimeter += temp[1];
        }if (j != data[0].length - 1) {
            temp = findBlob(data, covered, i, j + 1);
            area += temp[0];
            perimeter += temp[1];
        }if (j != 0) {
            temp = findBlob(data, covered, i, j - 1);
            area += temp[0];
            perimeter += temp[1];
        }
        temp[0] = area;
        temp[1] = perimeter;
        return temp;
    }
}
