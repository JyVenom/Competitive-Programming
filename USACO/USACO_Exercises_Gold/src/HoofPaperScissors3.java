import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class HoofPaperScissors3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] data = new int[n][3];
//        int[] data2 = new int[n];
        String line = br.readLine();
        if (line.equals("H")) {
            data[0][0] = 1;
        } else if (line.equals("P")) {
            data[0][1] = 1;
        } else {
            data[0][2] = 2;
        }
        for (int i = 1; i < n; i++) {
            data[i] = data[i - 1].clone();
            line = br.readLine();

            if (line.equals("H")) {
                data[i][0]++;
            } else if (line.equals("P")) {
                data[i][1]++;
//                data2[i] = 1;
            } else {
                data[i][2]++;
//                data2[i] = 2;
            }
        }

        int N = n - 1;
        int max = Math.max(data[N][0], Math.max(data[N][1], data[N][2]));
        if (k > 0) {
            ArrayList<int[]> switches = new ArrayList<>();
            int maxIndex = 0;
            int maxA = 0;
            int maxB = 0;
            for (int i = 1; i < n; i++) {
                int I = i - 1;
                int a, b;
                if (data[I][0] > data[I][1]) {
                    if (data[I][0] > data[I][2]) {
                        a = 0;
                    } else {
                        a = 2;
                    }
                } else {
                    if (data[I][1] > data[I][2]) {
                        a = 1;
                    } else {
                        a = 2;
                    }
                }

                int h = data[N][0] - data[I][0];
                int p = data[N][1] - data[I][1];
                int s = data[N][2] - data[I][2];
                if (h > p) {
                    if (h > s) {
                        b = 0;
                    } else {
                        b = 2;
                    }
                } else {
                    if (p > s) {
                        b = 1;
                    } else {
                        b = 2;
                    }
                }

                int temp = data[I][a] + data[N][b] - data[I][b];
                if (temp > max) {
                    max = temp;
                    maxIndex = i;
                    maxA = a;
                    maxB = b;
                }
            }
            int sum = max;
            switches.add(new int[]{0, maxA});
            switches.add(new int[]{maxIndex, maxB});
            for (int i = 1; i < k; i++) {
                max = 0;
                maxIndex = 0;
                int maxIndex2 = 0;
                int used = 0;
                int used2 = 0;
                for (int j = 1; j < n; j++) {
                    int temp = binSearch(switches, j, switches.size() - 1);
                    if (temp < 0) {
                        temp = -1 * (temp + 1);
                        int temp2 = temp - 1;
                        int org, cur, h, s, p, h2, p2, s2;
                        if (temp == switches.size()) {
                            org = data[N][switches.get(temp2)[1]] - data[switches.get(temp2)[0] - 1][switches.get(temp2)[1]];
                            h = data[N][0] - data[j - 1][0];
                            p = data[N][1] - data[j - 1][1];
                            s = data[N][2] - data[j - 1][2];
                        }
                        else {
                            if (temp2 == 0) {
                                org = data[switches.get(temp)[0] - 1][switches.get(temp2)[1]];
                            }
                            else {
                                org = data[switches.get(temp)[0] - 1][switches.get(temp2)[1]] - data[switches.get(temp2)[0] - 1][switches.get(temp2)[1]];
                            }
                            h = data[switches.get(temp)[0] - 1][0] - data[j - 1][0];
                            p = data[switches.get(temp)[0] - 1][1] - data[j - 1][1];
                            s = data[switches.get(temp)[0] - 1][2] - data[j - 1][2];
                        }
                        if (switches.get(temp2)[0] == 0) {
                            h2 = data[j - 1][0];
                            p2 = data[j - 1][1];
                            s2 = data[j - 1][2];
                        }
                        else {
                            h2 = data[j - 1][0] - data[switches.get(temp2)[0] - 1][0];
                            p2 = data[j - 1][1] - data[switches.get(temp2)[0] - 1][1];
                            s2 = data[j - 1][2] - data[switches.get(temp2)[0] - 1][2];
                        }
                        int temp3;
                        int temp4;
                        if (h > p) {
                            if (h > s) {
                                cur = h;
                                temp3 = 0;
                            } else {
                                cur = s;
                                temp3 = 2;
                            }
                        } else {
                            if (p > s) {
                                cur = p;
                                temp3 = 1;
                            } else {
                                cur = s;
                                temp3 = 2;
                            }
                        }
                        if (h2 > p2) {
                            if (h2 > s2) {
                                cur += h2;
                                temp4 = 0;
                            } else {
                                cur += s2;
                                temp4 = 2;
                            }
                        } else {
                            if (p2 > s2) {
                                cur += p2;
                                temp4 = 1;
                            } else {
                                cur += s2;
                                temp4 = 2;
                            }
                        }

                        int dif = cur - org;
                        if (dif > max) {
                            max = dif;
                            maxIndex = j;
                            used = temp3;
                            used2 = temp4;
                            maxIndex2 = temp2;
                        }
                    }
                }
                sum += max;
                switches.get(maxIndex2)[1] = used2;
                switches.add(new int[]{maxIndex, used});
                switches.sort(Comparator.comparingInt(o -> o[0]));
            }
            pw.println(sum);
        } else {
            pw.println(max);
        }

        pw.close();
    }

    private static int binSearch(ArrayList<int[]> arr, int key, int high) {
        int low = 0;
        int index = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid)[0] < key) {
                low = mid + 1;
            } else if (arr.get(mid)[0] > key) {
                high = mid - 1;
            } else if (arr.get(mid)[0] == key) {
                index = mid;
                break;
            }
        }

        if (index != Integer.MAX_VALUE) {
            return index;
        } else {
            return (-1 * low) - 1;
        }
    }
}
