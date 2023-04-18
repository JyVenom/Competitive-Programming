import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class WhyDidTheCowCrossTheRoad4 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] cTime = new int[c];
        int[][] nTime = new int[n][2];
        for (int i = 0; i < c; i++) {
            cTime[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(cTime);
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            nTime[i][0] = Integer.parseInt(st.nextToken());
            nTime[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nTime, Comparator.comparingInt(arr -> arr[1]));
        Arrays.sort(nTime, Comparator.comparingInt(arr -> arr[0]));

        int[] visited = new int[c];
        Arrays.fill(visited, -1);
        int pairs = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            int a = nTime[i][0];
            int b = nTime[i][1];
            for (int j = start; j < c; j++) {
                int curC = cTime[j];
                if (curC < a) {
                    start++;
                }
                else if (curC >= a && curC <= b) {
                    if (visited[j] == -1) {
                        pairs++;
                        visited[j] = i;
                    }
                    else {
                        int aTemp = nTime[visited[j]][0];
                        int bTemp = nTime[visited[j]][1];
                        for (int k = j + 1; k < c; k++) {
                            int curC2 = cTime[k];
                            if (curC2 < aTemp) {
                                start++;
                            }
                            else if (curC2 >= aTemp && curC2 <= bTemp) {
                                pairs++;
                                visited[k] = visited[j];
                                visited[j] = i;
                                break;
                            }
                            else if (curC2 > bTemp) {
                                break;
                            }
                        }
                    }
                    break;
                }
                else if (curC > b) {
                    break;
                }
            }
        }

        Arrays.sort(visited);
        pw.println(pairs);
        pw.close();
    }
}
