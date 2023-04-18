import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class LightsOut2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lightsout.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightsout.out")));

        //import data
        int N = Integer.parseInt(br.readLine());
        int[][] data = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
        }

        //process data and add to list
        List<vertex> vertices = new ArrayList<>();
        vertex prev = new vertex();
        vertices.add(prev);
        for (int i = 1; i < N; i++) {
            int I = i - 1;
            int dist = (int) Math.hypot(data[I][0] - data[i][0], data[I][1] - data[i][1]);
            vertex v = new vertex();
            v.setPrev(dist);
            vertices.add(v);
            prev.setNext(dist);

            prev = v;

            if (i == N - 1) {
                dist = (int) Math.hypot(data[0][0] - data[i][0], data[0][1] - data[i][1]);
                vertices.get(0).setPrev(dist);
                v.setNext(dist);
            }
        }
        //add angles
        int n = N - 1;
        int prevDir;
        if (data[0][0] == data[1][0]) {
            if (data[0][1] > data[1][1]) { //came down
                prevDir = 1;
            } else { //came up
                prevDir = 0;
            }
        } else {
            if (data[0][0] < data[1][0]) { //came right
                prevDir = 3;
            } else { //came left
                prevDir = 2;
            }
        }
        if (data[n][0] == data[0][0]) {
            if (data[n][1] > data[0][1]) { //came down
                if (prevDir == 2) {
                    vertices.get(0).setAngle(90);
                } else {
                    vertices.get(0).setAngle(270);
                }
            } else { //came up
                if (prevDir == 2) {
                    vertices.get(0).setAngle(270);
                } else {
                    vertices.get(0).setAngle(90);
                }
            }
        } else {
            if (data[n][0] < data[0][0]) { //came right
                if (prevDir == 0) {
                    vertices.get(0).setAngle(270);
                } else {
                    vertices.get(0).setAngle(90);
                }
            } else { //came left
                if (prevDir == 0) {
                    vertices.get(0).setAngle(90);
                } else {
                    vertices.get(0).setAngle(270);
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (data[i][0] == data[i + 1][0]) {
                if (data[i][1] > data[i + 1][1]) { //went down
                    if (prevDir == 2) {
                        vertices.get(i).setAngle(270);
                    } else {
                        vertices.get(i).setAngle(90);
                    }
                    prevDir = 1;
                } else { //went up
                    if (prevDir == 2) {
                        vertices.get(i).setAngle(90);
                    } else {
                        vertices.get(i).setAngle(270);
                    }
                    prevDir = 0;
                }
            } else {
                if (data[i][0] < data[i + 1][0]) { //went right
                    if (prevDir == 0) {
                        vertices.get(i).setAngle(90);
                    } else {
                        vertices.get(i).setAngle(270);
                    }
                    prevDir = 3;
                } else { //went left
                    if (prevDir == 0) {
                        vertices.get(i).setAngle(270);
                    } else {
                        vertices.get(i).setAngle(90);
                    }
                    prevDir = 2;
                }
            }
        }
        if (data[n][0] == data[0][0]) {
            if (data[n][1] > data[0][1]) { //came down
                if (prevDir == 2) {
                    vertices.get(n).setAngle(270);
                } else {
                    vertices.get(n).setAngle(90);
                }
            } else { //came up
                if (prevDir == 2) {
                    vertices.get(n).setAngle(90);
                } else {
                    vertices.get(n).setAngle(270);
                }
            }
        } else {
            if (data[n][0] < data[0][0]) { //came right
                if (prevDir == 0) {
                    vertices.get(n).setAngle(90);
                } else {
                    vertices.get(n).setAngle(270);
                }
            } else { //came left
                if (prevDir == 0) {
                    vertices.get(n).setAngle(270);
                } else {
                    vertices.get(n).setAngle(90);
                }
            }
        }
        //find min dist with lights on
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
            } else if (dist[i] == temp) {
                start = i - 1;
            }
            dist[i] = temp;
        }
        //temp list with all indexes to save time
        int[] min = new int[N];
        List<Integer> all = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            all.add(i);
        }
        //find min dist with lights off
        for (int i = start; i > 0; i--) {
            List<Integer> copy = new ArrayList<>(all);
            for (int j = n; j > 0; j--) {
                if (vertices.get(j).angle != vertices.get(i).angle) {
                    copy.remove(j - 1);
                } else copy.set(j - 1, copy.get(j - 1) + 1);
            }
            if (copy.get(copy.size() - 1) == vertices.size()) {
                copy.remove(copy.size() - 1);
            }
            if (copy.size() == 1) {
                min[i] = dist[i];
            } else {
                min[i] = helper(copy, vertices, dist, i + 1, vertices.get(i).next);
            }
        }

        //find max diff
        int max = 0;
        for (int i = 0; i <= start; i++) {
            max = Math.max(max, min[i] - dist[i]);
        }

        //print ans (max) and close
        pw.println(max);
        pw.close();
    }

    private static int helper(List<Integer> copy, List<vertex> vertices, int[] dist, int at, int sum) {
        if (at == vertices.size()) {
            return sum;
        }

        for (int i = copy.size() - 1; i >= 0; i--) {
            if (vertices.get(copy.get(i)).angle != vertices.get(at).angle || vertices.get(copy.get(i)).prev != vertices.get(at).prev) {
                copy.remove(i);
            } else copy.set(i, copy.get(i) + 1);
        }
        if (copy.get(copy.size() - 1) == vertices.size()) {
            copy.remove(copy.size() - 1);
        }
        if (copy.size() == 1) {
            return sum + dist[at];
        }

        return helper(copy, vertices, dist, at + 1, sum + vertices.get(at).next);
    }

    static class vertex {
        int prev, next, angle;

        private void setPrev(int prev) {
            this.prev = prev;
        }

        private void setNext(int next) {
            this.next = next;
        }

        private void setAngle(int angle) {
            this.angle = angle;
        }
    }
}
