import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.*;
import java.util.Random;

public class CowSteepleChaseII6 extends JComponent{
    private static long[][][] clusters;
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        PrintWriter out = new PrintWriter(new File("cowjump.out"));

        N = Integer.parseInt(br.readLine());
        long[][] data = new long[N / 100][2];
        long[][] centroids = new long[10][2];
        clusters = new long[centroids.length][data.length][2];

        int temp;
        String line;
        long maxX = 0, minX = 1000000000, maxY = 0, minY = 1000000000;
        int correct;
        for (int i = 0; i < N; i++){
            line = br.readLine();
            if ((i + 1) % 100 == 0){
                correct = ((i + 1) / 100) - 1;
                temp = line.indexOf(' ', line.indexOf(' ') + 1) + 1;
                data[correct][0] = Integer.parseInt(line.substring(temp, line.indexOf(' ', temp)));
                temp = line.lastIndexOf(' ') + 1;
                data[correct][1] = Integer.parseInt(line.substring(temp));
                maxX = Math.max(maxX, data[correct][0]);
                maxY = Math.max(maxY, data[correct][1]);
                minX = Math.min(minX, data[correct][0]);
                minY = Math.min(minY, data[correct][1]);
            }
        }

        for (int i = 0; i < centroids.length; i++){
            centroids[i] = data[i];
//            Random r = new Random();
//            centroids[i][0] = (r.nextInt((int) ((maxX - minX) + 1)) + minX);
        }

        long[] elem;
        long[][] oldCenters;
        do {
            oldCenters = centroids.clone();
            clusters = new long[centroids.length][data.length][2];
            for (int i = 0; i < data.length; i++) {
                int min = 0;
                for (int j = 0; j < centroids.length; j++) {
                    if (Point2D.distance(centroids[j][0], centroids[j][1], data[i][0], data[i][1]) < Point2D.distance(centroids[min][0], centroids[min][1], data[i][0], data[i][1])) {
                        min = j;
                    }
                }

                elem = new long[]{data[i][0], data[i][1]};
                add(clusters, min, elem);
                System.out.println(i);
            }
            for (int i = 0; i < clusters.length; i++) {
                centroids[i] = center(clusters, i);
            }
        } while (notSame(centroids, oldCenters));

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 200, 200);
        window.getContentPane().add(new CowSteepleChaseII6());
        window.setVisible(true);

        out.close();
    }
    public void paint(Graphics g) {
        g.setColor(Color.red);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[0][i][0], (int) clusters[0][i][1], (int) clusters[0][i][0], (int) clusters[0][i][1]);
        }
        g.setColor(Color.orange);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[1][i][0], (int) clusters[1][i][1], (int) clusters[1][i][0], (int) clusters[1][i][1]);
        }
        g.setColor(Color.yellow);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[2][i][0], (int) clusters[2][i][1], (int) clusters[2][i][0], (int) clusters[2][i][1]);
        }
        g.setColor(Color.green);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[3][i][0], (int) clusters[3][i][1], (int) clusters[3][i][0], (int) clusters[3][i][1]);
        }
        g.setColor(Color.blue);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[4][i][0], (int) clusters[4][i][1], (int) clusters[4][i][0], (int) clusters[4][i][1]);
        }
        g.setColor(Color.cyan);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[5][i][0], (int) clusters[5][i][1], (int) clusters[5][i][0], (int) clusters[5][i][1]);
        }
        g.setColor(Color.magenta);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[6][i][0], (int) clusters[6][i][1], (int) clusters[6][i][0], (int) clusters[6][i][1]);
        }
        g.setColor(Color.pink);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[7][i][0], (int) clusters[7][i][1], (int) clusters[7][i][0], (int) clusters[7][i][1]);
        }
        g.setColor(Color.white);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[8][i][0], (int) clusters[8][i][1], (int) clusters[8][i][0], (int) clusters[8][i][1]);
        }
        g.setColor(Color.gray);
        for (int i = 0; i < N / 100; i++){
            g.drawLine((int) clusters[9][i][0], (int) clusters[9][i][1], (int) clusters[9][i][0], (int) clusters[9][i][1]);
        }
    }
    
    private static void add (long[][][] data, int index, long[] elem){
        for (int i = 0; i < data[index].length; i++){
            if (data[index][i][0] == 0){
                data[index][i] = elem;
                return;
            }
        }
    }

    private static long[] center (long[][][] a1, int layer){
        int x = 0;
        int y = 0;
        int xDiv = a1[layer].length;
        int yDiv = a1[layer].length;
        for (int i = 0; i < a1[layer].length; i++){
            if (a1[layer][i][0] != 0) {
                x += a1[layer][i][0];
            }
            else {
                if (i > 0){
                    xDiv = Math.min(xDiv, i);
                }
            }
            if (a1[layer][i][1] != 0) {
                y += a1[layer][i][1];
            }
            else {
                if (i > 0) {
                    yDiv = Math.min(yDiv, i);
                }
            }
        }
        return new long[]{(x + xDiv / 2) / xDiv, (y + yDiv / 2) / yDiv};
    }

    private static boolean notSame (long[][] a1, long[][] a2){
        for (int i = 0; i < a1.length; i++){
            if (a1[i][0] != a2[i][0] || a1[i][1] != a2[i][1]){
                return true;
            }
        }
        return false;
    }
}
