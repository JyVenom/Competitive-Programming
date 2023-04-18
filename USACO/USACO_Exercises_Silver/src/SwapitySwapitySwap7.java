import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SwapitySwapitySwap7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("swap.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] moves = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            moves[i][0] = Integer.parseInt(st.nextToken()) - 1;
            moves[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        //simulate for one round (for k = 1)
        int[] order = new int[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        for (int[] move : moves) {
            int half = (move[0] + move[1]) / 2;
            for (int i = move[0], i1 = move[1]; i <= half; i++, i1--) {
                int temp = order[i1];
                order[i1] = order[i];
                order[i] = temp;
            }
        }
        //now simulate for remaining k - 1 rounds
        int[] finalOrder = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (order[i] == i) {
                finalOrder[i] = i;
            }
            else if (!visited[i]) {
                int at = i;
                boolean foundRepeat = false;
                for (int j = 0; j < k; j++) {
                    at = order[at];

                    if (at == i) {
                        j++;
                        ArrayList<Integer> prev = new ArrayList<>();
                        int at2 = i;
                        for (int l = 0; l < j; l++) {
                            prev.add(at2);
                            at2 = order[at2];
                        }
                        ArrayList<Integer> used = new ArrayList<>();
                        if (j == k) {
                            for (int cur : prev) {
                                visited[at2] = true;
                                finalOrder[cur] = cur;
                            }
                            foundRepeat = true;
                        }
                        else {
                            while (j < k) {
                                used.add(j);
                                j *= 2;
                            }
                            if (j >= k) {
                                j /= 2;
                                int temp = used.size() - 2;
                                while (temp >= 0) {
                                    while (j < k) {
                                        j += used.get(temp);
                                    }
                                    j -= used.get(temp);
                                    temp--;
                                }
                            }

                            at2 = i;
                            for (int l = j; l < k; l++) {
                                at2 = order[at2];
                            }
                            for (int cur : prev) {
                                visited[at2] = true;
                                finalOrder[cur] = at2;
                                at2 = order[at2];
                            }
                            foundRepeat = true;
                            break;
                        }
                    }
                }
                if (!foundRepeat) {
                    visited[i] = true;
                    finalOrder[i] = at;
                }
            }
        }

        for (int cur : finalOrder) {
            pw.println(cur + 1);
        }
        pw.close();
    }
}
