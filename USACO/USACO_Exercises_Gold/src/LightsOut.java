import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LightsOut {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lightsout.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightsout.out")));

        int N = Integer.parseInt(br.readLine());
        int[][] data = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        List<vertex> vertices = new ArrayList<>();
        vertex prev = new vertex();
        vertices.add(prev);
        for (int i = 1; i < N; i++) {
            int I = i - 1;
            double dist = Math.hypot(data[I][0] - data[i][0], data[I][1] - data[i][1]);
            vertex v = new vertex();
            v.setPrev(dist);
            vertices.add(v);
            prev.setNext(dist);

            prev = v;

             if (i == N - 1) {
                 dist = Math.hypot(data[0][0] - data[i][0], data[0][1] - data[i][1]);
                 vertices.get(0).setPrev(dist);
                 v.setNext(dist);
             }
        }
        int n = N - 1;
        double bc = Math.hypot(data[1][0] - data[n][0], data[1][1] - data[n][1]);
        vertices.get(0).setAngle((int) (Math.acos(vertices.get(0).prev * vertices.get(0).prev + vertices.get(0).next * vertices.get(0).next - bc * bc) * (180 / Math.PI)));
        bc = Math.hypot(data[0][0] - data[n - 1][0], data[0][1] - data[n - 1][1]);
        vertices.get(n).setAngle((int) (Math.acos(vertices.get(n).prev * vertices.get(n).prev + vertices.get(n).next * vertices.get(n).next - bc * bc) * (180 / Math.PI)));
        for (int i = 1; i < n; i++) {
            bc = Math.hypot(data[i - 1][0] - data[i + 1][0], data[i - 1][1] - data[i + 1][1]);
            vertices.get(i).setAngle((int) (Math.acos(vertices.get(i).prev * vertices.get(i).prev + vertices.get(i).next * vertices.get(i).next - bc * bc) * (180 / Math.PI)));
        }
        int[] dist = new int[N];
        int temp = 0;
        for (int i = 1; i < N; i++) {
            temp += vertices.get(i).prev;
            dist[i] = temp;
        }
        int start = 0;
        temp = 0;
        for (int i = N - 1; i > 0; i--) {
            temp += vertices.get(i).next;
            if (dist[i] < temp) {
                start = i;
                break;
            }
            else if (dist[i] == temp) {
                start = i - 1;
            }
            dist[i] = temp;
        }
        double[] min = new double[N];
        List<Integer> all = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            all.add(i);
        }
        for (int i = start; i > 0; i--) {
            List<Integer> copy = new ArrayList<>(all);
            for (int j = copy.size() - 1; j >= 0; j--) {
                if (vertices.get(i).angle != vertices.get(i).angle) {
                    copy.remove(i);
                }
            }
            min[i] = helper(copy, vertices, i + 1, vertices.get(i).next);
        }

        double max = 0;
        for (int i = 0; i <= start; i++) {
            max = Math.max(max, min[i] - dist[i]);
        }

        pw.println(max);
        pw.close();
    }

    private static double helper(List<Integer> copy, List<vertex> vertices, int at, double sum) {
        if (copy.size() == 1) {
            return sum;
        }

        for (int i = copy.size() - 1; i >= 0; i--) {
            if (vertices.get(i).angle != vertices.get(at).angle || vertices.get(i).prev != vertices.get(at).prev) {
                copy.remove(i);
            }
            else copy.set(i, copy.get(i) + 1);
        }
        return helper(copy, vertices, at + 1, sum + vertices.get(at).next);
    }

    static class vertex {
        double prev, next;
        int angle;

        public vertex() {

        }

        private void setPrev(double prev) {
            this.prev = prev;
        }

        private void setNext(double next){
            this.next = next;
        }

        private void setAngle(int angle) {
            this.angle = angle;
        }
    }
}
